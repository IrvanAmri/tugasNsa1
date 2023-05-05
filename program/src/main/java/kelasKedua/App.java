package kelasKedua;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kelasKedua.models.RawEmployeeData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws StreamReadException, DatabindException, IOException
    {
        //access json file from directory
        File json = new File("D:/kuliah(Amri)/Sem 6/Algoritma dan Struktur Data/Periode 2/kode/dataset/source/MOCK_DATA.json");
		
        //initialize object mapper from jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        
        //read json file and map it to array of RawEmployeeData class
        /* 
         * rawEmployeeData is object that store raw data from json file
         * next step is create A Tree from rawEmployeeDatas using EmployeeTree class as node
         */
        RawEmployeeData[] rawEmployeeDatas = objectMapper.readValue(json, RawEmployeeData[].class);
        
        //test print
        System.out.println(rawEmployeeDatas[0].toString());
    }
}
