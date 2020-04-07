package pro.cuber.wca.repository;

import org.springframework.data.repository.Repository;
import pro.cuber.wca.dao.Event;

import java.util.List;

public interface EventRepository extends Repository<Event, String> {
    List<Event> findByRankLessThanOrderByRankAsc(Integer limit);
}
