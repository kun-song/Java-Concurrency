package com.main;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import JavaBean.ResultBean;

public class ByHttpClient {
    private static final String jpgname = "http://g.hiphotos.baidu.com/zhidao/pic/item/86d6277f9e2f0708863675afe924b899a901f26a.jpg";
    private static final String postURL = "http://blog.taoholycity.com:8080/cm/api/serverTest.php?ac=doCheck";
    private static final String postURL3 = "http://192.168.0.102:8080/webserver/user/mobileLoginAction.do";

    // 登录 POST 地址
    public static final String LOGIN_POST_URL = "http://192.168.0.202:8080/webserver/user/userMobileLoginAction.do";
    // 注册 POST 地址
    public static final String REGISTER_POST_URL = "http://192.168.0.202:8080/webserver/user/userMobileRegisterAction.do";
    // 心率 POST 地址
    public static final String GET_PULSE_POST_URL = "http://192.168.0.202:8080/webserver/user/mobilePulseAction.do";

    public static void main(String[] args) {
        String json = getJSONByHttpClient(jpgname, postURL);
        System.out.println("json: " + json);

        /**
         * 1. Gson has only two methods: toJson & fromJson
         * 2. JavaBean's data member name must be the JsonObject's key name
         */
//        Gson gson = new Gson();
//        ResultBean resultBean = gson.fromJson(json, ResultBean.class);
//        System.out.println(resultBean);
    }

    /**
     * get JSON strings from the postURL
     * @param jpgname
     * @param postURL
     * @return
     */
    private static String getJSONByHttpClient(String jpgname, String postURL) {
        String jsonStr = null;
        try {
            // create HttpClient object
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,3000);
            // create POST method
            HttpPost post = new HttpPost(GET_PULSE_POST_URL);
            // set POST parameters
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("userId", "zhou"));
            nameValuePairs.add(new BasicNameValuePair("startTime", ""));
            nameValuePairs.add(new BasicNameValuePair("endTime", ""));
            nameValuePairs.add(new BasicNameValuePair("recordCount", ""));
            nameValuePairs.add(new BasicNameValuePair("isWarn", ""));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  // POST ??????

            // POST method
            HttpResponse response = client.execute(post);
            // get the status code
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode = " + statusCode);

            if (statusCode == 200) {    // 200 == successfully
                // get the response entity
                HttpEntity entity = response.getEntity();
                BufferedReader bf = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                // remove the front noisy characters
//                jsonStr = sb.toString().substring(6);
                System.out.println("JSON jsonStr: " + sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }
}
