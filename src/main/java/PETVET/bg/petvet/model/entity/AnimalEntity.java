package PETVET.bg.petvet.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
public class AnimalEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String animalType;

    @Column(nullable = false)
    private int age;

    private boolean isVaccinated;

    @Column(nullable = false, unique = true)
    private int identificationNumber;

    private String breed;

    private LocalDate lastVisit;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "animal")
    private List<ManipulationEntity> manipulations;

    public AnimalEntity() {}

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

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
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

    public int getAge() {
        return age;
    }

    public AnimalEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public AnimalEntity setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public AnimalEntity setIdentificationNumber(int identificationNumber) {
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

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public AnimalEntity setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }


}
