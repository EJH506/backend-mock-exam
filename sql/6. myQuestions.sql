
USE backend_mock_exam;

DROP TABLE IF EXISTS my_questions;
DROP TABLE IF EXISTS my_questions_levels;
DROP TABLE IF EXISTS my_questions_answers;

SELECT * FROM my_questions;
SELECT * FROM my_questions_levels;
SELECT * FROM my_questions_answers;

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
(2, 1, 'user 2의 lv.1 첫번째 문제입니다'),(2, 2, 'user 2의 lv.2 첫번째 문제입니다'),(2, 3, 'user 2의 lv.3 첫번째 문제입니다'),
(2, 1, 'user 2의 lv.1 두번째 문제입니다'),(2, 2, 'user 2의 lv.2 두번째 문제입니다'),(2, 3, 'user 2의 lv.3 두번째 문제입니다'),
(2, 1, 'user 2의 lv.1 세번째 문제입니다'),
(3, 1, 'user 3의 lv.1 첫번째 문제입니다'), (3, 2, 'user 3의 lv.2 첫번째 문제입니다');

CREATE TABLE my_questions_levels (
	level_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	user_id INT NOT NULL,														-- 유저 ID  (외래키)
	level INT NOT NULL,															-- 레벨
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

INSERT INTO my_questions_levels (user_id, level) VALUES 
(1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (3, 1);

CREATE TABLE my_questions_answers (
	answer_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	my_question_id INT NOT NULL,												-- 질문 ID
	answer_text TEXT NOT NULL,													-- 보기 내용
	is_correct BOOLEAN,															-- 정답 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

INSERT INTO my_questions_answers (my_question_id, answer_text, is_correct) VALUES
    (1, 'id 1의 1번째 보기입니다.', true),
    (1, 'id 1의 2번째 보기입니다.', false),
    (1, 'id 1의 3번째 보기입니다.', false),
    (1, 'id 1의 4번째 보기입니다.', false),
    (2, 'id 2의 1번째 보기입니다.', true),
    (2, 'id 2의 2번째 보기입니다.', false),
    (2, 'id 2의 3번째 보기입니다.', false),
    (2, 'id 2의 4번째 보기입니다.', false),
    (3, 'id 3의 1번째 보기입니다.', true),
    (3, 'id 3의 2번째 보기입니다.', false),
    (3, 'id 3의 3번째 보기입니다.', false),
    (3, 'id 3의 4번째 보기입니다.', false),
    (4, 'id 4의 1번째 보기입니다.', true),
    (4, 'id 4의 2번째 보기입니다.', false),
    (4, 'id 4의 3번째 보기입니다.', false),
    (4, 'id 4의 4번째 보기입니다.', false),
    (5, 'id 5의 1번째 보기입니다.', true),
    (5, 'id 5의 2번째 보기입니다.', false),
    (5, 'id 5의 3번째 보기입니다.', false),
    (5, 'id 5의 4번째 보기입니다.', false),
    (6, 'id 6의 1번째 보기입니다.', true),
    (6, 'id 6의 2번째 보기입니다.', false),
    (6, 'id 6의 3번째 보기입니다.', false),
    (6, 'id 6의 4번째 보기입니다.', false),
    (7, 'id 7의 1번째 보기입니다.', true),
    (7, 'id 7의 2번째 보기입니다.', false),
    (7, 'id 7의 3번째 보기입니다.', false),
    (7, 'id 7의 4번째 보기입니다.', false),
    (8, 'id 8의 1번째 보기입니다.', true),
    (8, 'id 8의 2번째 보기입니다.', false),
    (8, 'id 8의 3번째 보기입니다.', false),
    (8, 'id 8의 4번째 보기입니다.', false),
    (9, 'id 9의 1번째 보기입니다.', true),
    (9, 'id 9의 2번째 보기입니다.', false),
    (9, 'id 9의 3번째 보기입니다.', false),
    (9, 'id 9의 4번째 보기입니다.', false),
    (10, 'id 10의 1번째 보기입니다.', true),
    (10, 'id 10의 2번째 보기입니다.', false),
    (10, 'id 10의 3번째 보기입니다.', false),
    (10, 'id 10의 4번째 보기입니다.', false),
    (11, 'id 11의 1번째 보기입니다.', true),
    (11, 'id 11의 2번째 보기입니다.', false),
    (11, 'id 11의 3번째 보기입니다.', false),
    (11, 'id 11의 4번째 보기입니다.', false),
    (12, 'id 12의 1번째 보기입니다.', true),
    (12, 'id 12의 2번째 보기입니다.', false),
    (12, 'id 12의 3번째 보기입니다.', false),
    (12, 'id 12의 4번째 보기입니다.', false),
    (13, 'id 13의 1번째 보기입니다.', true),
    (13, 'id 13의 2번째 보기입니다.', false),
    (13, 'id 13의 3번째 보기입니다.', false),
    (13, 'id 13의 4번째 보기입니다.', false),
    (14, 'id 14의 1번째 보기입니다.', true),
    (14, 'id 14의 2번째 보기입니다.', false),
    (14, 'id 14의 3번째 보기입니다.', false),
    (14, 'id 14의 4번째 보기입니다.', false),
    (15, 'id 15의 1번째 보기입니다.', true),
    (15, 'id 15의 2번째 보기입니다.', false),
    (15, 'id 15의 3번째 보기입니다.', false),
    (15, 'id 15의 4번째 보기입니다.', false),
    (16, 'id 16의 1번째 보기입니다.', true),
    (16, 'id 16의 2번째 보기입니다.', false),
    (16, 'id 16의 3번째 보기입니다.', false),
    (16, 'id 16의 4번째 보기입니다.', false),
    (17, 'id 17의 1번째 보기입니다.', true),
    (17, 'id 17의 2번째 보기입니다.', false),
    (17, 'id 17의 3번째 보기입니다.', false),
    (17, 'id 17의 4번째 보기입니다.', false);
