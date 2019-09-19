/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.*;
import java.util.zip.DataFormatException;

/**
 *
 * @author ovidi
 */
public class CSVWriter {
    public static String separator = ",";
    private final String[] fields;
    private final PrintWriter file;
    
    public CSVWriter(String filename) throws FileNotFoundException{    //for new file without fields name and number
        file = new PrintWriter(new FileOutputStream(filename), true);
        this.fields = null;
    }
    
    public CSVWriter(String filename, String[] fields) throws FileNotFoundException{    //for new file with fields name and number
        file = new PrintWriter(new FileOutputStream(filename), true);
        this.fields = fields;
        
        for(int i = 0; i < fields.length - 1; i++){
            file.print(fields[i] + separator);
        }
        file.print(fields[fields.length - 1]);
        file.println();
    }
    
    public CSVWriter(String filename, int fieldsAmount) throws FileNotFoundException{    //for new file with just fields number
        file = new PrintWriter(new FileOutputStream(filename), true);
        
        fields = new String[fieldsAmount];
        for(int i = 0; i < fieldsAmount; i++){
            fields[i] = "field" + i;
        }
    }
    
    public CSVWriter(String filename, boolean useMetadata) throws FileNotFoundException, IOException{  //for appending
        if(useMetadata){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            fields = reader.readLine().split(separator);
            reader.close();
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
        
        file.println(String.join(separator, entry));
    }
}
