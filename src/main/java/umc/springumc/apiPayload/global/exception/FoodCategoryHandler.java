package umc.springumc.apiPayload.global.exception;

import umc.springumc.apiPayload.global.BaseErrorCode;
import umc.springumc.apiPayload.global.CustomException;

public class FoodCategoryHandler extends CustomException {
	public FoodCategoryHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
