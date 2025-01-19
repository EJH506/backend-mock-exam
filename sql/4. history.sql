
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

SELECT * FROM exam_history;
SELECT * FROM HISTORY_ITEMS HI ;

SELECT h.history_id, h.user_id, h.subject_name, h.LEVEL, h.total_questions_count, h.correct_rate, date_format(h.created_at, '%Y-%m-%d') AS created_at, 
		i.question_id, i.correct_answer_id, i.user_answer_id, i.is_correct
FROM HISTORY_ITEMS i
JOIN exam_history h ON h.history_id = i.history_id 
WHERE h.history_id = 1;

SELECT i.history_id, i.question_id, i.correct_answer_id, i.user_answer_id, i.is_correct,
q.question_text, a.answer_id, a.answer_text 
FROM HISTORY_ITEMS i
JOIN questions q on i.question_id = q.question_id 
JOIN answers a on a.answer_id = i.correct_answer_id or a.answer_id = i.user_answer_id
WHERE i.history_id = 1;
-- JOIN exam_history h ON h.history_id = i.history_id 

SELECT h.history_id, q.question_id, q.subject_id, s.subject_name, q.LEVEL, q.question_text FROM questions q
JOIN history_items h ON h.question_id = q.question_id
JOIN subjects s ON q.subject_id = s.subject_id 
WHERE history_id = 1 AND is_correct = false;

SELECT * FROM answers a
JOIN history_items h ON h.question_id = a.question_id 
WHERE history_id = 1 AND a.is_correct;

SELECT * FROM answers
        WHERE answer_id = 13;
-- 
-- INSERT INTO exam_history (user_id,subject_name,LEVEL,total_questions_count,correct_questions_count,correct_rate) VALUES
-- (1, "운영체제", 3, 5, 2, 60);
-- 
-- INSERT INTO history_items (history_id, question_id,correct_answer_id,user_answer_id,is_correct) VALUES
-- (1, 43,169,170,false),
-- (1, 44,174,176,false),
-- (1, 45,177,177,true),
-- (1, 46,181,181,true),
-- (1, 51,201,0,false);

SELECT * FROM history_items h
JOIN ANSWERS A ON h.QUESTION_ID = a.QUESTION_ID ;