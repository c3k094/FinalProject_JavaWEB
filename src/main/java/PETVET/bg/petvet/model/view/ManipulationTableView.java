package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.enums.ManipulationsEnum;

import java.util.Date;

public class ManipulationTableView {
    private Long id;

    private Date manipulationDate;

    private String additionalInformation;

    private String doctorFirstname;

    private String doctorLastName;

    private ManipulationsEnum manipulation;

    public ManipulationTableView() {
    }

    public Long getId() {
        return id;
    }

    public ManipulationTableView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDoctorFirstname() {
        return doctorFirstname;
    }

    public ManipulationTableView setDoctorFirstname(String doctorFirstname) {
        this.doctorFirstname = doctorFirstname;
        return this;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public ManipulationTableView setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
        return this;
    }

    public Date getManipulationDate() {
        return manipulationDate;
    }

    public ManipulationTableView setManipulationDate(Date manipulationDate) {
        this.manipulationDate = manipulationDate;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public ManipulationTableView setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }


    public ManipulationsEnum getManipulation() {
        return manipulation;
    }

    public ManipulationTableView setManipulation(ManipulationsEnum manipulation) {
        this.manipulation = manipulation;
        return this;
    }
}
