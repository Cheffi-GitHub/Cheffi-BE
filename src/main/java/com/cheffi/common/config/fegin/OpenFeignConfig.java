package com.cheffi.common.config.fegin;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.cheffi.web"})
public class OpenFeignConfig {
}
