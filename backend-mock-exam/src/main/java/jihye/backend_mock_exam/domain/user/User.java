package jihye.backend_mock_exam.domain.user;

import lombok.Data;

@Data
public class User {

    private Long userId;
    private String accountId;
    private String nickname;
    private String hashedPassword;
    private String findPasswordQuestion;
    private String findPasswordAnswer;
    private String gender;
    private Integer birthYear;

    public User() {
    }

    public User(String accountId, String nickname, String hashedPassword, String findPasswordQuestion, String findPasswordAnswer, String gender, Integer birthYear) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.hashedPassword = hashedPassword;
        this.findPasswordQuestion = findPasswordQuestion;
        this.findPasswordAnswer = findPasswordAnswer;
        this.gender = gender;
        this.birthYear = birthYear;
    }
}
