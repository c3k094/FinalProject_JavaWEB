package PETVET.bg.petvet.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "manipulations")
public class ManipulationEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    @ManyToOne
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public ManipulationEntity() {
    }

    public LocalDate getDate() {
        return date;
    }

    public ManipulationEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getType() {
        return type;
    }

    public ManipulationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public ManipulationEntity setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
