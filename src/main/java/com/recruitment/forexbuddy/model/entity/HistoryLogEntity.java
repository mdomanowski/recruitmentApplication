package com.recruitment.forexbuddy.model.entity;

import lombok.*;

import javax.persistence.*;

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
    private String currencyFrom;
    private String currencyTo;
    private String amount;
}
