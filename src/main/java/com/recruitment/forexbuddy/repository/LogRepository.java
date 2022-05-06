package com.recruitment.forexbuddy.repository;

import com.recruitment.forexbuddy.model.entity.HistoryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<HistoryLogEntity, Long> {
}
