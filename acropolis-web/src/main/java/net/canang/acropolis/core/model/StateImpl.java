package net.canang.acropolis.core.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
@Indexed
@Entity(name = "State")
@Table(name = "STATE")
public class StateImpl implements State {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_STATE")
    @SequenceGenerator(name = "SEQ_STATE", sequenceName = "SEQ_STATE", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
