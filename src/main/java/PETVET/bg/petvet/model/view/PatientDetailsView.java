package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.ManipulationEntity;
import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.entity.enums.DewormingTypeEnum;

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

    private Date vaccinationDate;

    private boolean vaccinated;

    private String vaccine;

    private boolean isCastrated;

    @SuppressWarnings("SpellCheckingInspection")
    private boolean isDewormed;

    private DewormingTypeEnum dewormingType;

    @SuppressWarnings("SpellCheckingInspection")
    private Date dewormingDate;

    public PatientDetailsView() {
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public PatientDetailsView setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
        return this;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public PatientDetailsView setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
        return this;
    }

    public String getVaccine() {
        return vaccine;
    }

    public PatientDetailsView setVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public PatientDetailsView setCastrated(boolean castrated) {
        isCastrated = castrated;
        return this;
    }

    public boolean isDewormed() {
        return isDewormed;
    }

    public PatientDetailsView setDewormed(boolean dewormed) {
        isDewormed = dewormed;
        return this;
    }

    public DewormingTypeEnum getDewormingType() {
        return dewormingType;
    }

    public PatientDetailsView setDewormingType(DewormingTypeEnum dewormingType) {
        this.dewormingType = dewormingType;
        return this;
    }

    public Date getDewormingDate() {
        return dewormingDate;
    }

    public PatientDetailsView setDewormingDate(Date dewormingDate) {
        this.dewormingDate = dewormingDate;
        return this;
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
