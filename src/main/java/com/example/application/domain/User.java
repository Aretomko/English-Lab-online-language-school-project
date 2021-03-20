package com.example.application.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "userok")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String realN;
    private String surname;
    private String password;

    private String email;
    private String activationCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<AnswerGrammar> answersGrammar;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<AnswerVocabulary> answersVocabulary;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<AnswerReading> answersReading;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<AnswerListening> answersListening;

    public User(String name, String surname, String email, Team team){
        this.realN = name;
        this.surname = surname;
        this.username = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0,6);
        this.password = UUID.randomUUID().toString().substring(0, 8);
        this.email = email;
        this.team = team;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getRealN() {
        return realN;
    }

    public void setRealN(String realN) {
        this.realN = realN;
    }

    public Set<AnswerGrammar> getAnswersGrammar() {
        return answersGrammar;
    }

    public void setAnswersGrammar(Set<AnswerGrammar> answersGrammar) {
        this.answersGrammar = answersGrammar;
    }

    public Set<AnswerVocabulary> getAnswersVocabulary() {
        return answersVocabulary;
    }

    public void setAnswersVocabulary(Set<AnswerVocabulary> answersVocabulary) {
        this.answersVocabulary = answersVocabulary;
    }

    public Set<AnswerReading> getAnswersReading() {
        return answersReading;
    }

    public void setAnswersReading(Set<AnswerReading> answersReading) {
        this.answersReading = answersReading;
    }

    public Set<AnswerListening> getAnswersListening() {
        return answersListening;
    }

    public void setAnswersListening(Set<AnswerListening> answersListening) {
        this.answersListening = answersListening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
}
