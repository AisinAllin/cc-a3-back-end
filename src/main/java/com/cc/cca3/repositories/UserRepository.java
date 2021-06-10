package com.cc.cca3.repositories;

import com.cc.cca3.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity as u where u.email= :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Query("select u from UserEntity as u where u.uuid= :uuid")
    Optional<UserEntity> findByUuid(@Param("uuid") String uuid);

    @Modifying
    @Query("update UserEntity u  set u.name=:name, u.address=:address, u.phone=:phone where u.id=:id")
    int updateCompanyProfileById(@Param("id")Long id,
                                 @Param("name") String name,
                                 @Param("address") String address,
                                 @Param("phone") String phone);

}
