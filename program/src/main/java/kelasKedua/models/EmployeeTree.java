package kelasKedua.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTree {
    private int employeeId;
    private EmployeeTree parent;
    private EmployeeTree[] children;
    private String employeeName;
    private String departement;
    private Long salary;
}
