package pro.cuber.wca.dao;

import javax.persistence.*;

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

        if (id != null ? !id.equals(country.id) : country.id != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        if (continentId != null ? !continentId.equals(country.continentId) : country.continentId != null) return false;
        return iso2 != null ? iso2.equals(country.iso2) : country.iso2 == null;
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
