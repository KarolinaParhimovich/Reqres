package restassured;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String email;
    private String password;
    private String name;
    private String job;
}
