package pro.cuber.wca.dao;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "KinchScores", schema = "wca")
@IdClass(KinchScorePK.class)
public class KinchScore implements Persistable<KinchScorePK> {
    private String personId;
    private String countryId;
    private String continentId;
    private String gender;
    private String eventId;
    private BigDecimal worldSame;
    private BigDecimal worldAll;
    private BigDecimal continentSame;
    private BigDecimal continentAll;
    private BigDecimal countrySame;
    private BigDecimal countryAll;

    @Transient
    private KinchScorePK id;

    @Id
    @Column(name = "personId")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "countryId")
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "continentId")
    public String getContinentId() {
        return continentId;
    }

    public void setContinentId(String continentId) {
        this.continentId = continentId;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
    @Column(name = "worldSame")
    public BigDecimal getWorldSame() {
        return worldSame;
    }

    public void setWorldSame(BigDecimal worldSame) {
        this.worldSame = worldSame;
    }

    @Basic
    @Column(name = "worldAll")
    public BigDecimal getWorldAll() {
        return worldAll;
    }

    public void setWorldAll(BigDecimal worldAll) {
        this.worldAll = worldAll;
    }

    @Basic
    @Column(name = "continentSame")
    public BigDecimal getContinentSame() {
        return continentSame;
    }

    public void setContinentSame(BigDecimal continentSame) {
        this.continentSame = continentSame;
    }

    @Basic
    @Column(name = "continentAll")
    public BigDecimal getContinentAll() {
        return continentAll;
    }

    public void setContinentAll(BigDecimal continentAll) {
        this.continentAll = continentAll;
    }

    @Basic
    @Column(name = "countrySame")
    public BigDecimal getCountrySame() {
        return countrySame;
    }

    public void setCountrySame(BigDecimal countrySame) {
        this.countrySame = countrySame;
    }

    @Basic
    @Column(name = "countryAll")
    public BigDecimal getCountryAll() {
        return countryAll;
    }

    public void setCountryAll(BigDecimal countryAll) {
        this.countryAll = countryAll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KinchScore that = (KinchScore) o;

        if (!Objects.equals(personId, that.personId)) return false;
        if (!Objects.equals(countryId, that.countryId)) return false;
        if (!Objects.equals(continentId, that.continentId)) return false;
        if (!Objects.equals(gender, that.gender)) return false;
        if (!Objects.equals(eventId, that.eventId)) return false;
        if (!Objects.equals(worldSame, that.worldSame)) return false;
        if (!Objects.equals(worldAll, that.worldAll)) return false;
        if (!Objects.equals(continentSame, that.continentSame))
            return false;
        if (!Objects.equals(continentAll, that.continentAll)) return false;
        if (!Objects.equals(countrySame, that.countrySame)) return false;
        return Objects.equals(countryAll, that.countryAll);
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (continentId != null ? continentId.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (worldSame != null ? worldSame.hashCode() : 0);
        result = 31 * result + (worldAll != null ? worldAll.hashCode() : 0);
        result = 31 * result + (continentSame != null ? continentSame.hashCode() : 0);
        result = 31 * result + (continentAll != null ? continentAll.hashCode() : 0);
        result = 31 * result + (countrySame != null ? countrySame.hashCode() : 0);
        result = 31 * result + (countryAll != null ? countryAll.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public KinchScorePK getId() {
        if (this.id == null) {
            this.id = new KinchScorePK();
            this.id.setEventId(this.eventId);
            this.id.setPersonId(this.personId);
        }
        return this.id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return true;
    }
}
