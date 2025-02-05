
USE backend_mock_exam;

DROP TABLE IF EXISTS exam_history;
DROP TABLE IF EXISTS history_items;

CREATE TABLE exam_history (
	history_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	user_id INT NOT NULL,														-- 유저 ID 외래키
	subject_name VARCHAR(255) NOT NULL,											-- 주제명
	level INT NOT NULL,															-- 레벨
	total_questions_count INT NOT NULL,											-- 총 문항 수
	correct_questions_count INT NOT NULL,										-- 맞힌 문항 수
	correct_rate DOUBLE NOT NULL,												-- 정답률
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
);

CREATE TABLE history_items (
	history_items_id INT AUTO_INCREMENT PRIMARY KEY,							-- 식별자
	history_id INT NOT NULL,													-- 외래키
	is_my_question boolean NOT NULL,												-- 문제가 나만의 문제인지 여부
	question_id INT NOT NULL,													-- 문제 ID
	correct_answer_id INT NOT NULL,												-- 정답 ID
	user_answer_id INT NOT NULL,												-- 사용자의 답 ID
	is_correct boolean NOT NULL,												-- 맞혔는지 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
);

SELECT * FROM exam_history;
SELECT * FROM history_items;
