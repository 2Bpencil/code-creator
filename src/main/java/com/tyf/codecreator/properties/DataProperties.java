package com.tyf.codecreator.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 读取配置
 * @author: tyf
 * @create: 2020-05-12 14:32
 **/
@Component
@PropertySource(value = { "classpath:data.properties"},ignoreResourceNotFound = true)
public class DataProperties {

    @Value("${outputPath}")
    private String outputPath;

    public String getOutputPath() {
        return outputPath;
    }
}
