package pro.cuber.wca.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.cuber.wca.dao.KinchScore;
import pro.cuber.wca.dao.KinchScorePK;

import javax.transaction.Transactional;

public interface KinchScoreRepository extends CrudRepository<KinchScore, KinchScorePK> {

    @Modifying
    @Transactional
    @Query("delete from KinchScore ks")
    void deleteAllData();
}
