
USE backend_mock_exam;

DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS levels;
DROP TABLE IF EXISTS questions;

CREATE TABLE subjects (
	subject_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_name VARCHAR(255) NOT NULL,											-- 주제명
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 수정일시
	UNIQUE (subject_name)
);

INSERT INTO SUBJECTS (SUBJECT_NAME) VALUES
('자료구조'), ('알고리즘'), ('운영체제'), ('네트워크'), ('데이터 베이스'), ('개발 언어'), ('백엔드');

CREATE TABLE levels (
	level_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_id INT NOT NULL,													-- 주제 ID 
	level INT NOT NULL,															-- 레벨
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
);

INSERT INTO LEVELS (subject_id, level) VALUES
(1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3), (4, 1), (4, 2), (4, 3),
(5, 1), (5, 2), (5, 3), (6, 1), (6, 2), (6, 3), (7, 1), (7, 2), (7, 3);

CREATE TABLE questions (
	question_id INT AUTO_INCREMENT PRIMARY KEY,									-- 식별자
	subject_id INT NOT NULL,													-- 주제 ID
	level INT NOT NULL,															-- 레벨 ID
	question_text TEXT NOT NULL,												-- 문제 내용
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
);

INSERT INTO questions (subject_id, level, question_text) VALUES
(1, 1, 'id 1의 첫번째 문제입니다'), (1, 1, 'id 1의 두번째 문제입니다'), (1, 1, 'id 1의 세번째 문제입니다'), (1, 1, 'id 1의 네번째 문제입니다'),
(1, 2, 'id 1의 다섯번째 문제입니다'), (1, 2, 'id 1의 여섯번째 문제입니다'), (1, 2, 'id 1의 일곱번째 문제입니다'), (1, 2, 'id 1의 여덟번째 문제입니다'),
(1, 3, 'id 1의 아홉번째 문제입니다'), (1, 3, 'id 1의 열번째 문제입니다'), (1, 3, 'id 1의 열한번째 문제입니다'), (1, 3, 'id 1의 열두번째 문제입니다'),
(1, 1, 'id 1의 열세번째 문제입니다'), (1, 1, 'id 1의 열네번째 문제입니다'), (1, 2, 'id 1의 열다섯번째 문제입니다'), (1, 2, 'id 1의 열여섯번째 문제입니다'),
(1, 3, 'id 1의 열일곱번째 문제입니다'), (2, 1, 'id 2의 첫번째 문제입니다'), (2, 1, 'id 2의 두번째 문제입니다'), (2, 1, 'id 2의 세번째 문제입니다'),
(2, 1, 'id 2의 네번째 문제입니다'), (2, 2, 'id 2의 다섯번째 문제입니다'), (2, 2, 'id 2의 여섯번째 문제입니다'), (2, 2, 'id 2의 일곱번째 문제입니다'),
(2, 2, 'id 2의 여덟번째 문제입니다'), (2, 3, 'id 2의 아홉번째 문제입니다'), (2, 3, 'id 2의 열번째 문제입니다'), (2, 3, 'id 2의 열한번째 문제입니다'),
(2, 3, 'id 2의 열두번째 문제입니다'), (2, 1, 'id 2의 열세번째 문제입니다'), (2, 1, 'id 2의 열네번째 문제입니다'), (2, 2, 'id 2의 열다섯번째 문제입니다'),
(2, 2, 'id 2의 열여섯번째 문제입니다'), (2, 3, 'id 2의 열일곱번째 문제입니다'), (3, 1, 'id 3의 첫번째 문제입니다'), (3, 1, 'id 3의 두번째 문제입니다'),
(3, 1, 'id 3의 세번째 문제입니다'), (3, 1, 'id 3의 네번째 문제입니다'), (3, 2, 'id 3의 다섯번째 문제입니다'), (3, 2, 'id 3의 여섯번째 문제입니다'),
(3, 2, 'id 3의 일곱번째 문제입니다'), (3, 2, 'id 3의 여덟번째 문제입니다'), (3, 3, 'id 3의 아홉번째 문제입니다'), (3, 3, 'id 3의 열번째 문제입니다'),
(3, 3, 'id 3의 열한번째 문제입니다'), (3, 3, 'id 3의 열두번째 문제입니다'), (3, 1, 'id 3의 열세번째 문제입니다'), (3, 1, 'id 3의 열네번째 문제입니다'),
(3, 2, 'id 3의 열다섯번째 문제입니다'), (3, 2, 'id 3의 열여섯번째 문제입니다'), (3, 3, 'id 3의 열일곱번째 문제입니다'), (4, 1, 'id 4의 첫번째 문제입니다'),
(4, 1, 'id 4의 두번째 문제입니다'), (4, 1, 'id 4의 세번째 문제입니다'), (4, 1, 'id 4의 네번째 문제입니다'), (4, 2, 'id 4의 다섯번째 문제입니다'),
(4, 2, 'id 4의 여섯번째 문제입니다'), (4, 2, 'id 4의 일곱번째 문제입니다'), (4, 2, 'id 4의 여덟번째 문제입니다'), (4, 3, 'id 4의 아홉번째 문제입니다'),
(4, 3, 'id 4의 열번째 문제입니다'), (4, 3, 'id 4의 열한번째 문제입니다'), (4, 3, 'id 4의 열두번째 문제입니다'), (4, 1, 'id 4의 열세번째 문제입니다'),
(4, 1, 'id 4의 열네번째 문제입니다'),(4, 2, 'id 4의 열다섯번째 문제입니다'),(4, 2, 'id 4의 열여섯번째 문제입니다'),(4, 3, 'id 4의 열일곱번째 문제입니다'),
(5, 1, 'id 5의 첫번째 문제입니다'),(5, 1, 'id 5의 두번째 문제입니다'),(5, 1, 'id 5의 세번째 문제입니다'),(5, 1, 'id 5의 네번째 문제입니다'),
(5, 2, 'id 5의 다섯번째 문제입니다'),(5, 2, 'id 5의 여섯번째 문제입니다'),(5, 2, 'id 5의 일곱번째 문제입니다'),(5, 2, 'id 5의 여덟번째 문제입니다'),
(5, 3, 'id 5의 아홉번째 문제입니다'),(5, 3, 'id 5의 열번째 문제입니다'),(5, 3, 'id 5의 열한번째 문제입니다'),(5, 3, 'id 5의 열두번째 문제입니다'),
(5, 1, 'id 5의 열세번째 문제입니다'),(5, 1, 'id 5의 열네번째 문제입니다'),(5, 2, 'id 5의 열다섯번째 문제입니다'),(5, 2, 'id 5의 열여섯번째 문제입니다'),
(5, 3, 'id 5의 열일곱번째 문제입니다'),(6, 1, 'id 6의 첫번째 문제입니다'),(6, 1, 'id 6의 두번째 문제입니다'),(6, 1, 'id 6의 세번째 문제입니다'),
(6, 1, 'id 6의 네번째 문제입니다'),(6, 2, 'id 6의 다섯번째 문제입니다'),(6, 2, 'id 6의 여섯번째 문제입니다'),(6, 2, 'id 6의 일곱번째 문제입니다'),
(6, 2, 'id 6의 여덟번째 문제입니다'),(6, 3, 'id 6의 아홉번째 문제입니다'),(6, 3, 'id 6의 열번째 문제입니다'),(6, 3, 'id 6의 열한번째 문제입니다'),
(6, 3, 'id 6의 열두번째 문제입니다'),(6, 1, 'id 6의 열세번째 문제입니다'),(6, 1, 'id 6의 열네번째 문제입니다'),(6, 2, 'id 6의 열다섯번째 문제입니다'),
(6, 2, 'id 6의 열여섯번째 문제입니다'),(6, 3, 'id 6의 열일곱번째 문제입니다'),(7, 1, 'id 7의 첫번째 문제입니다'),
(7, 1, 'id 7의 두번째 문제입니다'),(7, 1, 'id 7의 세번째 문제입니다'),(7, 1, 'id 7의 네번째 문제입니다'),(7, 2, 'id 7의 다섯번째 문제입니다'),
(7, 2, 'id 7의 여섯번째 문제입니다'),(7, 2, 'id 7의 일곱번째 문제입니다'),(7, 2, 'id 7의 여덟번째 문제입니다'),(7, 3, 'id 7의 아홉번째 문제입니다'),
(7, 3, 'id 7의 열번째 문제입니다'),(7, 3, 'id 7의 열한번째 문제입니다'),(7, 3, 'id 7의 열두번째 문제입니다'),(7, 1, 'id 7의 열세번째 문제입니다'),
(7, 1, 'id 7의 열네번째 문제입니다'),(7, 2, 'id 7의 열다섯번째 문제입니다'),(7, 2, 'id 7의 열여섯번째 문제입니다'),(7, 3, 'id 7의 열일곱번째 문제입니다');

SELECT * FROM subjects;
SELECT * FROM levels;
SELECT * FROM questions;
