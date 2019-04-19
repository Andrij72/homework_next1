package jpadb.entities;

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
@Table(name = "developers")
public class Developer extends TopClass {
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private String sex;
    @Column(name = "salary")
    private Integer salary;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set <Skill> skills = new HashSet <>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "developers")
    private Set <Project> projects = new HashSet <>();

    @PrePersist
    public void prePersist () {
        log.info("Developer.onPrePersist()");
    }

    @PostPersist
    public void postPersist () {
        log.info("Developer.onPostPersist()");
    }

    @PreRemove
    public void preRemove () {
        log.info("Developer.onPreRemove()");
    }

    @PostRemove
    public void postRemove () {
        log.info("Developer.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate () {
        log.info("Developer.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate () {
        log.info("Developer.onPostUpdate()");
    }

    @PostLoad
    public void postLoad () {
        log.info("Developer.onPostLoad()");
    }
}
