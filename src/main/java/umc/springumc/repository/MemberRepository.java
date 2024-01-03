package umc.springumc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springumc.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
