package com.example.springbootdemotoken.extend;

import com.example.springbootdemotoken.utils.HttpUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BodyReaderHttpRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;
    private Map<String, String> header = new HashMap<>(16);

    public BodyReaderHttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            header.put(name, request.getHeader(name));
        }
        body = HttpUtils.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            public boolean isFinished() {
                return false;
            }

            public boolean isReady() {
                return false;
            }

            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }

        };
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return super.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return super.getHeaders(name);
    }

    /**
     * <pre>获取HttpRequest的body数据:</pre>
     *
     * @return java.lang.String
     * @throws
     * @author iwinkfc
     * @create 2018/1/15 0015 - 21:52
     */
    public String getBody() {
        try {
            return new String(this.body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>获取HttpRequest的header数据:</pre>
     *
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               java.lang.String>
     * @throws
     * @author iwinkfc
     * @create 2018/1/15 0015 - 21:51
     */
    public Map<String, String> getHeader() {
        return this.header;
    }
}