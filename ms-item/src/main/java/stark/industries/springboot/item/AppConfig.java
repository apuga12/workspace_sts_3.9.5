package stark.industries.springboot.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean("clienteRestTemplate")
	@LoadBalanced
	public RestTemplate registrarRestemplate() {
		return new RestTemplate();
	}
}
