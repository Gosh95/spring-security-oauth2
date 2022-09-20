package com.example.oauth2.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@EntityGraph(attributePaths = {"authorities"})
	@Query("select m from Member m where m.account.email = :email")
	Optional<Member> findByEmail(String email);
}
