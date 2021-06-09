package com.cc.cca3.repositories;

import com.cc.cca3.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity as u where u.email= :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

//    @Query("select u from UserEntity as u where u.email = :email")
//    Optional<UserEntity> findByEmail(@Param("email") String email);
//
//    @Query("select u from UserEntity u where u.email = :email and u.status <> 'CANCELLED'")
//    Optional<UserEntity> findUserEntityByEmail(@Param("email") String email);
//
//    @Query("select u from UserEntity as u where u.email = :email and u.status = 'UNVERIFIED'")
//    Optional<UserEntity> findUnverifiedStatusByEmail(@Param("email") String email);
//
//    @Query("select u from UserEntity as u where u.id = :id and u.status <> 'CANCELLED'")
//    Optional<UserEntity> findUserEntityById(@Param("id") Long id);
//
//    @Modifying
//    @Query("update UserEntity u set u.status = :status where u.email = :email")
//    int updateStatusByEmail(@Param("email") String email, @Param("status") Enum status);
//
//    @Query("select u from UserEntity u join fetch u.employees where u.email = :email")
//    Optional<UserEntity> findEmploymentByEmail(@Param("email") String email);
//
//    @Query(nativeQuery = true,
//            value = "select u.name, u.email, cu.title \n" +
//                    "from company_user cu, user_info u \n" +
//                    "where cu.user_id = u.id \n" +
//                    "and cu.company_id = :id " +
//                    "and u.status = 'ACTIVATED' " +
//                    "order by u.name")
//    List<IEmployeeInfo> findAllEmployeeByCompanyId(@Param("id") Long id);

}