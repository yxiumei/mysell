package com.dtdream.mysell.config;

import com.dtdream.mysell.model.SupportDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yxiumei
 * @Data 2019/3/5 18:08
 */
@Data
@Component
@ConfigurationProperties(prefix = "support")
public class SupportConfig {

    private List<SupportDto> supportDtos;
}
