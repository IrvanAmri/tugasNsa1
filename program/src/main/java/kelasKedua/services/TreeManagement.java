package kelasKedua.services;

import hu.webarticum.treeprinter.TreeNode;
import hu.webarticum.treeprinter.printer.listing.ListingTreePrinter;
import kelasKedua.models.EmployeeTreeNode;
import lombok.Getter;

@Getter
public class TreeManagement {
    private EmployeeTreeNode root;
    private int size;

    public TreeManagement(EmployeeTreeNode root) {
        this.root = root;
        this.size = 1;
    }

    public TreeManagement() {
        this.root = null;
        this.size = 0;
    }

    public void addNode(int parentEmployeeId, EmployeeTreeNode newNode){
        if(this.root == null){
            this.root = newNode;
            this.size++;
        }
        else{
            EmployeeTreeNode parentNode = findNode(parentEmployeeId, this.root);
            if(parentNode != null){
                parentNode.getEmployeeChildren().add(newNode);
                this.size++;
            }
            else{
                System.out.println("Parent not found");
            }
        }
    }

    private EmployeeTreeNode findNode(int employeeId, EmployeeTreeNode node){
        if(node.getEmployeeId() == employeeId){
            return node;
        }
        else{
            for(TreeNode child : node.children()){
                EmployeeTreeNode result = findNode(employeeId, (EmployeeTreeNode) child);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }

    public void printTree(){
        new ListingTreePrinter().print(root);
    }

}
