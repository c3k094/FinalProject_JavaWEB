package PETVET.bg.petvet.model.view;

import java.util.Date;

public class AnimalCardView {
    private String id;
    private String name;
    private String animalType;
    private Date birthday;
    private String identificationNumber;

    public AnimalCardView() {
    }

    public String getId() {
        return id;
    }

    public AnimalCardView setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnimalCardView setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public AnimalCardView setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public AnimalCardView setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public AnimalCardView setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

}
