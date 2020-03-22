package training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @author: Golden
 * @date: 2020/3/15
 */

@Data
@Builder
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private int age;
}
