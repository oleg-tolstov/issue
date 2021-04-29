package com.itissue.issue.service;

import com.itissue.issue.models.Equipment;
import com.itissue.issue.repo.EquipmentRepo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class EquipmentLifeService {
    private final EquipmentRepo equipmentRepo;

    private List<Equipment> comesToTheEnd;
    private List<Equipment> overdue;

    private final List<String> comesToTheEndFormatted = new ArrayList<>();
    private final List<String> overdueFormatted = new ArrayList<>();

    @PostConstruct
    public void fillCollections() {
        StringBuilder sb = new StringBuilder();

        comesToTheEnd = equipmentRepo.comesToTheEnd();
        overdue = equipmentRepo.overdue();

        for (Equipment e : comesToTheEnd) {
            sb.append(e.getEquipmetType().getName()).append(" ")
                    .append(e.getManufacturer().getName()).append(" [")
                    .append(e.getSerial_number()).append("] ")
                    .append(e.getName()).append(" (истекает ")
                    .append(e.getExp_date().toLocalDate()).append(");");
            comesToTheEndFormatted.add(sb.toString());
        }

        sb.setLength(0);

        for (Equipment e : overdue) {
            sb.append(e.getEquipmetType().getName()).append(" ")
                    .append(e.getManufacturer().getName()).append(" [")
                    .append(e.getSerial_number()).append("] ")
                    .append(e.getName()).append(" (истек ")
                    .append(e.getExp_date().toLocalDate()).append(");");
            overdueFormatted.add(sb.toString());
        }
    }

    public EquipmentRepo getEquipmentRepo() {
        return equipmentRepo;
    }

    public List<Equipment> getComesToTheEnd() {
        return comesToTheEnd;
    }

    public List<Equipment> getOverdue() {
        return overdue;
    }

    public List<String> getComesToTheEndFormatted() {
        return comesToTheEndFormatted;
    }

    public List<String> getOverdueFormatted() {
        return overdueFormatted;
    }

    public EquipmentLifeService(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }
}