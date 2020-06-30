package ioc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gwh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String age;

}
