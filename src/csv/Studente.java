/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.time.LocalDate;

/**
 *
 * @author ovidi
 */
public class Studente {
    private String matricola;
    private String cognome;
    private String nome;
    private LocalDate nascita;
    private String residenza;

    public Studente(String matricola, String cognome, String nome, int giorno, int mese, int anno, String residenza) {
        this.matricola = matricola;
        this.cognome = cognome;
        this.nome = nome;
        this.nascita = LocalDate.of(anno, mese, giorno);
        this.residenza = residenza;
    }

    public Studente() {
        this.matricola = "";
        this.cognome = "";
        this.nome = "";
        this.nascita = null;
        this.residenza = "";
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascita() {
        return nascita.toString();
    }

    public void setNascita(int giorno, int mese, int anno) {
        this.nascita = LocalDate.of(anno, mese, giorno);
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }
    
    @Override
    public String toString(){
        return matricola + '\n' + cognome + ' ' + nome + '\n' + nascita.toString() + '\n' + residenza;
    }
    
    public String[] getEntry(){
        return new String[]{matricola, cognome, nome, nascita.toString(), residenza};
    }
    
    public static Studente fromEntry(String[] entry){
        int[] data = new int[3];
        String[] dataStrs = entry[3].split("-");
        for(int i = 0; i < 3; i++){
            data[i] = Integer.parseInt(dataStrs[2-i]);
        }
        
        return new Studente(entry[0], entry[1], entry[2], data[0], data[1], data[2], entry[4]);
    }
}
