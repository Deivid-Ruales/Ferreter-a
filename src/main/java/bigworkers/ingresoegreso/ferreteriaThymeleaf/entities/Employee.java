package bigworkers.ingresoegreso.ferreteriaThymeleaf.entities;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_employee")
    private long idEmployee;

    @ManyToOne
    @JoinColumn(name="id_document_type", nullable = false)
    private DocumentType documentType;
    @javax.validation.constraints.NotEmpty
    @Column(name="document_number", nullable = false)
    private String documentNumber;
    @javax.validation.constraints.NotEmpty
    @Column(name="name", nullable = false)
    private String name;
    @javax.validation.constraints.NotEmpty
    @Column(name="surnames", nullable = false)
    private String surnames;
    @javax.validation.constraints.NotEmpty
    @Column(name="address", nullable = false)
    private String address;
    @javax.validation.constraints.NotEmpty
    @Column(name="phone", nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name="id_profile",nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="id_enterprise",nullable = false)
    private Enterprise enterprise;

    @Column(name="state")
    private boolean state;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="updated_at")
    private Timestamp updatedAt;

    public long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updateAt) {
        this.updatedAt = updateAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", documentType=" + documentType +
                ", documentNumber='" + documentNumber + '\'' +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", profile=" + profile +
                ", enterprise=" + enterprise +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
