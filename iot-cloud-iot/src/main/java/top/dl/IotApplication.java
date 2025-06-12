package top.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: minder
 * @createTime: 2025/05/11 16:03
 * @description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.dl.feign"})
public class IotApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }
}
