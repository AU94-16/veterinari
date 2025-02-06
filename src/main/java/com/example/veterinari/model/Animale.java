package com.example.veterinari.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.Date;
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

    // Sesso: solo 'M' o 'F'
    @Column
    @Pattern(regexp = "^[MF]$", message = "Sesso non valido")
    private char sesso;

    // Fotografia: non deve essere vuoto
    @Column
    private String fotografia;

    // Peso: solo numeri positivi
    @Column
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Peso non valido")
    private float peso;

    // Colore: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Colore non valido")
    private String colore;

    @Column
    private java.util.Date annoDiNascita;

    // Sterilizzato: solo 'S' o 'N'
    @Column
    @Pattern(regexp = "^[SN]$", message = "Sterilizzazione non valida")
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
            fetch = FetchType.EAGER,  //determina il momento che una select prende anche le info da libri
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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Date getAnnoDiNascita() {
        return annoDiNascita;
    }

    public void setAnnoDiNascita(Date annoDiNascita) {
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
