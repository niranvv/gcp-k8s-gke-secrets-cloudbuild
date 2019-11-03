package hello;

import java.math.BigInteger;
import java.util.Date;

public class Task {
    private Long id;
    private Date taskDueDate;
    private Date documentDueDate;
    private BigInteger referenceNo;
    private String state;
    private String claimantName;
    private String companyName;
    private String taskName;
    private Date receivedDate;
    private String assingedTo;
    private Date createdAt;

    // @Column(nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    // @LastModifiedDate
    // private Date updatedAt;

    public Long getId() {
        return id;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public Date getDocumentDueDate() {
        return documentDueDate;
    }

    public BigInteger getReferenceNo() {
        return referenceNo;
    }

    public String getState() {
        return state;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getAssingedTo() {
        return assingedTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // public Date getUpdatedAt() {
    // return updatedAt;
    // }
}
