package com.example.springbootdemotoken.consts;

/**
 * @author zhaozhengyang
 * @date 2018/05/18 10:06
 */
public enum SysConst {
    /**
     * 系统设定
     */
    DEV(8096, "development"),
    PRO(8080, "product"),
    APP(1, "APP"),
    WEB(2, "WEB"),
    PAD(3, "PAD"),

    /**
     * 客户端请求，返回编码
     */
    OK(HttpConst.OK, "服务器成功返回用户请求的数据"),
    ERROR(HttpConst.SERVERERROE, "操作数据失败"),

    PWD(HttpConst.PWD, "错误的密码或登录帐号，请检查"),
    PWDSIMPLE(HttpConst.PWD, "密码太简单，请重新设置"),
    PHONEUNFOND(HttpConst.PWD, "手机号未录入，请完善个人信息"),
    SHIROUSERNOTFOUND(HttpConst.PWD, "用户不存在,请联系管理员"),
    DEPTNOTFOUND(HttpConst.PWD, "操作部门无效,请联系管理员"),
    CREATED(HttpConst.CREATED, "用户新建数据成功"),
    UPDATED(HttpConst.CREATED, "用户修改数据成功"),
    ACCEPTED(HttpConst.ACCEPTED, "受认可的资源"),
    CREATEFTD(HttpConst.CREATEFTD, "客户端创建数据失败"),
    UPDATEFTD(HttpConst.CREATEFTD, "客户端修改数据失败"),
    NOCONTENT(HttpConst.NOCONTENT, "用户删除数据成功"),
    DELETED(HttpConst.OK, "客户端删除数据成功"),
    DELETEFTD(HttpConst.DELETEFTD, "客户端删除数据失败"),
    BADREQUEST(HttpConst.BADREQUEST, "用户发出的请求有错误"),
    UNAUTHORIZED(HttpConst.UNAUTHORIZED, "客户端没有授权的请求"),
    FORBIDDEN(HttpConst.FORBIDDEN, "客户端被禁止的请求"),
    NOTFOUND(HttpConst.NOTFOUND, "未知的资源响应"),
    NOTALLOWED(HttpConst.NOTALLOWED, "客户端被拒绝的请求"),
    SERVERERROE(HttpConst.SERVERERROE, "服务器发送未知的错误"),
    SERVERUNAVAILABLE(HttpConst.SERVERUNAVAILABLE, "服务器当前不能处理请求"),

    /**
     * 异常处理编码
     */
    IPEXCEPTION(HttpConst.NOTALLOWED, "IP访问异常"),
    TOKENEXCEPTION(HttpConst.NOTALLOWED, "Token认证异常"),
    XSSSQLEXCEPTION(HttpConst.NOTALLOWED, "XSS-SQL验证异常"),
    PERMISSIONEXCEPTION(HttpConst.NOTALLOWED, "权限验证异常"),

    NULLPOINTEREXCEPTION(20101, "空指针引用异常"),
    CLASSCASTEXCEPTION(20102, "类型强制转换异常"),
    ILLEGALARGUMENTEXCEPTION(20103, "传递非法参数异常"),
    ARITHMETICEXCEPTION(20104, "算术运算异常"),
    ARRAYSTOREEXCEPTION(20105, "向数组中存放与声明类型不兼容对象异常"),
    INDEXOUTOFBOUNDSEXCEPTION(20106, "下标越界异常"),
    NEGATIVEARRAYSIZEEXCEPTION(20107, "创建一个大小为负数的数组错误异常"),
    NUMBERFORMATEXCEPTION(20108, "数字格式异常"),
    SECURITYEXCEPTION(20109, "安全异常"),
    UNSUPPORTEDOPERATIONEXCEPTION(20110, "不支持的操作异常"),
    SQLEXCEPTION(20111, "SQL执行异常"),
    IOEXCEPTION(20112, "IO流(输入输出)异常"),
    NOSUCHMETHODEXCEPTION(20113, "方法未找到异常"),
    DATETIMEEXCEPTION(20114, "日期时间解析异常"),
    RSPONSECOMMONERROR(20115, "少见异常的异常"),


    /**
     * TOKEN设定
     */
    TOKENSECRET(1, "X#$%(201805011201)(#*!()!CCX<273148:55DELL><AXISJWT>?N<{SX"),
    TOKENALG(2, "HS256"),
    TOKENTYP(3, "JWT"),
    TOKENEXPTIME(4, "4"),
    TOKENPREFIX(5,"jwt_cache:")
    ;

    /**
     * 返回编码
     */
    Integer code;

    /**
     * 消息描述
     */
    String message;

    SysConst(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }


    public String getValue() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
