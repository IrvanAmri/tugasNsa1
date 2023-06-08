package kelasKedua;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import kelasKedua.services.TreeManagement;
import kelasKedua.services.TreeRawProcessing;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws StreamReadException, DatabindException, IOException
    {
		String path = "D:/Development Projects/Project ASD/Final/tugasNsa1/dataset/source/MOCK_DATA.json";
        TreeManagement treeManagement = TreeRawProcessing.rawEmployeeDataToEmployeeTreeNode(path);
        treeManagement.printTree();
    }
}
