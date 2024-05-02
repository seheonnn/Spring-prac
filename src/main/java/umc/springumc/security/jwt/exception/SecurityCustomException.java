package umc.springumc.security.jwt.exception;

import lombok.Getter;
import umc.springumc.apiPayload.global.BaseErrorCode;
import umc.springumc.apiPayload.global.CustomException;

@Getter
public class SecurityCustomException extends CustomException {

	private final Throwable cause;

	public SecurityCustomException(BaseErrorCode errorCode) {
		super(errorCode);
		this.cause = null;
	}

	public SecurityCustomException(BaseErrorCode errorCode, Throwable cause) {
		super(errorCode);
		this.cause = cause;
	}
}
