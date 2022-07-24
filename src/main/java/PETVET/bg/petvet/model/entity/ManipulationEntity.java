package PETVET.bg.petvet.model.entity;

import PETVET.bg.petvet.model.entity.enums.DewormingType;
import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "manipulations")
public class ManipulationEntity extends BaseEntity {

    @Column(nullable = false)
    private Date date;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    @ManyToOne
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ManipulationsEnum manipulation;

    private boolean isVaccinated;

    private Date vaccinationDate;

    private String vaccine;

    private boolean isCastrated;

    private boolean isDewormed;
    @Enumerated(EnumType.STRING)
    private DewormingType dewormingType;

    @SuppressWarnings("SpellCheckingInspection")
    private Date dewormingDate;

    public ManipulationEntity() {
    }

    public Date getDate() {
        return date;
    }

    public ManipulationEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public ManipulationEntity setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public ManipulationEntity setAnimal(AnimalEntity animal) {
        this.animal = animal;
        return this;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public ManipulationEntity setDoctor(UserEntity doctor) {
        this.doctor = doctor;
        return this;
    }

    public ManipulationsEnum getManipulation() {
        return manipulation;
    }

    public ManipulationEntity setManipulation(ManipulationsEnum manipulation) {
        this.manipulation = manipulation;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public ManipulationEntity setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public ManipulationEntity setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
        return this;
    }

    public String getVaccine() {
        return vaccine;
    }

    public ManipulationEntity setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public ManipulationEntity setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public ManipulationEntity setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public DewormingType getDewormingType() {
        return dewormingType;
    }

    public ManipulationEntity setDewormingType(DewormingType dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getDewormingDate() {
        return dewormingDate;
    }

    public ManipulationEntity setDewormingDate(Date dewormingDate) {
        this.dewormingDate = dewormingDate;
        return this;
    }
}
