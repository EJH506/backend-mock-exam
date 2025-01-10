CREATE DATABASE backend_mock_exam;

USE backend_mock_exam;

DROP TABLE IF EXISTS users;

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

SELECT * FROM users;

update users
set nickname = "바보", gender = null, birth_year = 1999
where user_id = #{userId}