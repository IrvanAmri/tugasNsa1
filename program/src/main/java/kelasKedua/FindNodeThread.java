package kelasKedua;

import hu.webarticum.treeprinter.TreeNode;
import kelasKedua.models.EmployeeTreeNode;
import kelasKedua.services.TreeManagement;

public class FindNodeThread extends Thread {

    TreeManagement treeManagement;
    int employeeId;
    EmployeeTreeNode node;
    EmployeeTreeNode result;

    public FindNodeThread(int employeeId, EmployeeTreeNode node) {
        // this.treeManagement = treeManagement;
        this.employeeId = employeeId;
        this.node = node;
    }

    public EmployeeTreeNode getResult() {
        return result;
    }

    @Override
    public synchronized void run() {
        result = TreeManagement.findNode(employeeId, node);
        
    }

}
