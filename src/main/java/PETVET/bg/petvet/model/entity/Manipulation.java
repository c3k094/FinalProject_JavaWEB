package PETVET.bg.petvet.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "manipulations")
public class Manipulation extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    @ManyToOne
    private Animal animal;

    public Manipulation() {
    }

    public LocalDate getDate() {
        return date;
    }

    public Manipulation setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getType() {
        return type;
    }

    public Manipulation setType(String type) {
        this.type = type;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public Manipulation setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
