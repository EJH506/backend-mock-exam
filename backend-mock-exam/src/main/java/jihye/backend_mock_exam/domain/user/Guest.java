package jihye.backend_mock_exam.domain.user;

import lombok.Data;

@Data
public class Guest {

    private static final String GUEST = "guest";
    private String nickname;

    public Guest() {
    }

    public Guest(String nickname) {
        this.nickname = nickname;
    }
}
