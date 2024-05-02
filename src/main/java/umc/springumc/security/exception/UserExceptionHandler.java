package umc.springumc.security.exception;

import umc.springumc.apiPayload.global.BaseErrorCode;
import umc.springumc.apiPayload.global.CustomException;

public class UserExceptionHandler extends CustomException {
	public UserExceptionHandler(BaseErrorCode code) {
		super(code);
	}
}
