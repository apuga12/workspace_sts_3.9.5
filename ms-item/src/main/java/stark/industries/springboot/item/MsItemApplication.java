package stark.industries.springboot.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Internamente Feign se enlaza con Ribbon

@RibbonClient(name="servicio-productos")
@EnableFeignClients
@SpringBootApplication
public class MsItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemApplication.class, args);
	}

}
