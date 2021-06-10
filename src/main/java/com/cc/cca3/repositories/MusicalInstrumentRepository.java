package com.cc.cca3.repositories;

import com.cc.cca3.models.MusicalInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, Long> {

    @Query("select m from MusicalInstrument as m where m.type= :type")
    List<MusicalInstrument> findByType(@Param("type") String type);

    @Query(nativeQuery = true,
            value = "select * \n" +
                    "from musical_instrument mi \n" +
                    "order by mi.count DESC limit 20")
    List<MusicalInstrument> getAllAndOrderByCount();

    @Modifying
    @Query("update MusicalInstrument c set c.added=true where c.id=:id")
    int updateAddedById(@Param("id") Long id);


    @Modifying
    @Query("update MusicalInstrument c set c.added=false where c.id=:id")
    int updateAddedByIdToFalse(@Param("id") Long id);
}
