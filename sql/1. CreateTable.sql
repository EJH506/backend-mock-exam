CREATE DATABASE backend_mock_exam;

USE backend_mock_exam;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS levels;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS question_unit_setting;
DROP TABLE IF EXISTS exam_history;
DROP TABLE IF EXISTS history_items;

CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,										-- 식별자
	account_id VARCHAR(255) NOT NULL,											-- 아이디
	nickname VARCHAR(255) NOT NULL,												-- 닉네임
	hashed_password VARCHAR(255) NOT NULL,										-- 암호화된 비밀번호
	find_password_question VARCHAR(255) NOT NULL,								-- 비밀번호 찾기 질문
	find_password_answer VARCHAR(255) NOT NULL,									-- 비밀번호 찾기 질문에 대한 답변
	gender ENUM('male', 'female'),												-- 성별
	birth_year INT,																-- 출생년도
	roles VARCHAR(255) NOT NULL,												-- 역할 (ROLE_USER, ROLE_ADMIN)
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 수정일시
	UNIQUE (account_id)															-- 아이디의 유일성 보장
);

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

CREATE TABLE question_unit_setting (
	question_unit_id INT AUTO_INCREMENT PRIMARY KEY,							-- 식별자
	question_unit INT NOT NULL,													-- 관리자가 설정한 문항 분류 단위
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
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

SELECT * FROM users;
SELECT * FROM subjects;
SELECT * FROM levels;
SELECT * FROM questions;
SELECT * FROM answers;
SELECT * FROM question_unit_setting;
SELECT * FROM exam_history;
SELECT * FROM history_items;

SELECT * FROM exam_history WHERE history_id = 1;
SELECT question_id FROM history_items WHERE history_id = 1
/*
SELECT * FROM options
WHERE question_id = ?  -- 특정 문제 ID에 대해
ORDER BY RAND()        -- 보기 순서 랜덤 정렬
LIMIT 4;               -- 최대 4개 보기 출력
*/