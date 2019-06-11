package com.example.springbootdemotest.enumTest;
    
    
    /**  
    * @Title: EnumDemo01
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/22-10:45
    */
public enum  EnumDemo01 {
    RETURN_TYPE1("name1","1"),
    RETURN_TYPE2("name2","2"),
    RETURN_TYPE3("name3","3");

        private String message;
        private String code;

        EnumDemo01(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
}
