package com.main;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpURLConn {
    private static final String jpgname = "http://g.hiphotos.baidu.com/zhidao/pic/item/86d6277f9e2f0708863675afe924b899a901f26a.jpg";
    private static final String postURL = "http://blog.taoholycity.com:8080/cm/api/serverTest.php?ac=doCheck";
    private static final String postURL2 = "http://blog.taoholycity.com:8080/cm/api/server.php?ac=getConfig";
    private static final String postURL3 = "http://192.168.0.202:8080/webserver/user/personMobileLoginAction.do";

    public static final String LOGIN_POST_URL = "http://192.168.0.103:8080/webserver/user/userMobileLoginAction.do";

    public static final String REGISTER_POST_URL = "http://blog.taoholycity.com:8080/cm/userinfo/index.php?mode=login";

//    public static final String SETTING_POST_URL = "http://192.168.0.101:8089/webserver/user/getUserSettingMobileAction.do";
    public static final String SETTING_POST_URL = "http://192.168.0.100:8089/webserver/user/versionUpdateMobileAction.do";

    public static void main(String[] args) {
        String jsonStr = null;

        try {
            URL url = new URL(SETTING_POST_URL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            OutputStream outPara = connection.getOutputStream();

            String params = getParams();

            outPara.write(params.getBytes());
            outPara.flush();
            outPara.close();

            connection.connect();
            int responseCode = connection.getResponseCode();

            System.out.println("getResponseCode: " + connection.getResponseCode());

            if (responseCode == 200) {
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
//                jsonStr = sb.toString().substring(6);
//                System.out.println("jsonStr: " + sb.toString());
                String response = sb.toString().replace("\\", "");

                System.out.println("response = " + response);

            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }

    private static String getParams() {
//        String params = "img_server_path:" + jpgname;
        String name = "zhou";
        String pass = "w";
        /**
         * param1=SSSS & param2=BBBB
         */
        String params = "appType=1 &versionMain=1 &versionMini=1";
//        String params = "userId=zhou";

        return params;
    }
}