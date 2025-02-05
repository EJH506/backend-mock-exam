-- CREATE DATABASE backend_mock_exam;

# Dbeaver 에서 : 모든 탭 Alt + X 로 전체 실행 !

USE backend_mock_exam;

DROP TABLE IF EXISTS question_unit_setting;

CREATE TABLE question_unit_setting (
	question_unit_id INT AUTO_INCREMENT PRIMARY KEY,							-- 식별자
	question_unit INT NOT NULL,													-- 관리자가 설정한 문항 분류 단위
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,								-- 생성일시
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 수정일시
);

INSERT INTO question_unit_setting (question_unit) VALUES (5);

SELECT * FROM question_unit_setting;