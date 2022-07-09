package PETVET.bg.petvet.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddPatientDTO {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String animalType;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotNull
    private boolean isVaccinated;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String identificationNumber;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String breed;

    private LocalDate lastVisit;

    @NotNull
    private Long ownerId;

    public AddPatientDTO() {
    }

    public String getName() {
        return name;
    }

    public AddPatientDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public AddPatientDTO setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public AddPatientDTO setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public AddPatientDTO setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public AddPatientDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public AddPatientDTO setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public AddPatientDTO setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public AddPatientDTO setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
