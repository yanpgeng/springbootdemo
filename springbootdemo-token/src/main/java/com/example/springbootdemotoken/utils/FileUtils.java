package com.example.springbootdemotoken.utils;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>处理上传下载中的文件中文编码</pre>
 *
 * @author 许言杰(qoodit.kfc)
 * @version v1.0
 * @create 2018/1/6 - 23:51
 * @email xuyanjie@qiaodit.com
 */
public class FileUtils extends cn.hutool.core.io.FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 设置下载文件中文件的名称
     *
     * @param filename
     * @param request
     * @return 通用浏览器文件码名
     */
    public static String encodeFileName(String filename, HttpServletRequest request) {

        String encodedFileName;

        String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser().getGroup().getName();
        logger.error("logger:{ encodeFileName  browser ===> [" + browser + "]}");
        try {
            switch (browser) {
                case "Chrome":
                case "Internet Explorer":
                case "Safari":
                    encodedFileName = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
                    break;
                case "Firefox":
                case "Opera":
                default:
                    encodedFileName = MimeUtility.encodeWord(filename);
                    break;
            }
            return encodedFileName;
        } catch (UnsupportedEncodingException e) {
            return filename;
        }
    }
    /**
     * 判断是否是白名单
     */
    public static boolean isWhiteBeg(String requestUrl, List<String> whiteList) {

        if (requestUrl.length() == 1 && "/".equals(requestUrl)
                || whiteList.size() <= 0) {
            return true;
        } else {
            for (String urlTemp : whiteList) {
                if (requestUrl.indexOf(urlTemp) > -1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 读取白名单配置文件
     */
    public static List<String> readWhiteFile(InputStream stream) {

        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if ("".equals(line) || line.contains("#")) {
                } else {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }

        return list;
    }

    public static String GenerateFile(String imgSource, String savePath, String saveName, HttpServletRequest request) throws IOException {
        return null;
    }
}
