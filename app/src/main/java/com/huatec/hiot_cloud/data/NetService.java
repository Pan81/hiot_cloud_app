package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.data.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.test.networktest.loginResultDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface NetService {

    @POST("/auth/login")
    Observable<ResultBase<loginResultDTO>> login(@Query("username") String userName,
                                                 @Query("password") String password,
                                                 @Query("loginCode") String loginCode);


    @GET("/user/one")
    Observable<ResultBase<UserBean>> getUserInfo(@Header("Authorization") String authorization);

    @PUT("/user/email")
    Observable<ResultBase<String>> updateEmail(@Header("Authorization") String authorization, @Query("email") String email);

    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);

    @PUT("/user/password")
    Observable<ResultBase<String>> updatePassword(@Query("oldpassword") String oldpassword, @Query("newpassword") String newpassword,
                                                  @Query("confirmpassword") String confirmpassword, @Header("Authorization") String Authorization);
}