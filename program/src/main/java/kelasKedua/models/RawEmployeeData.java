package kelasKedua.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RawEmployeeData {
    private int employee_id;
    private Integer parent;
    private String employee_name;
    private String department;
    private Long salary;
}
