package kelasKedua.services;

import java.util.ArrayList;
import java.util.List;

import hu.webarticum.treeprinter.TreeNode;
import hu.webarticum.treeprinter.printer.listing.ListingTreePrinter;
import kelasKedua.models.EmployeeTreeNode;
import kelasKedua.FindNodeThread;
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

    public void addNode(int parentEmployeeId, EmployeeTreeNode newNode) {
        if (this.root == null || parentEmployeeId == -1) {
            this.root = newNode;
            this.size++;
        } else {
            EmployeeTreeNode parentNode;
            try {
                parentNode = parallelFindNode(parentEmployeeId, this.root);
            } catch(InterruptedException e) {
                parentNode = null;
            }
            if (parentNode != null) {
                parentNode.getEmployeeChildren().add(newNode);
                this.size++;
            } else {
                System.out.println("Parent not found");
            }
        }
    }

    public EmployeeTreeNode parallelFindNode(int employeeId, EmployeeTreeNode node) throws InterruptedException {
        if (node.getEmployeeId() == employeeId) {
            return node;
        } else {
            if (node.children().size() == 0) {
                return null;
            } else {
                ArrayList<FindNodeThread> threads = new ArrayList<FindNodeThread>();
                List<TreeNode> childrens = node.children();
                for (int i = 0; i < childrens.size(); i++) {
                    FindNodeThread newThread = new FindNodeThread(employeeId, node);
                    newThread.start();
                    System.out.println("Started thread " + i);
                    threads.add(newThread);
                }
                for (FindNodeThread thread : threads) {
                    thread.join();
                }
                for (int i = 0; i < threads.size(); i++) {
                    FindNodeThread thread = threads.get(i);
                    EmployeeTreeNode result = thread.getResult();
                    if (result != null) {
                        System.out.println("Thread " + i + " finished with result");
                        return result;
                    }
                }
                return null;
            }
        }
    }

    public static EmployeeTreeNode findNode(int employeeId, EmployeeTreeNode node) {
        if (node.getEmployeeId() == employeeId) {
            return node;
        } else {
            for (TreeNode child : node.children()) {
                EmployeeTreeNode result = findNode(employeeId, (EmployeeTreeNode) child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public void printTree() {
        new ListingTreePrinter().print(root);
    }

}
