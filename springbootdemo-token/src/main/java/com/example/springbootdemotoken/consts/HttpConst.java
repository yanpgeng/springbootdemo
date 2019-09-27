package com.example.springbootdemotoken.consts;


/**
 * 客户端请求，返回编码
 * @author zhaozhengyang
 */
public class HttpConst {

    /**
     * 服务器成功返回用户请求的数据
     */
    public final static Integer OK = 200;

    /**
     * 用户新建或修改数据成功
     */
    public final static Integer CREATED = 201;
    /**
     * 受认可的资源
     */
    public final static Integer ACCEPTED = 202;
    /**
     * 客户端创建/修改失败
     */
    public final static Integer CREATEFTD = 203;
    /**
     * 用户删除数据成功
     */
    public final static Integer NOCONTENT = 204;
    /**
     * 客户端删除失败
     */
    public final static Integer DELETEFTD = 205;
    /**
     * 用户发出的请求有错误，服务器没有进行操作
     */
    public final static Integer BADREQUEST = 400;
    /**
     * 用户没有授权（令牌、用户名、密码错误）
     */
    public final static Integer UNAUTHORIZED = 401;

    /**
     * 错误的密码或凭证
     */
    public final static Integer PWD = 402;
    /**
     * 用户得到授权，但是无权限操作
     */
    public final static Integer FORBIDDEN = 403;
    /**
     * 用户发出的请求针对的是不存在的记录，服务器没有进行操作
     */
    public final static Integer NOTFOUND = 404;
    /**
     * 客户端被拒绝的请求
     */
    public final static Integer NOTALLOWED = 405;
    /**
     * 服务器发生错误
     */
    public final static Integer SERVERERROE = 500;
    /**
     * 服务器当前不能处理请求
     */
    public final static Integer SERVERUNAVAILABLE = 503;

}
