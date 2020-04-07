package pro.cuber.wca.dao;

import javax.persistence.*;

@Entity
@IdClass(RanksAveragePK.class)
public class RanksAverage {
    private String personId;
    private String eventId;
    private int best;
    private int worldRank;
    private int continentRank;
    private int countryRank;

    @Id
    @Column(name = "personId")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Id
    @Column(name = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "best")
    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }

    @Basic
    @Column(name = "worldRank")
    public int getWorldRank() {
        return worldRank;
    }

    public void setWorldRank(int worldRank) {
        this.worldRank = worldRank;
    }

    @Basic
    @Column(name = "continentRank")
    public int getContinentRank() {
        return continentRank;
    }

    public void setContinentRank(int continentRank) {
        this.continentRank = continentRank;
    }

    @Basic
    @Column(name = "countryRank")
    public int getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(int countryRank) {
        this.countryRank = countryRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RanksAverage that = (RanksAverage) o;

        if (best != that.best) return false;
        if (worldRank != that.worldRank) return false;
        if (continentRank != that.continentRank) return false;
        if (countryRank != that.countryRank) return false;
        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + best;
        result = 31 * result + worldRank;
        result = 31 * result + continentRank;
        result = 31 * result + countryRank;
        return result;
    }
}
