package jihye.backend_mock_exam.domain.user;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String accountId;
    private String nickname;
    private String password;
    private String findPasswordQuestion;
    private String findPasswordAnswer;
    private String gender;
    private String birthYear;

    public User() {
    }

    public User(String accountId, String nickname, String password, String findPasswordQuestion, String findPasswordAnswer, String gender, String birthYear) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.findPasswordQuestion = findPasswordQuestion;
        this.findPasswordAnswer = findPasswordAnswer;
        this.gender = gender;
        this.birthYear = birthYear;
    }
}
