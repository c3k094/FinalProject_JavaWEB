package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.ManipulationEntity;
import PETVET.bg.petvet.model.entity.OwnerEntity;

import java.util.Date;
import java.util.List;

public class PatientDetailsView {
    private Long id;
    private String name;
    private String animalType;
    private Date birthday;
    private String identificationNumber;
    private OwnerEntity owner;
    private String breed;
    private List<ManipulationEntity> manipulations;

    public PatientDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public PatientDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ManipulationEntity> getManipulations() {
        return manipulations;
    }

    public PatientDetailsView setManipulations(List<ManipulationEntity> manipulations) {
        this.manipulations = manipulations;
        return this;
    }

    public String getName() {
        return name;
    }

    public PatientDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public PatientDetailsView setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public PatientDetailsView setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public PatientDetailsView setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public PatientDetailsView setOwner(OwnerEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public PatientDetailsView setBreed(String breed) {
        this.breed = breed;
        return this;
    }
}
