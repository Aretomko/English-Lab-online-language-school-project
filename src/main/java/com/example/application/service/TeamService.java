package com.example.application.service;

import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.repos.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepo teamRepo;

    public TeamService(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }
    public List<Team> getAllTeams(){
        return teamRepo.findAll();
    }
    public List<String> getAllTeamsNames(){
        return teamRepo.findAll().stream().map(Team::getName).collect(Collectors.toList());
    }

    public Team getTeamByName(String username) {
        return teamRepo.findByName(username);
    }
    public String getTemName(User item) {
        if (item.getTeam() != null){
            return item.getTeam().getName();
        }
        return "Not set";
    }
    public void deleteTeamByName(String name){
        teamRepo.delete(teamRepo.findByName(name));
    }

    public void save(Team team){
        teamRepo.save(team);
    }

    public Team getTeamById(String id) {
        Long idLong = Long.parseLong(id.trim());
        Team team = teamRepo.findById(idLong).orElse(null);
        return team;
    }

}
