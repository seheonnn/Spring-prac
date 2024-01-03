package umc.springumc.apiPayload.exception.handler;

import umc.springumc.apiPayload.code.BaseErrorCode;
import umc.springumc.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
