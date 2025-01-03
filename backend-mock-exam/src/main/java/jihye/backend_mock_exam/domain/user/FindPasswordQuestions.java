package jihye.backend_mock_exam.domain.user;

public enum FindPasswordQuestions {

    MY_NICKNAME("어렸을 때 나의 별명은?"),
    MY_TREASURE("나의 보물 제 1호는?"),
    MEMORABLE_TEACHER("가장 기억에 남는 선생님 성함은?"),
    RESPECT_PERSON("내가 가장 존경하는 인물은?"),
    BEST_FRIEND("가장 친한 친구의 이름은?"),
    SPECIAL_DATE("추억하고 싶은 날짜가 있다면?"),
    MY_MOTTO("나의 인생 좌우명은?"),
    ELEM_PARTNER("초등학교 때 기억에 남는 짝꿍 이름은?"),
    FAVORITE_CHARACTER("내가 좋아하는 캐릭터 이름은?")
    ;

    private final String questionDetail;

    FindPasswordQuestions(String question) {
        this.questionDetail = question;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }
}
