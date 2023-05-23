package tw.niq.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(GlobalFilterConfiguration.class);
	
	private final Environment environment;

	public GlobalFilterConfiguration(Environment environment) {
		this.environment = environment;
		String currentGlobalConfig = environment.getProperty("tw.niq.example.config.global.active");
		String currentConfig = environment.getProperty("tw.niq.example.config.active");
		logger.info("currentGlobalConfig: " + currentGlobalConfig);
		logger.info("currentConfig: " + currentConfig);
	}

	@Order(1)
	@Bean
	public GlobalFilter secondPrePostGlobalFilter() {
		
		return (exchange, chain) -> {
			
			logger.info("## secondPrePostGlobalFilter - Pre");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("## secondPrePostGlobalFilter - Post");
			}));
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPrePostGlobalFilter() {
		
		return (exchange, chain) -> {
			
			logger.info("## thirdPrePostGlobalFilter - Pre");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("## thirdPrePostGlobalFilter - Post");
			}));
		};
	}
	
	@Order(3)
	@Bean
	public GlobalFilter fourthPrePostGlobalFilter() {
		
		return (exchange, chain) -> {
			
			logger.info("## fourthPrePostGlobalFilter - Pre");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("## fourthPrePostGlobalFilter - Post");
			}));
		};
	}
	
}
