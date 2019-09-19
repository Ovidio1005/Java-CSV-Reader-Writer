/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

/**
 *
 * @author ovidi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Classe c1 = new Classe();
        
        c1.addStudente(new Studente("A1", "Preet", "Singh", 1, 2, 2001, "Brescia"));
        c1.addStudente(new Studente("B2", "Asare", "Jacub", 3, 4, 2001, "Brescia"));
        c1.addStudente(new Studente("C3", "Bena", "Giovanni", 5, 6, 2001, "Brescia"));
        c1.addStudente(new Studente("D4", "Forsetti", "Nicola", 7, 8, 2001, "Brescia"));
        c1.addStudente(new Studente("E5", "Favalli", "Lorenzo", 9, 10, 2001, "Brescia"));
        
        try{
            Classe.toCSV(c1, "classe.csv");
        } catch (Exception e){}
        
        c1.removeStudente("D4");
        
        Classe c2 = null;
        try{
            c2 = Classe.fromCSV("classe.csv");
        } catch (Exception e){}
        
        System.out.println(c1.toString());
        System.out.println("\n-------------------------\n");
        System.out.println(c2.toString());
    }
    
}
