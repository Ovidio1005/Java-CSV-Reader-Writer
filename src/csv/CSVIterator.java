/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.IOException;
import java.util.Iterator;
/**
 *
 * @author ovidi
 */
public class CSVIterator implements Iterator<String[]>{
    private CSVReader csv;
    
    public CSVIterator(CSVReader csv){
        this.csv = csv;
    }
    
    @Override
    public String[] next(){
        try{
            return csv.next();
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean hasNext(){
        return csv.hasNext();
    }
}
