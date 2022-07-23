package PETVET.bg.petvet.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class EditPatientDTO {
    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String animalType;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birthday;

    @NotNull
    private boolean vaccinated;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String identificationNumber;

    private String breed;

    @NotNull
    private Long ownerId;


    public EditPatientDTO() {
    }

    public Long getId() {
        return id;
    }

    public EditPatientDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EditPatientDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public EditPatientDTO setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public EditPatientDTO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public EditPatientDTO setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public EditPatientDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public EditPatientDTO setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public EditPatientDTO setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
