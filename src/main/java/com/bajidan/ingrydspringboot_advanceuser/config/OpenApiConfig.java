package com.bajidan.ingrydspringboot_advanceuser.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "bajidan",
                        email = "babajidedaniju@gmail.com",
                        url = "https://Bajidaan"
                ),
                description = "Advanced user",
                title = "Ingryd SpringBoot Advanced User",
                version = "0.0.1_SNAPSHOT",
                license = @License(
                        name = "Licences name",
                        url = "oracle.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {@Server(
                description = "Advanced user",
                url = "https://Bajidaan/serve"
        ),
                @Server(
                        description = "Second Advanced user",
                        url = "https://Bajidaan/server"
                )
        }
)
public class OpenApiConfig {
}
