package com.h.cheng.mvpdemo.login;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.h.cheng.mvpdemo.R;
import com.h.cheng.mvpdemo.base.BaseActivity;


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
                presenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
//                presenter.upload("/storage/emulated/0/DCIM/Camera/IMG_20180710_152800_BURST19.jpg");

            }
        });

    }

    @Override
    public void onLoginSucc() {
        //Login Succ

        showtoast("登录成功");

    }

}

