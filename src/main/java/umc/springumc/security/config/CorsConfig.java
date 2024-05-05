package umc.springumc.security.config;

import java.util.ArrayList;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CorsConfig implements WebMvcConfigurer {

	public static CorsConfigurationSource apiConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		ArrayList<String> allowedOriginPatterns = new ArrayList<>();
		allowedOriginPatterns.add("http://localhost:8080");
		allowedOriginPatterns.add("http://localhost:3000");

		ArrayList<String> allowedHttpMethods = new ArrayList<>();
		allowedHttpMethods.add("GET");
		allowedHttpMethods.add("POST");

		configuration.setAllowCredentials(true);   // 내 서버가 응답을 할 때 응답해준 json을 자바스크립트에서 처리할 수 있게 할지를 설정
		configuration.setAllowedOrigins(allowedOriginPatterns); // 응답 허용할 uri
		configuration.setAllowedMethods(allowedHttpMethods); // 응답 허용할 HTTP method
		configuration.addAllowedHeader("*"); // 응답 허용할 header

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // /** -> 모든 요청들 configuration 설정을 따르도록 등록

		return source;
	}
}
