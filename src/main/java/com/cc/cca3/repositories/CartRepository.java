package com.cc.cca3.repositories;

import com.cc.cca3.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart as c where c.userEntity.id= :id")
    List<Cart> findByUserEntity(@Param("id") Long id);

}
