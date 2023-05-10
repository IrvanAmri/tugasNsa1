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
public class RawProjectData {
    private int project_id;
    private String contact_person;
    private String company_name;
    private String deadline;
    private Long contract_price;
}
