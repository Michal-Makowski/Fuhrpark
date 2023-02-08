package com.makowski.fuhrpark.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 9, message = "Car number is too long")
    @NotBlank(message = "Car number cannot be blank")
    @NonNull
    @Column(name = "car_number", nullable = false, unique = true)
    private String carNumber;
    
    @NotBlank(message = "Car brand cannot be blank")
    @NonNull
    @Column(name = "brand", nullable = false)
    private String brand;
    
    @NotBlank(message = "Car model cannot be blank")
    @NonNull
    @Column(name = "model", nullable = false)
    private String model;
    
    @Past(message = "Registration date must be in the past")
    @NotNull(message = "Date cannot be empty")
    @NonNull
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate; 

    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Cost> costs;
}
