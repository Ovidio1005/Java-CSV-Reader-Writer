/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author ovidi
 */
public class Classe {
    private final ArrayList<Studente> studenti;

    public Classe() {
        studenti = new ArrayList<>();
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }
    
    public void addStudente(Studente s){
        studenti.add(s);
    }
    
    public void removeStudente(String matricola){
        for(Studente s: studenti){
            if(s.getMatricola().equals(matricola)){
                studenti.remove(s);
                break;
            }
        }
    }
    
    @Override
    public String toString(){
        String s = "";
        
        if(studenti.size() == 0){
            return s;
        }
        
        for(int i = 0; i < studenti.size() - 1; i++){
            s += studenti.get(i).toString() + "\n\n";
        }
        s += studenti.get(studenti.size() - 1).toString();
        
        return s;
    }
    
    public static void toCSV(Classe c, String filename) throws Exception, FileNotFoundException{
        CSVWriter csv = new CSVWriter(filename, new String[]{"matricola", "cognome", "nome", "nascita", "residenza"});
        
        for(Studente s: c.getStudenti()){
            csv.add(s.getEntry());
        }
    }
    
    public static Classe fromCSV(String filename)throws FileNotFoundException, IOException{
        Classe c = new Classe();
        
        CSVReader csv = new CSVReader(filename, true);
        
        for(String[] entry: csv){
            c.addStudente(Studente.fromEntry(entry));
        }
        
        return c;
    }
}
