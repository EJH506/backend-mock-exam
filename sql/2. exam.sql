
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
(1, 1, 'Sid=1의 Qid=1 문제입니다'), (1, 1, 'Sid=1의 Qid=2 문제입니다'), (1, 1, 'Sid=1의 Qid=3 문제입니다'), (1, 1, 'Sid=1의 Qid=4 문제입니다'),
(1, 2, 'Sid=1의 Qid=5 문제입니다'), (1, 2, 'Sid=1의 Qid=6 문제입니다'), (1, 2, 'Sid=1의 Qid=7 문제입니다'), (1, 2, 'Sid=1의 Qid=8 문제입니다'),
(1, 3, 'Sid=1의 Qid=9 문제입니다'), (1, 3, 'Sid=1의 Qid=10 문제입니다'), (1, 3, 'Sid=1의 Qid=11 문제입니다'), (1, 3, 'Sid=1의 Qid=12 문제입니다'),
(1, 1, 'Sid=1의 Qid=13 문제입니다'), (1, 1, 'Sid=1의 Qid=14 문제입니다'), (1, 2, 'Sid=1의 Qid=15 문제입니다'), (1, 2, 'Sid=1의 Qid=16 문제입니다'),
(1, 3, 'Sid=1의 Qid=17 문제입니다'), (2, 1, 'Sid=2의 Qid=18 문제입니다'), (2, 1, 'Sid=2의 Qid=19 문제입니다'), (2, 1, 'Sid=2의 Qid=20 문제입니다'),
(2, 1, 'Sid=2의 Qid=21 문제입니다'), (2, 2, 'Sid=2의 Qid=22 문제입니다'), (2, 2, 'Sid=2의 Qid=23 문제입니다'), (2, 2, 'Sid=2의 Qid=24 문제입니다'),
(2, 2, 'Sid=2의 Qid=25 문제입니다'), (2, 3, 'Sid=2의 Qid=26 문제입니다'), (2, 3, 'Sid=2의 Qid=27 문제입니다'), (2, 3, 'Sid=2의 Qid=28 문제입니다'),
(2, 3, 'Sid=2의 Qid=29 문제입니다'), (2, 1, 'Sid=2의 Qid=30 문제입니다'), (2, 1, 'Sid=2의 Qid=31 문제입니다'), (2, 2, 'Sid=2의 Qid=32 문제입니다'),
(2, 2, 'Sid=2의 Qid=33 문제입니다'), (2, 3, 'Sid=2의 Qid=34 문제입니다'), (3, 1, 'Sid=3의 Qid=35 문제입니다'), (3, 1, 'Sid=3의 Qid=36 문제입니다'),
(3, 1, 'Sid=3의 Qid=37 문제입니다'), (3, 1, 'Sid=3의 Qid=38 문제입니다'), (3, 2, 'Sid=3의 Qid=39 문제입니다'), (3, 2, 'Sid=3의 Qid=40 문제입니다'),
(3, 2, 'Sid=3의 Qid=41 문제입니다'), (3, 2, 'Sid=3의 Qid=42 문제입니다'), (3, 3, 'Sid=3의 Qid=43 문제입니다'), (3, 3, 'Sid=3의 Qid=44 문제입니다'),
(3, 3, 'Sid=3의 Qid=45 문제입니다'), (3, 3, 'Sid=3의 Qid=46 문제입니다'), (3, 1, 'Sid=3의 Qid=47 문제입니다'), (3, 1, 'Sid=3의 Qid=48 문제입니다'),
(3, 2, 'Sid=3의 Qid=49 문제입니다'), (3, 2, 'Sid=3의 Qid=50 문제입니다'), (3, 3, 'Sid=3의 Qid=51 문제입니다'), (4, 1, 'Sid=4의 Qid=52 문제입니다'),
(4, 1, 'Sid=4의 Qid=53 문제입니다'), (4, 1, 'Sid=4의 Qid=54 문제입니다'), (4, 1, 'Sid=4의 Qid=55 문제입니다'), (4, 2, 'Sid=4의 Qid=56 문제입니다'),
(4, 2, 'Sid=4의 Qid=57 문제입니다'), (4, 2, 'Sid=4의 Qid=58 문제입니다'), (4, 2, 'Sid=4의 Qid=59 문제입니다'), (4, 3, 'Sid=4의 Qid=60 문제입니다'),
(4, 3, 'Sid=4의 Qid=61 문제입니다'), (4, 3, 'Sid=4의 Qid=62 문제입니다'), (4, 3, 'Sid=4의 Qid=63 문제입니다'), (4, 1, 'Sid=4의 Qid=64 문제입니다'),
(4, 1, 'Sid=4의 Qid=65 문제입니다'), (4, 2, 'Sid=4의 Qid=66 문제입니다'), (4, 2, 'Sid=4의 Qid=67 문제입니다'), (4, 3, 'Sid=4의 Qid=68 문제입니다'),
(5, 1, 'Sid=5의 Qid=69 문제입니다'), (5, 1, 'Sid=5의 Qid=70 문제입니다'), (5, 1, 'Sid=5의 Qid=71 문제입니다'), (5, 1, 'Sid=5의 Qid=72 문제입니다'),
(5, 2, 'Sid=5의 Qid=73 문제입니다'), (5, 2, 'Sid=5의 Qid=74 문제입니다'), (5, 2, 'Sid=5의 Qid=75 문제입니다'), (5, 2, 'Sid=5의 Qid=76 문제입니다'),
(5, 3, 'Sid=5의 Qid=77 문제입니다'), (5, 3, 'Sid=5의 Qid=78 문제입니다'), (5, 3, 'Sid=5의 Qid=79 문제입니다'), (5, 3, 'Sid=5의 Qid=80 문제입니다'),
(5, 1, 'Sid=5의 Qid=81 문제입니다'), (5, 1, 'Sid=5의 Qid=82 문제입니다'), (5, 2, 'Sid=5의 Qid=83 문제입니다'), (5, 2, 'Sid=5의 Qid=84 문제입니다'),
(5, 3, 'Sid=5의 Qid=85 문제입니다'), (6, 1, 'Sid=6의 Qid=86 문제입니다'), (6, 1, 'Sid=6의 Qid=87 문제입니다'), (6, 1, 'Sid=6의 Qid=88 문제입니다'),
(6, 1, 'Sid=6의 Qid=89 문제입니다'), (6, 2, 'Sid=6의 Qid=90 문제입니다'), (6, 2, 'Sid=6의 Qid=91 문제입니다'), (6, 2, 'Sid=6의 Qid=92 문제입니다'),
(6, 2, 'Sid=6의 Qid=93 문제입니다'), (6, 3, 'Sid=6의 Qid=94 문제입니다'), (6, 3, 'Sid=6의 Qid=95 문제입니다'), (6, 3, 'Sid=6의 Qid=96 문제입니다'),
(6, 3, 'Sid=6의 Qid=97 문제입니다'), (6, 1, 'Sid=6의 Qid=98 문제입니다'), (6, 1, 'Sid=6의 Qid=99 문제입니다'), (6, 2, 'Sid=6의 Qid=100 문제입니다'),
(6, 2, 'Sid=6의 Qid=101 문제입니다'), (6, 3, 'Sid=6의 Qid=102 문제입니다'), (7, 1, 'Sid=7의 Qid=103 문제입니다'),
(7, 1, 'Sid=7의 Qid=104 문제입니다'), (7, 1, 'Sid=7의 Qid=105 문제입니다'), (7, 1, 'Sid=7의 Qid=106 문제입니다'), (7, 2, 'Sid=7의 Qid=107 문제입니다'),
(7, 2, 'Sid=7의 Qid=108 문제입니다'), (7, 2, 'Sid=7의 Qid=109 문제입니다'), (7, 2, 'Sid=7의 Qid=110 문제입니다'), (7, 3, 'Sid=7의 Qid=111 문제입니다'),
(7, 3, 'Sid=7의 Qid=112 문제입니다'), (7, 3, 'Sid=7의 Qid=113 문제입니다'), (7, 3, 'Sid=7의 Qid=114 문제입니다'), (7, 1, 'Sid=7의 Qid=115 문제입니다'),
(7, 1, 'Sid=7의 Qid=116 문제입니다'), (7, 2, 'Sid=7의 Qid=117 문제입니다'), (7, 2, 'Sid=7의 Qid=118 문제입니다'), (7, 3, 'Sid=7의 Qid=119 문제입니다');

SELECT * FROM subjects;
SELECT * FROM levels;
SELECT * FROM questions;
