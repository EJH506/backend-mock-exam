
USE backend_mock_exam;

DROP TABLE IF EXISTS memo;

CREATE TABLE memo (
	memo_id INT AUTO_INCREMENT PRIMARY KEY,										-- 식별자
	user_id INT NOT NULL,														-- 유저 ID 외래키
	memo_text TEXT,																-- 메모 내용
	deleted boolean NOT NULL DEFAULT FALSE,										-- 삭제 여부
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
	update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
)

SELECT * FROM memo;