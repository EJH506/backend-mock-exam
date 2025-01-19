
USE backend_mock_exam;

DROP TABLE IF EXISTS incorrect_note;

CREATE TABLE incorrect_note (
	incorrect_note_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,														-- 유저 ID
	question_id INT NOT NULL,													-- 문제 ID
	subject_id INT NOT NULL,													-- 해당 문제가 속한 주제 ID
	level INT NOT NULL,															-- 해당 문제의 level
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 수정일시
    UNIQUE (user_id, question_id)
)

INSERT INTO incorrect_note (user_id, question_id, subject_id, level) VALUES
(1, 1, 1, 1),(1, 4, 1, 1),(1, 61, 4, 3),(1, 43, 3, 3),(1, 11, 1, 3),(1, 125, 8, 2),(1, 134, 8, 2),(1, 65, 4, 1),
(1, 97, 6, 3),(1, 74, 5, 2),(1, 25, 2, 2),(1, 78, 5, 3),(1, 3, 1, 1),(1, 6, 1, 2),(1, 24, 2, 2),(1, 108, 7, 2);
