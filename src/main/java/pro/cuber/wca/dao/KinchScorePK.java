package pro.cuber.wca.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class KinchScorePK implements Serializable {
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

        KinchScorePK that = (KinchScorePK) o;

        if (!Objects.equals(personId, that.personId)) return false;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
