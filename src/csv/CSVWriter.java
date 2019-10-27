/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.DataFormatException;

/**
 *
 * @author Ovidio1005
 */
public class CSVWriter implements Closeable{
    public static char defaultSeparator = ',';
    public char separator;
    private final String[] fields;
    private final PrintWriter file;
    
    public CSVWriter(String filename) throws FileNotFoundException{    //for new file without fields name and number
        this.separator = defaultSeparator;
        file = new PrintWriter(new FileOutputStream(filename), true);
        this.fields = null;
    }
    
    public CSVWriter(String filename, String[] fields) throws FileNotFoundException{    //for new file with fields name and number
        this.separator = defaultSeparator;
        file = new PrintWriter(new FileOutputStream(filename), true);
        this.fields = fields;
        
        for(int i = 0; i < fields.length; i++){
            if(fields[i].contains("\"") || fields[i].contains("" + separator)){
                fields[i] = "\"" + fields[i].replace("\"", "\"\"") + "\"";
            }
        }
        
        file.println(String.join("" + separator, fields));
    }
    
    public CSVWriter(String filename, int fieldsAmount) throws FileNotFoundException{    //for new file with just fields number
        this.separator = defaultSeparator;
        file = new PrintWriter(new FileOutputStream(filename), true);
        
        fields = new String[fieldsAmount];
        for(int i = 0; i < fieldsAmount; i++){
            fields[i] = "field" + i;
        }
    }
    
    public CSVWriter(String filename, boolean useHeader) throws FileNotFoundException, IOException{  //for appending
        this.separator = defaultSeparator;
        if(useHeader){
            try (CSVReader reader = new CSVReader(filename, true)) {
                fields = reader.getFields();
            }
        }
        else{
            fields = null;
        }
        
        file = new PrintWriter(new FileOutputStream(filename, true), true);
    }

    public String[] getFields() {
        return fields;
    }
    
    public int fieldsAmount(){
        if(fields == null){
            return 0;
        }
        return fields.length;
    }
    
    public void add(String[] entry) throws DataFormatException{
        if(fields != null){
            if(entry.length != fields.length){
                throw new DataFormatException("The number of fields in the entry (" + entry.length + ") doesn't match the number of fields in the metadata (" + fields.length + ")");
            }
        }
        
        for(int i = 0; i < entry.length; i++){
            if(entry[i].contains("\"") || entry[i].contains("" + separator)){
                entry[i] = "\"" + entry[i].replace("\"", "\"\"") + "\"";
            }
        }
        
        file.println(String.join("" + separator, entry));
    }
    
    @Override
    public void close(){
        file.close();
    }
}
