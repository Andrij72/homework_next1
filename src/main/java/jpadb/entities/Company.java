package jpadb.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j
@Entity
@Table(name = "companies")
public class Company extends TopClass {

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companies")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Company.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Company.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Company.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Company.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Company.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Company.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Company.onPostLoad()");
    }
}
