package com.h.cheng.mvpdemo.test;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.h.cheng.mvpdemo.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 作者： ch
 * 时间： 2018/6/2 0002-下午 4:00
 * 描述：
 * 来源：
 */


public class V_Login extends BVHelper<P_Login> {


    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.et_search)
    EditText etSearch;
    private Observable observable;

    public V_Login(@NonNull Activity activity) {
        super(R.layout.activity_login, activity);
    }


    @Override
    protected void initView() {

        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        e.onNext(s.toString());

                    }
                });
            }
        }).debounce(400, TimeUnit.MILLISECONDS);



        observable.subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

                Log.e("cheng", s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected P_Login createPresenter() {
        return new P_Login(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void onViewClicked() {

    }

    public void loginSucc() {

        setText(password, "");
        String pwd = getText(password);
        finish();
    }

}
