package com.example.veterinari.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proprietario")
public class Proprietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nome: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome non valido")
    private String nomeProprietario;

    @Column
    private String indirizzo;

    // Città: solo lettere e spazi
    @Column
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Città non valida")
    private String citta;

    // Codice Fiscale: regex per il formato corretto
    @Column(name = "codice_fiscale")
    @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z0-9]{7}[A-Z]{1}$", message = "Codice Fiscale non valido")
    private String codiceFiscale;

    // CAP: solo 5 numeri
    @Column
    @Pattern(regexp = "^\\d{5}$", message = "CAP non valido")
    private String CAP;

    // Telefono: numeri telefonici italiani con prefisso opzionale
    @Column
    @Pattern(regexp = "^(?:\\+39)?\\d{10}$", message = "Numero di telefono non valido")
    private String telefono;

    // Email: regex per email
    @Column
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email non valida")
    private String email;

    // Sesso: solo 'M' o 'F'
    @Column
    private char sessoProprietario;

    @OneToMany
            (
                    mappedBy = "proprietario",
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



    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public char getSessoProprietario() {
        return sessoProprietario;
    }

    public void setSessoProprietario(char sessoProprietario) {
        this.sessoProprietario = sessoProprietario;
    }

    public List<Animale> getAnimali() {
        return animali;
    }

    public void setAnimali(List<Animale> animali) {
        this.animali = animali;
    }
}
