package com.example.application.repos;


import com.example.application.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo  extends JpaRepository<Team, Long> {
    Team findByName(String name);
}
