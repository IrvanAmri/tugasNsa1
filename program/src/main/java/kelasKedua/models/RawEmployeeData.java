package kelasKedua.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RawEmployeeData {
    private int employee_id;
    private int parent;
    private String employee_name;
    private String department;
    private Long salary;
}
