package com.sdp.eteaching;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class EteachingApplication {


    public static void main(String[] args) {
        SpringApplication.run(EteachingApplication.class, args);
    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement(@Value("${multipart.maxFileSize}")String maxFileSize, @Value("${multipart.maxRequestSize}") String maxRequestSize) {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.ofBytes(8192));
//        factory.setMaxRequestSize(DataSize.ofBytes(8192));
//        return factory.createMultipartConfig();
//    }

}
