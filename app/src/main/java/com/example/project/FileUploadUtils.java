package com.example.project;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class FileUploadUtils {
    public static void goSend(File file){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .build();
        Request request = new Request.Builder()
                .url("http://59.0.236.118:3000/test")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FileUploadUtils", "onFailure");
                Log.v("filepath",file.getName());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("success", response.body().string());
            }
        });
    }
}