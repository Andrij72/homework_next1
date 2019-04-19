package jpadb.entities;

import database.data.Company;
import database.data.Developer;
import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Log4j
@Entity
@Table(name = "projects")
public class Project extends TopClass {

    @Column(name = "cost")
    private Double cost;
    @Column(name = "date")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToMany
    @JoinTable(name = "project_developer",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id")
    )
    private Set<Developer> developers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "project_company",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id")
    )
    private Set<Company> companies = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "project_customer",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
    private Set<Customer> customers = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Project.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Project.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Project.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Project.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Project.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Project.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Project.onPostLoad()");
    }
}
