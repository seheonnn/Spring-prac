package umc.springumc.base.exception.handler;

import umc.springumc.base.Code;
import umc.springumc.base.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(Code errorCode){
        super(errorCode);
    }
}
