package PETVET.bg.petvet.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

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
