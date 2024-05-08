package umc.springumc.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.security.jwt.util.RedisUtil;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/redis")
@RestController
public class RedisTestController {

	private final RedisUtil redisUtil;
	private final RedisTestRepository redisTestRepository;

	@PostMapping("/test")
	public Void redisTest() {
		RedisTestEntity redisTestEntity = RedisTestEntity.builder()
			.id("key1")
			.value("value1")
			.build();

		long startTime = System.currentTimeMillis();
		redisTestRepository.save(redisTestEntity);
		long saveExecutionTime = System.currentTimeMillis() - startTime;
		log.info("save 실행 시간: {} ", saveExecutionTime);

		startTime = System.currentTimeMillis();
		redisUtil.saveAsValue(
			"key2",
			"value2",
			21600L,
			TimeUnit.MILLISECONDS
		);
		long saveAsValueExecutionTime = System.currentTimeMillis() - startTime;
		log.info("saveAsValue 실행 시간: {} ", saveAsValueExecutionTime);

		startTime = System.currentTimeMillis();
		redisTestRepository.findById("key1");
		saveExecutionTime = System.currentTimeMillis() - startTime;
		log.info("findById 실행 시간: {} ", saveExecutionTime);

		startTime = System.currentTimeMillis();
		redisUtil.get("key2");
		saveAsValueExecutionTime = System.currentTimeMillis() - startTime;
		log.info("get 실행 시간: {} ", saveAsValueExecutionTime);

		return null;
	}
}
