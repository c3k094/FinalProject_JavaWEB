package PETVET.bg.petvet.model.dto;

import PETVET.bg.petvet.model.entity.enums.DewormingType;
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

    private DewormingType dewormingType;

    @SuppressWarnings("SpellCheckingInspection")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dewormingDate;


    public EditPatientDTO() {
    }

    public Long getId() {
        return id;
    }

    public EditPatientDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public EditPatientDTO setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
        return this;
    }

    public String getVaccine() {
        return vaccine;
    }

    public EditPatientDTO setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public EditPatientDTO setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public EditPatientDTO setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public DewormingType getDewormingType() {
        return dewormingType;
    }

    public EditPatientDTO setDewormingType(DewormingType dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getDewormingDate() {
        return dewormingDate;
    }

    public EditPatientDTO setDewormingDate(Date dewormingDate) {
        this.dewormingDate = dewormingDate;
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
