package com.makowski.fuhrpark.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cost")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = 0, message = "Cost cannot be negative")
    @NotNull(message = "Cost cannot be blank")
    @NonNull
    @Column(name = "cost", nullable = false)
    private double cost;

    @Min(value = 0, message = "Kilometer cannot be negative")
    @NotNull(message = "Kilometer cannot be blank")
    @NonNull
    @Column(name = "kilometer", nullable = false)
    private double kilometer;

    @Past(message = "Date must be in the past")
    @NotNull(message = "Date cannot be empty")
    @NonNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cost_type_id", referencedColumnName = "id")
    private CostType costType;
}
