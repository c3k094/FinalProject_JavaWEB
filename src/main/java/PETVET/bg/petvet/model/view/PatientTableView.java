package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.OwnerEntity;

import java.util.Date;

public class PatientTableView {

    private Long id;
    private String name;
    private String animalType;
    private Date birthday;
    private String identificationNumber;
    private OwnerEntity owner;

    public PatientTableView() {
    }

    public Long getId() {
        return id;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public PatientTableView setOwner(OwnerEntity owner) {
        this.owner = owner;
        return this;
    }

    public PatientTableView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PatientTableView setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public PatientTableView setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public PatientTableView setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public PatientTableView setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }
}
