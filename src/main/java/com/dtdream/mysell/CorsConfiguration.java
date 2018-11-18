//package com.dtdream.mysell;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Author yxiumei
// * @Data 2018/11/13 23:42
// */
//@Configuration
//public class CorsConfiguration {
//
//    /**
//     * 配置可以实现跨域
//     * @param
//     */
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                // 所有方法可以访问
////                .allowedMethods("*")
////                // 所有请求可以访问
////                .allowedOrigins("http://localhost:8088")
////                // 允许所有请求头
////                .allowedHeaders("*");
////    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:8088");
//            }
//        };
//    }
//}
