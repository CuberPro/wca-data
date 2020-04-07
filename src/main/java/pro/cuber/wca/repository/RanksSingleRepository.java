package pro.cuber.wca.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import pro.cuber.wca.dao.RanksSingle;
import pro.cuber.wca.dao.RanksSinglePK;
import pro.cuber.wca.model.PersonalBest;

import java.util.List;

public interface RanksSingleRepository extends Repository<RanksSingle, RanksSinglePK> {

    @Query("select r.personId as personId, r.eventId as eventId, r.best as best, " +
        "p.gender as gender, p.countryId as countryId, c.continentId as continentId " +
        "from RanksSingle as r " +
        "left join Person as p on r.personId=p.id and p.subid=1 " +
        "left join Country as c on p.countryId=c.id " +
        "where r.eventId=:eventId")
    List<PersonalBest> getPersonalBests(@Param("eventId") String eventId);
}
