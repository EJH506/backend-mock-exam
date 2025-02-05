
USE backend_mock_exam;

DROP TABLE IF EXISTS my_questions_levels;
DROP TABLE IF EXISTS my_questions;
DROP TABLE IF EXISTS my_questions_answers;

SELECT * FROM my_questions_levels;
SELECT * FROM my_questions;
SELECT * FROM my_questions_answers;

CREATE TABLE my_questions_levels (
	level_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	user_id INT NOT NULL,														-- 유저 ID  (외래키)
	level INT NOT NULL,															-- 레벨
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

INSERT INTO my_questions_levels (user_id, level) VALUES 
(1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (3, 1);

CREATE TABLE my_questions (
	question_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	user_id INT NOT NULL,														-- 유저 ID (외래키)
	level INT NOT NULL,															-- 레벨 ID
	question_text TEXT NOT NULL,												-- 문제 내용
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

INSERT INTO my_questions (user_id, level, question_text) VALUES
(1, 1, 'user 1의 lv.1 첫번째 문제입니다'),(1, 2, 'user 1의 lv.2 첫번째 문제입니다'),(1, 3, 'user 1의 lv.3 첫번째 문제입니다'),
(1, 1, 'user 1의 lv.1 두번째 문제입니다'),(1, 2, 'user 1의 lv.2 두번째 문제입니다'),(1, 3, 'user 1의 lv.3 두번째 문제입니다'),
(1, 1, 'user 1의 lv.1 세번째 문제입니다'),(1, 2, 'user 1의 lv.2 세번째 문제입니다'),
(2, 1, 'user 2의 lv.1 첫번째 문제입니다'),(2, 2, 'user 2의 lv.2 첫번째 문제입니다'),
(2, 1, 'user 2의 lv.1 두번째 문제입니다'),(2, 2, 'user 2의 lv.2 두번째 문제입니다'),
(2, 1, 'user 2의 lv.1 세번째 문제입니다'),
(3, 1, 'user 3의 lv.1 첫번째 문제입니다');

CREATE TABLE my_questions_answers (
	answer_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	my_question_id INT NOT NULL,												-- 질문 ID
	answer_text TEXT NOT NULL,													-- 보기 내용
	is_correct BOOLEAN,															-- 정답 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

INSERT INTO my_questions_answers (my_question_id, answer_text, is_correct) VALUES
    (1, 'user 1의 lv.1 첫번째 문제의 1번째 보기입니다.', true),
    (1, 'user 1의 lv.1 첫번째 문제의 2번째 보기입니다.', false),
    (1, 'user 1의 lv.1 첫번째 문제의 3번째 보기입니다.', false),
    (1, 'user 1의 lv.1 첫번째 문제의 4번째 보기입니다.', false),
    (2, 'user 1의 lv.2 첫번째 문제의 1번째 보기입니다.', true),
    (2, 'user 1의 lv.2 첫번째 문제의 2번째 보기입니다.', false),
    (2, 'user 1의 lv.2 첫번째 문제의 3번째 보기입니다.', false),
    (2, 'user 1의 lv.2 첫번째 문제의 4번째 보기입니다.', false),
    (3, 'user 1의 lv.3 첫번째 문제의 1번째 보기입니다.', true),
    (3, 'user 1의 lv.3 첫번째 문제의 2번째 보기입니다.', false),
    (3, 'user 1의 lv.3 첫번째 문제의 3번째 보기입니다.', false),
    (3, 'user 1의 lv.3 첫번째 문제의 4번째 보기입니다.', false),
    (4, 'user 1의 lv.1 두번째 문제의 1번째 보기입니다.', true),
    (4, 'user 1의 lv.1 두번째 문제의 2번째 보기입니다.', false),
    (4, 'user 1의 lv.1 두번째 문제의 3번째 보기입니다.', false),
    (4, 'user 1의 lv.1 두번째 문제의 4번째 보기입니다.', false),
    (5, 'user 1의 lv.2 두번째 문제의 1번째 보기입니다.', true),
    (5, 'user 1의 lv.2 두번째 문제의 2번째 보기입니다.', false),
    (5, 'user 1의 lv.2 두번째 문제의 3번째 보기입니다.', false),
    (5, 'user 1의 lv.2 두번째 문제의 4번째 보기입니다.', false),
    (6, 'user 1의 lv.3 두번째 문제의 1번째 보기입니다.', true),
    (6, 'user 1의 lv.3 두번째 문제의 2번째 보기입니다.', false),
    (6, 'user 1의 lv.3 두번째 문제의 3번째 보기입니다.', false),
    (6, 'user 1의 lv.3 두번째 문제의 4번째 보기입니다.', false),
    (7, 'user 1의 lv.1 세번째 문제의 1번째 보기입니다.', true),
    (7, 'user 1의 lv.1 세번째 문제의 2번째 보기입니다.', false),
    (7, 'user 1의 lv.1 세번째 문제의 3번째 보기입니다.', false),
    (7, 'user 1의 lv.1 세번째 문제의 4번째 보기입니다.', false),
    (8, 'user 1의 lv.2 세번째 문제의 1번째 보기입니다.', true),
    (8, 'user 1의 lv.2 세번째 문제의 2번째 보기입니다.', false),
    (8, 'user 1의 lv.2 세번째 문제의 3번째 보기입니다.', false),
    (8, 'user 1의 lv.2 세번째 문제의 4번째 보기입니다.', false),
    (9, 'user 2의 lv.1 첫번째 문제의 1번째 보기입니다.', true),
    (9, 'user 2의 lv.1 첫번째 문제의 2번째 보기입니다.', false),
    (9, 'user 2의 lv.1 첫번째 문제의 3번째 보기입니다.', false),
    (9, 'user 2의 lv.1 첫번째 문제의 4번째 보기입니다.', false),
    (10, 'user 2의 lv.2 첫번째 문제의 1번째 보기입니다.', true),
    (10, 'user 2의 lv.2 첫번째 문제의 2번째 보기입니다.', false),
    (10, 'user 2의 lv.2 첫번째 문제의 3번째 보기입니다.', false),
    (10, 'user 2의 lv.2 첫번째 문제의 4번째 보기입니다.', false),
    (11, 'user 2의 lv.1 두번째 문제의 1번째 보기입니다.', true),
    (11, 'user 2의 lv.1 두번째 문제의 2번째 보기입니다.', false),
    (11, 'user 2의 lv.1 두번째 문제의 3번째 보기입니다.', false),
    (11, 'user 2의 lv.1 두번째 문제의 4번째 보기입니다.', false),
    (12, 'user 2의 lv.2 두번째 문제의 1번째 보기입니다.', true),
    (12, 'user 2의 lv.2 두번째 문제의 2번째 보기입니다.', false),
    (12, 'user 2의 lv.2 두번째 문제의 3번째 보기입니다.', false),
    (12, 'user 2의 lv.2 두번째 문제의 4번째 보기입니다.', false),
    (13, 'user 2의 lv.1 세번째 문제의 1번째 보기입니다.', true),
    (13, 'user 2의 lv.1 세번째 문제의 2번째 보기입니다.', false),
    (13, 'user 2의 lv.1 세번째 문제의 3번째 보기입니다.', false),
    (13, 'user 2의 lv.1 세번째 문제의 4번째 보기입니다.', false),
    (14, 'user 3의 lv.1 첫번째 문제의 1번째 보기입니다.', true),
    (14, 'user 3의 lv.1 첫번째 문제의 2번째 보기입니다.', false),
    (14, 'user 3의 lv.1 첫번째 문제의 3번째 보기입니다.', false),
    (14, 'user 3의 lv.1 첫번째 문제의 4번째 보기입니다.', false);
