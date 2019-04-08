package com.example.springbootdemoconsumer.config.webConfig;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
    * @Title: WebConfig
    * @ProjectName springbootdemo
    * @Description: spring boot 父子工程中子工程整合jsp页面配置类，该类将子模块的webapp相对路径添加到tomcat的文件路径中；
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/4/4-17:38
    */
@Configuration
public class WebConfig {
        @Bean
        public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
            return (factory) -> {
                factory.addContextCustomizers((context) -> {
                            //模块中webapp相对路径
                            String relativePath = "springbootdemo-consumer/src/main/webapp";
                            File docBaseFile = new File(relativePath);
                            // 路径是否存在
                            if (docBaseFile.exists()) {
                                context.setDocBase(docBaseFile.getAbsolutePath());
                            }
                        }
                );
            };
        }
}
