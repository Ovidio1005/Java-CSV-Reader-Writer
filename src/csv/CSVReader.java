/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author Ovidio1005
 */
public class CSVReader implements Iterable<String[]>, Closeable{
    public static final char defaultSeparator = ',';
    public char separator;
    private final String[] fields;
    private final BufferedReader file;
    private String line;
    private boolean hasNext;
    
    public CSVReader(String filename) throws FileNotFoundException, IOException {
        this.separator = defaultSeparator;
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

    public CSVReader(String filename, boolean hasHeader) throws FileNotFoundException, IOException {
        this.separator = defaultSeparator;
        this.hasNext = true;
        file = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        
        if(hasHeader){
            String fieldsString = file.readLine();
            while("".equals(fieldsString)){
                fieldsString = file.readLine();
            }
            fields = parse(fieldsString);
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
            
            return parse(returnLine);
        } else{
            return null;
        }
    }
    
    public boolean hasNext(){
        return hasNext;
    }
    
    @Override
    public Iterator<String[]> iterator(){
        return new CSVIterator(this);
    }
    
    private String[] parse(String entry){
        ArrayList<String> entryList = new ArrayList<>();
        
        char lastChar = ' ';
        boolean inQuotes = false;
        String field = "";
        
        for(int i = 0; i < entry.length(); i++){
            if(!inQuotes){
                if(entry.charAt(i) == '"'){
                    inQuotes = true;
                }
                else if(entry.charAt(i) == separator){
                    entryList.add(field);
                    field = "";
                }
                else{
                    lastChar = entry.charAt(i);
                    field += lastChar;
                }
            }
            else{
                if(lastChar == '"'){
                    if(entry.charAt(i) == '"'){
                        lastChar = ' ';
                        entry += '"';
                    }
                    else{
                        inQuotes = false;
                        entryList.add(field);
                        field = "";
                        lastChar = ' ';
                    }
                }
                else{
                    lastChar = entry.charAt(i);
                    
                    if(lastChar != '"'){
                        field += lastChar;
                    }
                }
            }
        }
        entryList.add(field);
        
        return entryList.toArray(new String[entryList.size()]);
    }
    
    @Override
    public void close() throws IOException{
        file.close();
    }
}
