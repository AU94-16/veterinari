package com.example.veterinari.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "storico")
public class Storico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_animale", referencedColumnName = "id")
    private Animale animale;

    // Tipo: solo lettere e spazi
    @Column
    /*@Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Tipo non valido")*/
    private String tipo;

    // Nome: solo lettere e spazi
    @Column
   /* @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome non valido")*/
    private String nome;

    @Column
    private String dosaggioDettaglio;

    @Column
    private LocalDateTime dataOraPrestazione;

    @Column
    private LocalDateTime dataOraRichiamo;

    @Column
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animale getAnimale() {
        return animale;
    }

    public void setAnimale(Animale animale) {
        this.animale = animale;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosaggioDettaglio() {
        return dosaggioDettaglio;
    }

    public void setDosaggioDettaglio(String dosaggioDettaglio) {
        this.dosaggioDettaglio = dosaggioDettaglio;
    }

    public LocalDateTime getDataOraPrestazione() {
        return dataOraPrestazione;
    }

    public void setDataOraPrestazione(LocalDateTime dataOraPrestazione) {
        this.dataOraPrestazione = dataOraPrestazione;
    }

    public LocalDateTime getDataOraRichiamo() {
        return dataOraRichiamo;
    }

    public void setDataOraRichiamo(LocalDateTime dataOraRichiamo) {
        this.dataOraRichiamo = dataOraRichiamo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
