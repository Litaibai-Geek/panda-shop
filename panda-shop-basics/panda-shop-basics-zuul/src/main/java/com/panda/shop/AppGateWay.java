package com.panda.shop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:微服务网关入口
 * @author: 李太白
 * @date: 2021-09-28 21:47
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
public class AppGateWay {

    @ApolloConfig
    private Config config;

    public static void main(String[] args) {
        SpringApplication.run(AppGateWay.class, args);
    }

    // 添加文档来源
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        // 访问swagger-ui页面每次都会访问get方法
        @SuppressWarnings("rawtypes")
        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            // 从阿波罗中获取配置信息
            return resources();
        }

        /**
         * 从阿波罗服务器中获取resources
         *
         * @return
         */
        private List<SwaggerResource> resources() {

            List resources = new ArrayList<>();
            // app-itmayiedu-order
            // 网关使用服务别名获取远程服务的SwaggerApi
            String swaggerDocJson = swaggerDocument();
            JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                String version = jsonObject.getString("version");
                resources.add(swaggerResource(name, location, version));
            }
            return resources;
        }

        /**
         * 获取swaggerDocument配置
         *
         * @return
         */
        private String swaggerDocument() {
            String property = config.getProperty("panda.shop.zuul.swaggerDocument", "");
            return property;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }

    }

}