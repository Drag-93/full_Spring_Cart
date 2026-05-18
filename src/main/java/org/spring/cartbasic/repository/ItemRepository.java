package org.spring.cartbasic.repository;

import org.spring.cartbasic.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    //@Query를 사용하여 JPQL패치 조인을 명시
    //i.memberEntity를 한번에(fetch)묶어서(join)가져오라는 뜻

    @Query("select i from ItemEntity i join fetch i.memberEntity")
    List<ItemEntity> findAllWithMember();

}
