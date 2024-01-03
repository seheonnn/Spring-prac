package umc.springumc.base.exception.handler;

import umc.springumc.base.Code;
import umc.springumc.base.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(Code errorCode){
        super(errorCode);
    }
}
