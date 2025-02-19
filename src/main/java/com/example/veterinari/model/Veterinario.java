package com.example.veterinari.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nome: solo lettere e spazi
    @Column
    @Pattern(regexp = "[a-zA-Z\\séèìòù]{1,50}", message = "Nome non valido")
    private String nome;

    // Cognome: solo lettere e spazi
    @Column
    @Pattern(regexp = "[a-zA-Z\\séèìòù]{1,50}", message = "Cognome non valido")
    private String cognome;

    // Email: regex per email
    @Column
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email non valida")
    private String email;

    // Password: regex per password sicura
    @Column
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}", message = "Password debole")
    private String password;

    // Sesso: gestito da select
    @Column
    private String sesso;

    // Telefono: numeri telefonici italiani con prefisso opzionale
    @Column
    @Pattern(regexp = "^(?:\\+39)?\\d{10}$|^$", message = "Numero di telefono non valido")
    private String telefono;

    // Città: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]*$", message = "Città non valida")
    private String citta;

    // Numero di Iscrizione Albo: solo alfanumerico
    @Column
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Numero di iscrizione all'albo non valido")
    private String numeroIscrizioneAlbo;

    @Column
    private String fotoProfilo;

    @Column
    private LocalDate dataRegistrazione;

    @OneToMany
            (
                    mappedBy = "veterinario",
                    cascade = CascadeType.REMOVE,
                    fetch = FetchType.EAGER,
                    orphanRemoval = true
            )
    private List<Animale> animali = new ArrayList<>();

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNumeroIscrizioneAlbo() {
        return numeroIscrizioneAlbo;
    }

    public void setNumeroIscrizioneAlbo(String numeroIscrizioneAlbo) {
        this.numeroIscrizioneAlbo = numeroIscrizioneAlbo;
    }

    public String getFotoProfilo() {
        return fotoProfilo;
    }

    public void setFotoProfilo(String fotoProfilo) {
        this.fotoProfilo = fotoProfilo;
    }

    public List<Animale> getAnimali() {
        return animali;
    }

    public void setAnimali(List<Animale> animali) {
        this.animali = animali;
    }

    public LocalDate getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }
}