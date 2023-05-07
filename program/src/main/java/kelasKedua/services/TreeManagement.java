package kelasKedua.services;

import hu.webarticum.treeprinter.TreeNode;
import hu.webarticum.treeprinter.printer.listing.ListingTreePrinter;
import lombok.Getter;

@Getter
public class TreeManagement {
    private TreeNode root;
    private int size;

    public TreeManagement(TreeNode root) {
        this.root = root;
        this.size = 1;
    }

    public TreeManagement() {
        this.root = null;
        this.size = 0;
    }

    public void addNode(int parentEmployeeId, TreeNode newNode){
        if(this.root == null){
            this.root = newNode;
            this.size++;
        }
        else{
            TreeNode parentNode = findNode(parentEmployeeId, this.root);
            if(parentNode != null){
                parentNode.children().add(newNode);
                this.size++;
            }
            else{
                System.out.println("Parent not found");
            }
        }
    }

    private TreeNode findNode(int employeeId, TreeNode node){
        if(node.insets().top() == employeeId){
            return node;
        }
        else{
            for(TreeNode child : node.children()){
                TreeNode result = findNode(employeeId, child);
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
