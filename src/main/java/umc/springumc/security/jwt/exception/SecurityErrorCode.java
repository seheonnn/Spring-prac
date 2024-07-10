package umc.springumc.security.jwt.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.springumc.apiPayload.global.ApiResponse;
import umc.springumc.apiPayload.global.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {

	INVALID_TOKEN(HttpStatus.BAD_REQUEST, "SEC4001", "잘못된 형식의 토큰입니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "SEC4010", "인증이 필요합니다."),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "SEC4011", "토큰이 만료되었습니다."),
	TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED, "SEC4012", "토큰이 위조되었거나 손상되었습니다."),
	BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "SEC4013", "아이디 또는 비밀번호가 일치하지 않습니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "SEC4030", "권한이 없습니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "SEC4040", "존재하지 않는 계정입니다."),
	TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "SEC4041", "토큰이 존재하지 않습니다."),
	INTERNAL_SECURITY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SEC5000", "인증 처리 중 서버 에러가 발생했습니다."),
	INTERNAL_TOKEN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SEC5001", "토큰 처리 중 서버 에러가 발생했습니다."),

	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public ApiResponse<Void> getErrorResponse() {
		return ApiResponse.onFailure(code, message);
	}
}
