package com.example.springbootdemotoken.utils;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    //workerman推送主机地址
    private static final String WORKERMAN_URL = "http://47.93.120.99:2121/";


    //业务端测试发送基本推送的api地址
    private static final String JPUSH_URL = "http://47.93.41.211:12306/jpush";
    //业务端测试的极光key
    private static final String APP_KEY = "693905b010cac21a850feae9";
    private static final String MASTER_SECRET = "4ea5ecec5b10d29321d8a8cc";

    private static final String TELPHONE_URL = "http://121.42.245.53:12306/sendnotice";


    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), Charset.forName("UTF-8")));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception ex) {

        }
        return sb.toString();
    }

    /**
     * workerman发送推送
     */
    public static void sendWorkerMan(String channel, String title, String message, String url) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "publish");
        //todo
        paramMap.put("to", channel + "_gcl_line");
        ContentEntity content = new ContentEntity(title, message, url);
        paramMap.put("content", JSON.toJSON(content).toString());
        post(WORKERMAN_URL, paramMap);
    }

    /**
     * 手机短信发送推送
     */
    public static void sendTelphoneMsg(String iphone, String msg) {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", "鸿诚短信");
        paramMap.put("sdst", iphone);
        paramMap.put("smsg", msg);
        paramMap.put("contractnum", "鸿诚短信");

        get(TELPHONE_URL, paramMap);
    }

    /**
     * goeasy发送推送
     */
    public static void sendGoEasy(String channel, String content) {
        /*GoEasy goEasy = new GoEasy( GOEASY_RESTHOST, GOEASY_COMMONKEY );
        goEasy.publish( channel , content );*/
    }

    /**
     * 发送极光推送
     */
    public static void sendJpush(String alias, String content) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("secret", MASTER_SECRET);
        paramMap.put("appkey", APP_KEY);
        paramMap.put("platform", "all");
        paramMap.put("alias", alias);
        paramMap.put("content", content);
        get(JPUSH_URL, paramMap);
    }

    /**
     * 发送 post请求
     */
    public static void post(String url, Map<String, String> paramMap) {
        // 创建参数队列
        List<BasicNameValuePair> params = paramMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httppost.setEntity(uefEntity);
            logger.debug("executing request " + httppost.getURI());
            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    logger.debug("--------------------------------------");
                    logger.debug("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    logger.debug("--------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送 get请求
     */
    public static void get(String url, Map<String, String> paramMap) {
        // 创建参数队列
        String param;
        if (paramMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("?");
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            param = sb.toString();
        } else {
            param = "";
        }
        String finalUrl = url + param;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget.
        HttpGet httpget = new HttpGet(finalUrl);
        try {
            logger.debug("executing request " + httpget.getURI());
            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                logger.debug("--------------------------------------");
                // 打印响应状态
                logger.debug(response.getStatusLine().toString());
                if (entity != null) {
                    // 打印响应内容长度
                    logger.debug("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    logger.debug("Response content: " + EntityUtils.toString(entity));
                }
                logger.debug("------------------------------------");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 推送用包装实体 */
    private static class ContentEntity {
        private String title;
        private String message;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        ContentEntity(String title, String message, String url) {
            this.title = title;
            this.message = message;
            this.url = url;
        }
    }
}
