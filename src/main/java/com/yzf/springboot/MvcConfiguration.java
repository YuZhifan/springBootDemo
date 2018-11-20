package com.yzf.springboot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yzf.springboot.admin.exception.CommonExceptionHandler;
import com.yzf.springboot.admin.interceptror.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*.ico", "/swagger**");
    }

    @Bean
    public SessionInterceptor getSessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //super.configureHandlerExceptionResolvers(resolvers);
        resolvers.add(getCommonExceptionHandler());
    }

    @Bean
    public CommonExceptionHandler getCommonExceptionHandler() {
        return new CommonExceptionHandler();
    }

    /**
     * 若无法排除jackson依赖,优化使用fastJson
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter); // 删除MappingJackson2HttpMessageConverter
        converters.add(getFastJsonHttpMessageConverter());
        converters.add(getStringHttpMessageConverter());
    }

    @Bean
    public FastJsonHttpMessageConverter getFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.parseMediaType("text/xml;charset=UTF-8")));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        //fastJsonConfig.setFeatures(Feature.AllowISO8601DateFormat, Feature.InitStringFieldAsEmpty);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }

    @Bean
    public StringHttpMessageConverter getStringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return stringHttpMessageConverter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
