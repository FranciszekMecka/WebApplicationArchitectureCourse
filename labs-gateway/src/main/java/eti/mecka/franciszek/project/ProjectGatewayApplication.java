package eti.mecka.franciszek.project;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${labs.player.url}") String playerUrl,
			@Value("${labs.organization.url}") String professionUrl,
			@Value("${labs.gateway.host}") String host
	) {
		return builder
				.routes()
				.route("organizations", route -> route
						.host(host)
						.and()
						.path(
								"/api/organizations/{uuid}",
								"/api/organizations"
						)
						.uri(professionUrl)
				)
				.route("players", route -> route
						.host(host)
						.and()
						.path(
								"/api/players",
								"/api/players/**",
								"/api/organizations/{uuid}/players",
								"/api/organizations/{uuid}/players/**"
						)
						.uri(playerUrl)
				)
				.build();
	}
}
