package pro.cuber.wca.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Countries", schema = "wca")
public class Country {
    private String id;
    private String name;
    private String continentId;
    private String iso2;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "iso2")
    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!Objects.equals(id, country.id)) return false;
        if (!Objects.equals(name, country.name)) return false;
        if (!Objects.equals(continentId, country.continentId)) return false;
        return Objects.equals(iso2, country.iso2);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (continentId != null ? continentId.hashCode() : 0);
        result = 31 * result + (iso2 != null ? iso2.hashCode() : 0);
        return result;
    }
}
