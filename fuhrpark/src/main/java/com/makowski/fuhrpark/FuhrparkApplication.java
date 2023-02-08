package com.makowski.fuhrpark;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.makowski.fuhrpark.entity.Car;
import com.makowski.fuhrpark.entity.Cost;
import com.makowski.fuhrpark.entity.CostType;
import com.makowski.fuhrpark.repository.CarRepository;
import com.makowski.fuhrpark.repository.CostRepository;
import com.makowski.fuhrpark.repository.CostTypeRepository;

@SpringBootApplication
public class FuhrparkApplication implements CommandLineRunner {

	@Autowired
	CarRepository carRepository;
	@Autowired
	CostRepository costRepository;
	@Autowired
	CostTypeRepository costTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(FuhrparkApplication.class, args);
	}

	@Bean
			public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}	

	@Override
	public void run(String... args) throws Exception {
		Car[] cars = new Car[]{
			new Car("A-XA544", "Audi", "A4", LocalDate.parse("2022-01-22")),
			new Car("A-DA778", "BMW", "3", LocalDate.parse("2021-04-27")),
			new Car("M-SE484", "Mercedes", "E-Klasse", LocalDate.parse("2020-03-12")),
			new Car("F-WE098", "BMW", "X3", LocalDate.parse("2022-10-11"))
		};
		
		for(int i = 0; i < cars.length; i++){
			carRepository.save(cars[i]);
		}

		CostType[] costTypes = new CostType[]{
			new CostType("Tanken"),
			new CostType("Service")
		};

		for(int i = 0; i < costTypes.length; i++){
			costTypeRepository.save(costTypes[i]);
		}

	 	Cost[] costs = new Cost[]{
			new Cost(72.58, 526, LocalDate.parse("2022-02-15"), "Tanken"),
			new Cost(70.78, 1002, LocalDate.parse("2022-02-25"), "Tanken"),
			new Cost(69.53, 1528, LocalDate.parse("2022-03-07"), "Tanken"),
			new Cost(75.59, 2088, LocalDate.parse("2022-03-17"), "Tanken"),
			new Cost(73.14, 2601, LocalDate.parse("2022-03-29"), "Tanken")
		};

		for(int i = 0; i < costs.length; i++){
			costs[i].setCar(cars[0]);
			costs[i].setCostType(costTypes[1]);
			costRepository.save(costs[i]);
		}

		Cost[] costs1 = new Cost[]{
			new Cost(62.58, 505, LocalDate.parse("2021-02-15"), "Tanken"),
			new Cost(60.78, 1012, LocalDate.parse("2021-02-25"), "Tanken"),
			new Cost(59.53, 1499, LocalDate.parse("2021-03-07"), "Tanken"),
			new Cost(65.59, 2064, LocalDate.parse("2021-03-17"), "Tanken"),
			new Cost(63.14, 2633, LocalDate.parse("2021-03-29"), "Tanken")
		};

		for(int i = 0; i < costs1.length; i++){
			costs1[i].setCar(cars[1]);
			costs1[i].setCostType(costTypes[0]);
			costRepository.save(costs1[i]);
		}
	}

}
