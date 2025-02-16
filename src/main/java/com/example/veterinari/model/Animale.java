package com.example.veterinari.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animale")
public class Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nome: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome non valido")
    private String nome;

    // Specie: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Specie non valida")
    private String specie;

    // Razza: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Razza non valida")
    private String razza;

    // Sesso: solo 'M' o 'F' - gestione: select
    @Column
    private char sesso;

    @Column
    private String fotografia;

    // Peso: solo numeri positivi - con o senza . - nullo
    @Column
    private Float peso;

    // Colore: solo lettere e spazi - permette stringhe vuote
    @Column
    @Pattern(regexp = "^([A-Za-zÀ-ÿ ]+)?$", message = "Colore non valido")
    private String colore;

    //Anno di Nascita: gestione:.html
    @Column(name="anno_di_nascita")
    private Year annoDiNascita;

    // Sterilizzato: solo 'S' o 'N'- gestione: select
    @Column
    private char sterilizzato;

    @Column
    private String allergie;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_veterinario", referencedColumnName = "id")
    private Veterinario veterinario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_proprietario", referencedColumnName = "id")
    private Proprietario proprietario;

    @OneToMany(
            mappedBy = "animale",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Storico> storico = new ArrayList<>();;

    public List<Storico> getStorico() {
        return storico;
    }

    public void setStorico(List<Storico> storico) {
        this.storico = storico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getRazza() {
        return razza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Year getAnnoDiNascita() {
        return annoDiNascita;
    }

    public void setAnnoDiNascita(Year annoDiNascita) {
        this.annoDiNascita = annoDiNascita;
    }

    public char getSterilizzato() {
        return sterilizzato;
    }

    public void setSterilizzato(char sterilizzato) {
        this.sterilizzato = sterilizzato;
    }

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
