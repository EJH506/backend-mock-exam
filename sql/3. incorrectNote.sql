
USE backend_mock_exam;

DROP TABLE IF EXISTS incorrect_note;

CREATE TABLE incorrect_note (
	incorrect_note_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,														-- 유저 ID
	question_id INT NOT NULL,													-- 문제 ID
	subject_id INT,																-- 해당 문제가 속한 주제 ID, 나만의 문제일 경우 NULL
	is_my_question BOOLEAN NOT NULL,											-- 나만의 문제인지 여부
	level INT NOT NULL,															-- 해당 문제의 level
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 수정일시
    UNIQUE (user_id, question_id)
)

SELECT * FROM incorrect_note;