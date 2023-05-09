package kelasKedua.models;

import java.util.ArrayList;
import java.util.List;

import hu.webarticum.treeprinter.Insets;
import hu.webarticum.treeprinter.TreeNode;
import hu.webarticum.treeprinter.text.ConsoleText;
import hu.webarticum.treeprinter.text.PlainConsoleText;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeTreeNode implements TreeNode {
    private int employeeId;
    private List<EmployeeTreeNode> employeeChildren;
    private String employeeName;
    private String departement;
    private Long salary;
    private Insets insets;

    public EmployeeTreeNode(
        int employeeId,
        String employeeName,
        String departement, 
        Long salary
        ) 
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departement = departement;
        this.employeeChildren = new ArrayList<EmployeeTreeNode>();
        this.salary = salary;
        this.insets = new Insets(employeeId);
    }

    @Override
    public List<TreeNode> children() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (EmployeeTreeNode eTreeNode : employeeChildren) {
            treeNodes.add(eTreeNode);
        }
        return treeNodes;
    }

    public List<EmployeeTreeNode> getEmployeeChildren() {
        return employeeChildren;
    }

    @Override
    public ConsoleText content() {
        return new PlainConsoleText(this.employeeName);
    }

    @Override
    public Insets insets() {
        return this.insets;
    }
}
