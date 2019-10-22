package com.example.doctor.project.Interface;

import com.example.doctor.project.entity.User;
import com.example.doctor.project.entity.XuanZhe;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookService {
    @GET("book10")
    Call<ResponseBody> getBook10();
    @GET("book11")
    Call<ResponseBody> getBook11(@Query("id") Integer id);
    @POST("book12")
    Call<User> getBook12(@Body User user);
    @GET("songs.apk")
    Call<ResponseBody> getTxt();
    @POST("getyuwen")
    Call<List<XuanZhe>> getYuWen();
    @POST("jiancha")
    Call<List<String>> getAnswer(@Body User user);
}
