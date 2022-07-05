package PETVET.bg.petvet.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal extends BaseEntity{

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
    private Owner owner;

    @OneToMany(mappedBy = "animal")
    private List<Manipulation> manipulations;

    public Animal() {}

    public List<Manipulation> getManipulations() {
        return manipulations;
    }

    public Animal setManipulations(List<Manipulation> manipulations) {
        this.manipulations = manipulations;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public Animal setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Animal setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public Animal setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public Animal setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Animal setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public Animal setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }


}
