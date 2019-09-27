package com.example.springbootdemotoken.extend;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class BodyReaderHttpResponseWrapper extends HttpServletResponseWrapper {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);

    public BodyReaderHttpResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    /**
     * 覆盖getWriter()方法，将字符流缓冲到本地
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    /**
     * 覆盖getOutputStream()方法，将字节流缓冲到本地
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            public boolean isReady() {
                return false;
            }

            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };
    }

    /**
     * 把缓冲区内容写入输出流后关闭
     *
     * @author xxj
     */
    public void flush() {
        try {
            printWriter.flush();
            printWriter.close();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字节流
     *
     * @return
     */
    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }

    /**
     * 将换出区内容转为文本输出
     *
     * @return
     */
    public String getTextContent() {
        flush();
        return byteArrayOutputStream.toString();
    }
}