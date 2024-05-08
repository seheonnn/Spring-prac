package umc.springumc.redis;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@RedisHash(value = "RedisTestEntity", timeToLive = 21600L) // 유효시간: 6시간
public class RedisTestEntity {

	@Id
	private String id;

	private String value;
}
