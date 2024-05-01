package umc.springumc.service.TempService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.apiPayload.global.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {
	@Override
	public void CheckFlag(Integer flag) {
		if (flag == 1)
			throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
	}
}
