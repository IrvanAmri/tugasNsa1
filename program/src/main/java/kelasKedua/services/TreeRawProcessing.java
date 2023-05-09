package kelasKedua.services;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kelasKedua.models.EmployeeTreeNode;
import kelasKedua.models.RawEmployeeData;

// digunakan untuk memproses data mentah
public final class TreeRawProcessing {
    public static TreeManagement rawEmployeeDataToEmployeeTreeNode(String path) throws StreamReadException, DatabindException, IOException{
        //access json file from directory
        File json = new File(path);

        //initialize object mapper from jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        
        //read json file and map it to array of RawEmployeeData class
        /* 
         * rawEmployeeData is object that store raw data from json file
         */
        RawEmployeeData[] rawEmployeeDatas = objectMapper.readValue(json, RawEmployeeData[].class);

        TreeManagement treeManagement = new TreeManagement();
        for (RawEmployeeData rawEmployeeData : rawEmployeeDatas) {
            EmployeeTreeNode employeeTreeNode = new EmployeeTreeNode(
                rawEmployeeData.getEmployee_id(),
                rawEmployeeData.getEmployee_name(),
                rawEmployeeData.getDepartment(),
                rawEmployeeData.getSalary()
            );
            treeManagement.addNode(rawEmployeeData.getParent(), employeeTreeNode);
        }
        return treeManagement;
    }
}
