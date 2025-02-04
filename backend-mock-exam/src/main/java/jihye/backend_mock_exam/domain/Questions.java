package jihye.backend_mock_exam.domain;

import lombok.Data;

public interface Questions {

    Long getQuestionId();
    int getLevel();
    Long getSubjectId();
    String getSubjectName();
    String getQuestionText();
    boolean isDeleted();
    String getCreatedAt();
    String getUpdateAt();

    Long getUserId();
}
