package jpadb.entities;

import database.data.Project;
import lombok.*;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Log4j
@Entity
@Table(name = "customers")
public class Customer extends TopClass {

    @Column(name = "age")
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customers")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Customer.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Customer.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Customer.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Customer.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Customer.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Customer.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Customer.onPostLoad()");
    }
}
