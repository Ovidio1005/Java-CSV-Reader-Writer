/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;
import java.io.*;
import java.util.Iterator;

/**
 *
 * @author ovidi
 */
public class CSVReader implements Iterable<String[]>{
    public static String separator = ",";
    private final String[] fields;
    private final BufferedReader file;
    private String line;
    private boolean hasNext;
    
    public CSVReader(String filename) throws FileNotFoundException, IOException {
        this.hasNext = true;
        
        file = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        
        fields = null;
        
        line = file.readLine();
        while("".equals(line)){
            line = file.readLine();
        }
        
        if(line == null){
            hasNext = false;
        }
    }

    public CSVReader(String filename, boolean hasMetadata) throws FileNotFoundException, IOException {
        this.hasNext = true;
        file = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        
        if(hasMetadata){
            String fieldsString = file.readLine();
            while("".equals(fieldsString)){
                fieldsString = file.readLine();
            }
            fields = fieldsString.split(separator);
        }
        else{
            fields = null;
        }
        
        line = file.readLine();
        while("".equals(line)){
            line = file.readLine();
        }
        
        if(line == null){
            hasNext = false;
        }
    }
    
    public String[] getFields() {
        return fields;
    }
    
    public String[] next() throws IOException {
        if(hasNext){
            String returnLine = line;
            
            line = file.readLine();
            while("".equals(line)){
                line = file.readLine();
            }

            if(line == null){
                hasNext = false;
            }
            
            return returnLine.split(separator);
        } else{
            return null;
        }
    }
    
    public boolean hasNext(){
        return hasNext;
    }
    
    public Iterator<String[]> iterator(){
        return new CSVIterator(this);
    }
}
