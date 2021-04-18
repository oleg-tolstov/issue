package com.itissue.issue.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_emp",
            foreignKey = @ForeignKey(name = "ID_EMP_FK")
    )
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "equ_id")
     private Equipment equipment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Issue(Long employee) {
    }

    public Issue() {
    }

}
