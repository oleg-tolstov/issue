package com.itissue.issue.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String serial_number;

    private String note;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime start_date; // Ввод в эксплуатацию

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime exp_date; // Срок пригодности


    @ManyToOne
    @JoinColumn(name = "id_type",
            foreignKey = @ForeignKey(name = "ID_TYPE_FK")
    )
    private EquipmetType equipmetType;

    @ManyToOne
    @JoinColumn(name = "id_man",
            foreignKey = @ForeignKey(name = "ID_MAN_FK")
    )
    private Manufacturer manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EquipmetType getEquipmetType() {
        return equipmetType;
    }

    public void setEquipmetType(EquipmetType equipmetType) {
        this.equipmetType = equipmetType;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDateTime exp_date) {
        this.exp_date = exp_date;
    }

    public Equipment(String name, String serial_number, String note, EquipmetType equipmetType, Manufacturer manufacturer) {
        this.name = name;
        this.serial_number = serial_number;
        this.note = note;
        this.equipmetType = equipmetType;
        this.manufacturer = manufacturer;
    }
    public Equipment() {
    }
}
