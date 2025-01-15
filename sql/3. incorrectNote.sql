
USE backend_mock_exam;

DROP TABLE IF EXISTS incorrect_note;

CREATE TABLE incorrect_note (
	incorrect_note_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,														-- 유저 ID
	question_id INT NOT NULL,													-- 문제 ID
	subject_id INT NOT NULL,													-- 해당 문제가 속한 주제 ID
	level INT NOT NULL,															-- 해당 문제의 level
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

SELECT * FROM QUESTIONS;
SELECT question_id FROM incorrect_note WHERE question_id=108;
DELETE FROM incorrect_note;
SELECT * FROM incorrect_note WHERE SUBJECT_ID = 1 AND LEVEL = 1 ORDER BY INCORRECT_NOTE_ID DESC ;
SELECT * FROM incorrect_note;

SELECT level FROM QUESTIONS Q WHERE QUESTION_ID = 108;
INSERT INTO incorrect_note (question_id, subject_id, level) VALUES
(1, 1, 1),
(4, 1, 1),
(61, 4, 3),
(43, 3, 3),
(11, 1, 3),
(125, 8, 2),
(134, 8, 2),
(65, 4, 1),
(97, 6, 3),
(74, 5, 2),
(25, 2, 2),
(78, 5, 3),
(3, 1, 1),
(6, 1, 2),
(24, 2, 2),
(108, 7, 2);

INSERT INTO incorrect_note (question_id, subject_id, level) VALUES
(108, (SELECT subject_id FROM QUESTIONS WHERE QUESTION_ID = 108), (SELECT LEVEL FROM QUESTIONS WHERE QUESTION_ID = 108));

INSERT INTO incorrect_note (question_id, subject_id, level)
SELECT q.question_id, q.subject_id, q.level
FROM questions q
WHERE q.question_id = 108;

-- SELECT level FROM QUESTIONS WHERE QUESTION_ID = 108;
