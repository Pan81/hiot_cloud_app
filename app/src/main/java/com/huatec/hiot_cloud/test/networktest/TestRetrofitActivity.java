package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.test.mvptest.modle.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = " TestRetrofitActivity";
    private TestRetrofitService service;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        //创建retrofit和service对象
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });


        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2("pan81", "abc123", "app");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("0350ef8d20ef447681378b654bb43e32_137cbb00c113403c9d0bad181b5a767f_use");
            }
        });
        //修改邮箱
        Button btnUpdataEmail = findViewById(R.id.btn_retrofit_email);
        btnUpdataEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("0350ef8d20ef447681378b654bb43e32_137cbb00c113403c9d0bad181b5a767f_use", "p8111@email.com");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    //注册用户
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("pan8");
        userBean.setEmail("81@email.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);
    }

    //修改邮箱
    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        callEnqueue(call);
    }

    //获取用户信息
    private void getUserInfo(String authorization) {
        Call<ResponseBody> call = service.getUserInfo(authorization);
        callEnqueue(call);
    }

    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueue(call);
    }

    //另一种接口登入方式
    private void login2(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueue(call);
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        callEnqueue(call);
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }

}