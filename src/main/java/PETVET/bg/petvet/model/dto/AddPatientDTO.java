package PETVET.bg.petvet.model.dto;

import PETVET.bg.petvet.model.entity.enums.DewormingTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class AddPatientDTO {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String animalType;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birthdate;

    @NotNull
    private boolean vaccinated;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String identificationNumber;

    private String breed;

    @NotNull
    private Long ownerId;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vaccinationDate;

    private String vaccine;

    private boolean isCastrated;

    @SuppressWarnings("SpellCheckingInspection")
    private boolean isDewormed;

    private DewormingTypeEnum dewormingType;

    @SuppressWarnings("SpellCheckingInspection")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dewormingDate;



    public AddPatientDTO() {
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public AddPatientDTO setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
        return this;
    }

    public String getVaccine() {
        return vaccine;
    }

    public AddPatientDTO setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public AddPatientDTO setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public AddPatientDTO setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public DewormingTypeEnum getDewormingType() {
        return dewormingType;
    }

    public AddPatientDTO setDewormingType(DewormingTypeEnum dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getDewormingDate() {
        return dewormingDate;
    }

    public AddPatientDTO setDewormingDate(Date dewormingDate) {
        this.dewormingDate = dewormingDate;
        return this;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public AddPatientDTO setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public AddPatientDTO setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public AddPatientDTO setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
