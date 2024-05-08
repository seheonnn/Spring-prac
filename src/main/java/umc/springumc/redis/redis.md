## Redis

- 인메모리 DB
- Key-Value 형태의 NoSQL

### NoSQL vs SQL

- SQL (관계형 데이터베이스): 데이터는 테이블 형식으로 구조화되며, 각 테이블은 레코드(행)의 모음으로 구성됩니다. 테이블 간의 관계는 외래 키(FOREIGN KEY)를 사용하여 정의됩니다.
- NoSQL (비관계형 데이터베이스): 다양한 데이터 모델이 존재하며, 주요 유형으로는 문서형, 키-값 형식, 열 지향, 그래프 형식 등이 있습니다. 각 NoSQL 데이터베이스는 특정한 모델에 중점을 둡니다.

### RedisTemplate vs CrudRepository

- RedisTemplate
    - Low-Level API: Redis의 다양한 명령어를 직접 호출
    - 직접적인 제어
- CrudRepository
    - High-Level API: 높은 수준의 추상화
    - CRUD 지원: 개발자가 데이터베이스와 직접 상호작용하지 않고도 데이터 조작 가능
    - 추상화를 위한 계층으로 인한 속도 저하
    - 불필요한 명령어 호출되거나 사용해야 (ttl 확인,
      hget ~) [참고](https://velog.io/@fksk94/Redis-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%86%8D%EB%8F%84-%EB%B9%84%EA%B5%90)
