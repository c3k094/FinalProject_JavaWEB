package PETVET.bg.petvet.model.entity;

import PETVET.bg.petvet.model.entity.enums.DewormingType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "animals")
public class AnimalEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String animalType;

    @Column(nullable = false)
    private Date birthday;

    private boolean isVaccinated;

    private Date vaccinationDate;

    private String vaccine;

    private boolean isCastrated;

    @SuppressWarnings("SpellCheckingInspection")
    private boolean isDewormed;

    private DewormingType dewormingType;

    @SuppressWarnings("SpellCheckingInspection")
    private Date dewormingDate;

    @Column(nullable = false, unique = true)
    private String identificationNumber;

    private String breed;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "animal")
    private List<ManipulationEntity> manipulations;

    public AnimalEntity() {}

    public String getVaccine() {
        return vaccine;
    }

    public AnimalEntity setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public DewormingType getDewormingType() {
        return dewormingType;
    }

    public AnimalEntity setDewormingType(DewormingType dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public AnimalEntity setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public AnimalEntity setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public AnimalEntity setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public Date getDewormingDate() {
        return dewormingDate;
    }

    public AnimalEntity setDewormingDate(Date dewormingDate) {
        this.dewormingDate = dewormingDate;
        return this;
    }

    public List<ManipulationEntity> getManipulations() {
        return manipulations;
    }

    public AnimalEntity setManipulations(List<ManipulationEntity> manipulations) {
        this.manipulations = manipulations;
        return this;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public AnimalEntity setOwner(OwnerEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnimalEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public AnimalEntity setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public AnimalEntity setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public AnimalEntity setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public AnimalEntity setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public AnimalEntity setBreed(String breed) {
        this.breed = breed;
        return this;
    }

}
