package top.dl.framework.common.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author: minder
 * @createTime: 2025/04/25 8:51
 * @description:
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"top.dl"};
        return GroupedOpenApi.builder().group("IotCloud")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("2210151384@qq.com");

        return new OpenAPI().info(new Info()
                .title("IotCloud")
                .description("IotCloud")
                .contact(contact)
                .version("1.0")
                .termsOfService("https://dl.top")
                .license(new License().name("MIT")
                        .url("https://dl.top")));
    }

}
