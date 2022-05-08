package com.recruitment.forexbuddy.model.entity;

import com.recruitment.forexbuddy.model.enums.RequestType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log_history")
public class HistoryLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private RequestType requestType;
    private String currencyFrom;
    private String currencyTo;
    private double rate;
    private double amount;
}
