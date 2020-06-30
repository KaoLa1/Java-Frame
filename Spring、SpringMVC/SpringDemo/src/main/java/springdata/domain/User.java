package springdata.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author gwh
 */
@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private Integer deptId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptid=" + deptId +
                '}';
    }
}
