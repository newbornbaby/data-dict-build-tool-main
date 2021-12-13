package org.maozuowei;

import org.mybatis.spring.annotation.MapperScan;
import org.maozuowei.config.DictExportConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author maozuowei
 * @date 2020-09-21 17:20:00
 */
@SpringBootApplication
@EnableConfigurationProperties(DictExportConfig.class)
@MapperScan(basePackages = {"org.maozuowei.mapper"})
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE)
                .run(args);
    }

}
