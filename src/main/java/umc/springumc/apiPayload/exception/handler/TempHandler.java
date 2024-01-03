package umc.springumc.apiPayload.exception.handler;

import umc.springumc.apiPayload.code.BaseErrorCode;
import umc.springumc.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
