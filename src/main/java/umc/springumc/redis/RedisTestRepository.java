package umc.springumc.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisTestRepository extends CrudRepository<RedisTestEntity, String> {
}
