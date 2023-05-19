package tw.niq.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(GlobalFilterConfiguration.class);

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
