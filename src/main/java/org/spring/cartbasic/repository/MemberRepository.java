package org.spring.cartbasic.repository;

import org.spring.cartbasic.entity.CartEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
