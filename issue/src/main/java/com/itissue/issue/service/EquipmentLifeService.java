package com.itissue.issue.service;

import com.itissue.issue.models.Equipment;
import com.itissue.issue.repo.EquipmentRepo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EquipmentLifeService {
    private final EquipmentRepo equipmentRepo;

    private List<Equipment> comesToTheEnd;
    private List<Equipment> overdue;

    private final List<String> comesToTheEndFormatted = new ArrayList<>();
    private List<String> overdueFormatted;

    @PostConstruct
    public void fillCollections() {
        StringBuilder sb = new StringBuilder();

        comesToTheEnd = equipmentRepo.comesToTheEnd();

        for (Equipment e : comesToTheEnd) {
            sb.append(e.getEquipmetType().getName()).append(" ")
                    .append(e.getManufacturer().getName()).append(" [")
                    .append(e.getSerial_number()).append("] ")
                    .append(e.getName()).append(" (истекает ")
                    .append(e.getExp_date().toLocalDate()).append(")");
            comesToTheEndFormatted.add(sb.toString());
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