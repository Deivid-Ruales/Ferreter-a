package bigworkers.ingresoegreso.ferreteriaThymeleaf.entities;

import javax.persistence.*;

@Entity
@Table(name="documents_types")
public class     DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_document_type")
    private long idDocumentType;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="initials",nullable = false)
    private String initials;
    @Column(name="state")
    private boolean state;

    public long getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(long idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "idDocumentType=" + idDocumentType +
                ", description='" + description + '\'' +
                ", initials='" + initials + '\'' +
                ", state=" + state +
                '}';
    }
}
