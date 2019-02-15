package com.h.cheng.mvpdemo.login;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.h.cheng.mvpdemo.R;
import com.h.cheng.mvpdemo.base.BaseActivity;
import com.h.cheng.mvpdemo.utils.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //示例代码，示例接口
//                presenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
                HashMap<String, String> map = new HashMap<>();
                map.put("userid", "AFBB6A71D95DFCC7");
                map.put("name", "cc123");
                map.put("desc", "456");
                map.put("authtype", "2");
                map.put("publisherpass", "123456");
                map.put("assistantpass", "123456");
                map.put("templatetype", "3");
                String path = createHashedQueryString(map, System.currentTimeMillis(), "5sW7N7NFkpe7OHWPaDX0bwxoBq30SLPR");
                Log.e("cheng", "last:" + path);
                presenter.createRoom("?" + path);

//                presenter.upload("/storage/emulated/0/DCIM/Camera/IMG_20180710_152800_BURST19.jpg");

            }
        });

    }

    @Override
    public void onLoginSucc() {
        //Login Succ

        showtoast("登录成功");

    }

    /**
     * 功能：将一个Map按照Key字母升序构成一个QueryString. 并且加入时间混淆的hash串
     *
     * @param queryMap query内容
     * @param time     加密时候，为当前时间；解密时，为从querystring得到的时间；
     * @param salt     加密salt
     * @return
     */

    public String createHashedQueryString(Map<String, String> queryMap, long time, String salt) {

        Map<String, String> map = new TreeMap<String, String>(queryMap);
        String qsolder = createQueryString(map); //生成queryString方法可自己编写
        Log.e("cheng", "sort:" + qsolder);
//        String qs = "";
//        try {
//            qs = URLEncoder.encode(qsolder, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        Log.e("cheng", "encode:" + qs);

//        if (qs == null) {
//            return null;
//        }



        time = time / 1000;
        Log.e("cheng", "time:" + time);
        String hash = MD5Util.encode(String.format("%s&time=%d&salt=%s", qsolder, time, salt));
        hash = hash.toUpperCase();
        Log.e("cheng", "hash:" + hash);
        String thqs = String.format("%s&time=%d&hash=%s", qsolder, time, hash);
        return thqs;
    }

    private String createQueryString(Map<String, String> map) {

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        StringBuffer stringBuffer = new StringBuffer();

        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }

    class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}

