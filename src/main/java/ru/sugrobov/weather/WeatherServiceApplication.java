package ru.sugrobov.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.sugrobov.weather.repository"})
@EntityScan(basePackages = {"ru.sugrobov.weather.model"})
@EnableTransactionManagement
public class WeatherServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherServiceApplication.class, args);
  }
}
