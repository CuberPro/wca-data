package pro.cuber.wca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.cuber.wca.dao.Event;
import pro.cuber.wca.dao.KinchScore;
import pro.cuber.wca.model.PersonalBest;
import pro.cuber.wca.repository.EventRepository;
import pro.cuber.wca.repository.KinchScoreRepository;
import pro.cuber.wca.repository.RanksAverageRepository;
import pro.cuber.wca.repository.RanksSingleRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KinchCalculationService {

    private RanksAverageRepository ranksAverageRepository;
    private RanksSingleRepository ranksSingleRepository;
    private KinchScoreRepository kinchScoreRepository;
    private EventRepository eventRepository;

    public void calculateKinch() {
        kinchScoreRepository.deleteAllData();
        List<Event> events = eventRepository.findByRankLessThanOrderByRankAsc(990);
        for (Event event : events) {
            calculateKinch(event.getId());
        }
    }

    private void calculateKinch(String eventId) {
        switch (eventId) {
            case "333":
            case "222":
            case "444":
            case "555":
            case "666":
            case "777":
            case "333oh":
            case "clock":
            case "minx":
            case "pyram":
            case "skewb":
            case "sq1":
                calculateKinchFromAverage(eventId);
                break;
            case "333mbf":
                calculateKinchFromSingle(eventId);
                break;
            case "333bf":
            case "333fm":
            case "444bf":
            case "555bf":
                calculateKinchFromBoth(eventId);
                break;
            default:
                System.err.println("unknown event!");
                break;
        }
    }

    private void calculateKinchFromAverage(String eventId) {
        Map<String, KinchScore> results = new HashMap<>();
        calculateKinchFromPbList(eventId, ranksAverageRepository.getPersonalBests(eventId), results);
        kinchScoreRepository.saveAll(results.values());
    }

    private void calculateKinchFromBoth(String eventId) {
        Map<String, KinchScore> results = new HashMap<>();
        calculateKinchFromPbList(eventId, ranksSingleRepository.getPersonalBests(eventId), results);
        calculateKinchFromPbList(eventId, ranksAverageRepository.getPersonalBests(eventId), results);
        kinchScoreRepository.saveAll(results.values());
    }

    private void calculateKinchFromSingle(String eventId) {
        Map<String, KinchScore> results = new HashMap<>();
        calculateKinchFromPbList(eventId, ranksSingleRepository.getPersonalBests(eventId), results);
        kinchScoreRepository.saveAll(results.values());
    }

    private void calculateKinchFromPbList(String eventId, List<PersonalBest> personalBests,
                                          Map<String, KinchScore> results) {
        Map<String, Integer> records = new HashMap<>();
        for (PersonalBest pb : personalBests) {
            updateRecordMap(records, pb.getGender(), pb.getBest(), pb.getContinentId(), pb.getCountryId());
        }
        for (PersonalBest pb : personalBests) {
            String personId = pb.getPersonId();
            KinchScore kinchScore = buildKinchScore(eventId, records, pb.getPersonId(), pb.getCountryId(),
                pb.getContinentId(), pb.getGender(), pb.getBest(), results.get(personId));
            results.put(personId, kinchScore);
        }
    }

    private KinchScore buildKinchScore(String eventId, Map<String, Integer> records, String personId,
                                       String countryId, String continentId, String gender, int best,
                                       KinchScore result) {
        if (result == null) {
            result = new KinchScore();
            result.setPersonId(personId);
            result.setCountryId(countryId);
            result.setContinentId(continentId);
            result.setGender(gender);
            result.setEventId(eventId);
        }

        String normalizedGender = normalizeGender(gender);
        result.setWorldAll(better(kinchScore(eventId, records, getRecordKey(null, "a"), best), result.getWorldAll()));
        result.setWorldSame(better(kinchScore(eventId, records, getRecordKey(null, normalizedGender), best),
            result.getWorldSame()));
        result.setContinentAll(better(kinchScore(eventId, records, getRecordKey(continentId, "a"), best),
            result.getContinentAll()));
        result.setContinentSame(better(kinchScore(eventId, records, getRecordKey(continentId, normalizedGender),
            best), result.getContinentSame()));
        result.setCountryAll(better(kinchScore(eventId, records, getRecordKey(countryId, "a"), best),
            result.getCountryAll()));
        result.setCountrySame(better(kinchScore(eventId, records, getRecordKey(countryId, normalizedGender), best),
            result.getCountrySame()));

        return result;
    }

    private BigDecimal better(BigDecimal bd1, BigDecimal bd2) {
        if (bd1 == null) {
            return bd2;
        }
        if (bd2 == null) {
            return bd1;
        }
        return bd1.compareTo(bd2) > 0 ? bd1 : bd2;
    }

    private void updateRecordMap(Map<String, Integer> records, String gender, int best, String continentId,
                                 String countryId) {
        String normalizedGender = normalizeGender(gender);
        String[] recordKeys = {
            getRecordKey(null, "a"),
            getRecordKey(null, normalizedGender),
            getRecordKey(continentId, "a"),
            getRecordKey(continentId, normalizedGender),
            getRecordKey(countryId, "a"),
            getRecordKey(countryId, normalizedGender)
        };
        for (String key : recordKeys) {
            Integer record = records.get(key);
            if (record == null || record > best) {
                records.put(key, best);
            }
        }
    }

    private BigDecimal kinchScore(String eventId, Map<String, Integer> records, String key, int best) {
        int record = records.get(key);
        if ("333mbf".equals(eventId)) {
            BigDecimal mbfScoreRecord = mbfScore(record);
            BigDecimal mbfScoreBest = mbfScore(best);
            return mbfScoreBest.movePointRight(2).divide(mbfScoreRecord, RoundingMode.HALF_UP).setScale(2,
                RoundingMode.HALF_UP);
        } else {
            BigDecimal score = BigDecimal.valueOf(record * 100.0 / best);
            return score.setScale(2, RoundingMode.HALF_UP);
        }
    }

    private BigDecimal mbfScore(int origin) {
        BigDecimal difference = BigDecimal.valueOf(99 - origin / 10_000_000);
        int time = origin % 10_000_000 / 100;
        return difference.add(BigDecimal.valueOf((3600 - time) / 3600.0).setScale(4, RoundingMode.HALF_UP));
    }

    private static String normalizeGender(String gender) {
        return "".equals(gender) ? "u" : gender; // u stands for unknown
    }

    private String getRecordKey(String regionName, String gender) {
        if (regionName == null) {
            return String.join("_", "WR", gender);
        } else if (regionName.charAt(0) == '_') {
            return String.join("_", "CR", regionName.substring(1), gender);
        } else {
            return String.join("_", "NR", regionName, gender);
        }
    }

    @Autowired
    public void setRanksAverageRepository(RanksAverageRepository ranksAverageRepository) {
        this.ranksAverageRepository = ranksAverageRepository;
    }

    @Autowired
    public void setRanksSingleRepository(RanksSingleRepository ranksSingleRepository) {
        this.ranksSingleRepository = ranksSingleRepository;
    }

    @Autowired
    public void setKinchScoreRepository(KinchScoreRepository kinchScoreRepository) {
        this.kinchScoreRepository = kinchScoreRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
