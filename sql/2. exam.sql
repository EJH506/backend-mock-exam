
USE backend_mock_exam;

DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS levels;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS exam_history;
DROP TABLE IF EXISTS history_items;

CREATE TABLE subjects (
	subject_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_name VARCHAR(255) NOT NULL,											-- 주제명
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 수정일시
	UNIQUE (subject_name)
)

CREATE TABLE levels (
	level_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_id INT NOT NULL,													-- 주제 ID 
	level INT NOT NULL,															-- 레벨
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

CREATE TABLE questions (
	question_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_id INT NOT NULL,													-- 주제 ID
	level INT NOT NULL,															-- 레벨 ID
	question_text TEXT NOT NULL,												-- 문제 내용
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

CREATE TABLE answers (
	answer_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	question_id INT NOT NULL,													-- 질문 ID
	answer_text TEXT NOT NULL,													-- 보기 내용
	is_correct BOOLEAN,															-- 정답 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

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
)

CREATE TABLE history_items (
	history_items_id INT AUTO_INCREMENT PRIMARY KEY,							-- 식별자
	history_id INT NOT NULL,													-- 외래키
	question_id INT NOT NULL,													-- 문제 ID
	correct_answer_id INT NOT NULL,												-- 정답 ID
	user_answer_id INT NOT NULL,												-- 사용자의 답 ID
	is_correct boolean NOT NULL,												-- 맞혔는지 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

SELECT * FROM levels where subject_id = (select subject_id from subjects where subject_name = "알고리즘");

SELECT l.*
FROM levels l
JOIN subjects s ON l.subject_id = s.subject_id
WHERE s.subject_name = '알고리즘';


SELECT * FROM subjects;
SELECT * FROM levels;
SELECT * FROM questions;
SELECT * FROM answers;
SELECT * FROM exam_history;
SELECT * FROM history_items;