package org.spring.cartbasic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 스프링 설정 설정  //외부에서 로컬의 특정 폴더에 접근
public class WebConfigMvcClass implements WebMvcConfigurer {


    String saveFiles="file:///E:/full/upload/test0521/"; //실제 파일이 저장되는 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**") // /upload/이미지명 -> 가상(개발자임의)
                .addResourceLocations(saveFiles); // 실제 이미지 경로

    }
}