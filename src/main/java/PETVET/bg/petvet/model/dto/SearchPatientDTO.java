package PETVET.bg.petvet.model.dto;

public class SearchPatientDTO {
    private String identificationNumber;
    private String name;

    public SearchPatientDTO() {
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public SearchPatientDTO setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public SearchPatientDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isEmpty() {
        return (identificationNumber == null || identificationNumber.isEmpty()) && (name == null || name.isEmpty());
    }
}
