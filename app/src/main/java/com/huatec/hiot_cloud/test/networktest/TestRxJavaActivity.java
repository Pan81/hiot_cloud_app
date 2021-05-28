package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetService;
import com.huatec.hiot_cloud.data.ResultBase;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends AppCompatActivity {
    private static final String basUrl = "http://114.67.88.191:8080";
    private static final String TAG = "TestRxJavaActivity";
    private EditText etToken;
    private Retrofit retrofit;
    private NetService service;
    private EditText etUserName;
    private EditText etPassword;
    private String oldpassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);
        //editText
        etToken = findViewById(R.id.et_rxjava_token);
        /*etUserName=findViewById(R.id.et_rxjava_username);
        etPassword=findViewById(R.id.et_rxjava_password);*/
        //创建Retrofit
        createRetrofit();

        // 登入
        Button btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("pan81 ", "abc123");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo(etToken.getText().toString());
            }
        });
        //修改email
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail(etToken.getText().toString(), "pan8666@abc.com");
            }
        });

        //修改email
        Button btnUpdatePassWord = findViewById(R.id.btn_rxjava_update_password);
        btnUpdatePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword(oldpassword, etPassword.getText().toString(), etPassword.getText().toString(), etToken.getText().toString());
                oldpassword = etPassword.getText().toString();
            }
        });

        //注册
        Button btnRsgist = findViewById(R.id.btn_rxjava_register);
        btnRsgist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });


    }

    private void updatePassword(String oldpassword, String newpassword, String confirmpassword, String authorization) {
        Observable<ResultBase<String>> observer = service.updatePassword(oldpassword, newpassword, confirmpassword, authorization);
        observer.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResultBase<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ResultBase<String> resultBase) {
                if (resultBase != null && resultBase.getData() != null) {
                    String userBean = resultBase.getData();
                    String str = String.format("用户的新密码为：%s",
                            userBean);
                    Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                    Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("pan898");
        userBean.setEmail("pan166@mail.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        service.register(userBean).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            UserBean userBean1 = resultBase.getData();
                            String str = String.format("用户%s的新邮箱为：%s",
                                    userBean1.getUsername(), userBean1.getEmail());
                            Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateEmail(String authorization, String email) {
        Observable<ResultBase<String>> observer = service.updateEmail(authorization, email);
        observer.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResultBase<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ResultBase<String> resultBase) {
                if (resultBase != null && resultBase.getData() != null) {
                    String userBean = resultBase.getData();
                    String str = String.format("用户的新邮箱为：%s",
                            userBean);
                    Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                    Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                //                       主线程观察                          io线程订阅
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            UserBean userBean = resultBase.getData();
                            String str = String.format("用户：%s,email:%s", userBean.getUsername(), userBean.getEmail());
                            Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void login(String userName, String passWord) {
        service.login(userName, passWord, "app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //订阅：建立关系
                .subscribe(new Observer<ResultBase<loginResultDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultBase<loginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            loginResultDTO loginResult = resultBase.getData();
                            Log.d(TAG, "onNext: " + loginResult.getTokenValue());
                            etToken.setText(loginResult.getTokenValue());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //配置RXjava工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(NetService.class);
    }
}