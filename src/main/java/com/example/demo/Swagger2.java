package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Configuration 注解      让Spring来加载该类配置
 * @EnableSwagger2 注解     启用Swagger2
 *
 * 通过createRestApi函数创建 Docket的 Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，
 * Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被 @ApiIgnore 指定的请求）。
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这是扫描注解的配置，即你的API接口位置(根据包路径扫描接口)【不配置默认扫描项目所有接口】
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")
                .description("初识swagger")
//                .termsOfServiceUrl("")
//                .contact("")
                .version("1.0")
                .build();
    }


}
