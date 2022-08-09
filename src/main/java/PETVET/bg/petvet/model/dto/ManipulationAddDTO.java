package PETVET.bg.petvet.model.dto;

import PETVET.bg.petvet.model.entity.enums.DewormingTypeEnum;
import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public class ManipulationAddDTO {

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date manipulationDate;

    private String additionalInformation;

    private Long animalId;

    private Long doctorId;

    @NotNull
    private ManipulationsEnum manipulation;

    private boolean isVaccinated;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date animalVaccinationDate;

    private String vaccine;

    private boolean isCastrated;

    private boolean isDewormed;

    private DewormingTypeEnum dewormingType;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date animalDewormingDate;

    public ManipulationAddDTO() {
    }

    public Date getManipulationDate() {
        return manipulationDate;
    }

    public ManipulationAddDTO setManipulationDate(Date manipulationDate) {
        this.manipulationDate = manipulationDate;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public ManipulationAddDTO setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public ManipulationAddDTO setAnimalId(Long animalId) {
        this.animalId = animalId;
        return this;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public ManipulationAddDTO setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public ManipulationsEnum getManipulation() {
        return manipulation;
    }

    public ManipulationAddDTO setManipulation(ManipulationsEnum manipulation) {
        this.manipulation = manipulation;
        return this;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public ManipulationAddDTO setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
        return this;
    }

    public Date getAnimalVaccinationDate() {
        return animalVaccinationDate;
    }

    public ManipulationAddDTO setAnimalVaccinationDate(Date animalVaccinationDate) {
        this.animalVaccinationDate = animalVaccinationDate;
        return this;
    }

    public String getVaccine() {
        return vaccine;
    }

    public ManipulationAddDTO setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public ManipulationAddDTO setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public ManipulationAddDTO setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public DewormingTypeEnum getDewormingType() {
        return dewormingType;
    }

    public ManipulationAddDTO setDewormingType(DewormingTypeEnum dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getAnimalDewormingDate() {
        return animalDewormingDate;
    }

    public ManipulationAddDTO setAnimalDewormingDate(Date animalDewormingDate) {
        this.animalDewormingDate = animalDewormingDate;
        return this;
    }
}
