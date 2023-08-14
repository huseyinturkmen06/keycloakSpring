package com.alibou.keycloak.service;

import com.alibou.keycloak.dto.BelgenetDto;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class BelgenetRequest {

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
            .readTimeout(0, TimeUnit.SECONDS)     // Set read timeout to 0 (infinite)
            .build();

    String url = "https://www.belgenet.com.tr/mobil-imza-service/mobilImzaLogin";

    private String konu;
    private String operator;
    private String telNo;
    private String tcKimlikNo;


    public String requestToBelgenet(BelgenetDto belgenetDto) throws IOException {

        konu=belgenetDto.getKonu();
        operator=belgenetDto.getOperator();
        telNo=belgenetDto.getTelNo();
        tcKimlikNo=belgenetDto.getTcKimlikNo();

        String json = "{\"konu\":\"" + konu + "\",\"operator\":\"" + operator + "\",\"telNo\":\"" + telNo + "\",\"tcKimlikNo\":\"" + tcKimlikNo + "\"}";

        System.out.println(json.toString() + "\n");
        System.out.println(konu + operator + telNo + tcKimlikNo);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();



        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("Response: " + responseBody);
        return responseBody;

    }



}
