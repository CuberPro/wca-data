package pro.cuber.wca.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RanksSinglePK implements Serializable {
    private String personId;
    private String eventId;

    @Column(name = "personId")
    @Id
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Column(name = "eventId")
    @Id
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RanksSinglePK that = (RanksSinglePK) o;

        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
