package umc.springumc.security.jwt.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.apiPayload.global.ApiResponse;
import umc.springumc.security.jwt.util.HttpResponseUtil;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException)
		throws IOException {
		HttpStatus httpStatus;
		ApiResponse<String> errorResponse;

		log.error("[*] AuthenticationException: ", authException);
		httpStatus = HttpStatus.UNAUTHORIZED;
		errorResponse = ApiResponse.onFailure(
			TokenErrorCode.INVALID_TOKEN.getCode(),
			TokenErrorCode.INVALID_TOKEN.getMessage(),
			authException.getMessage());

		HttpResponseUtil.setErrorResponse(response, httpStatus, errorResponse);
	}
}
