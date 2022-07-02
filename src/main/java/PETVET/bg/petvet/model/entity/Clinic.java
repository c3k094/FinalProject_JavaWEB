package PETVET.bg.petvet.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinics")
public class Clinic extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private String workingHours;

    private String imgUrl;

    @OneToMany(mappedBy = "clinic")
    private List<Animal> patients;

    @ManyToMany
    private List<UserEntity> doctors = new ArrayList<>();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<UserEntity> getDoctors() {
        return doctors;
    }

    public Clinic setDoctors(List<UserEntity> doctors) {
        this.doctors = doctors;
        return this;
    }

    public Clinic() {}


    public String getName() {
        return name;
    }

    public Clinic setName(String name) {
        this.name = name;
        return this;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public Clinic setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Clinic setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public List<Animal> getPatients() {
        return patients;
    }

    public Clinic setPatients(List<Animal> patients) {
        this.patients = patients;
        return this;
    }
}
