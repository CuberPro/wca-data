package pro.cuber.wca.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Events")
public class Event {
    private String id;
    private String name;
    private int rank;
    private String format;
    private String cellName;

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
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "cellName")
    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (rank != event.rank) return false;
        if (!Objects.equals(id, event.id)) return false;
        if (!Objects.equals(name, event.name)) return false;
        if (!Objects.equals(format, event.format)) return false;
        return Objects.equals(cellName, event.cellName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + rank;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (cellName != null ? cellName.hashCode() : 0);
        return result;
    }
}
