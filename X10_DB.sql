CREATE DATABASE IF NOT EXISTS xtendb;
USE xtendb;
CREATE TABLE IF NOT EXISTS `user` (
	`user_seq`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`id`	VARCHAR(20)	NOT NULL,
	`password`	VARCHAR(20)	NULL	COMMENT '소셜로그인 할 경우에는 가져오지 않음',
	`email`	VARCHAR(50)	NULL	COMMENT '소셜로그인 할 경우에는 이 필드 지우고  sns id로 조회',
	`nickname`	VARCHAR(50)	NOT NULL,
	`img`	VARCHAR(50)	NULL	COMMENT '사용자가 업로드한 프로필 이미지'
);

CREATE TABLE IF NOT EXISTS `test_result` (
	`test_result_seq`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_seq`	INT	NOT NULL,
	`exercise_seq`	INT	NOT NULL,
	`test_date`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `exercise` (
	`exercise_seq`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`exercise_name`	VARCHAR(20)	NOT NULL,
	`exercise_image`	VARCHAR(200) NULL
);

CREATE TABLE IF NOT EXISTS `favorite_exercises` (
	`favorite_exercises_seq`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_seq`	INT	NOT NULL,
	`exercise_seq`	INT	NOT NULL
);

CREATE TABLE IF NOT EXISTS `favorite_videos` (
	`favorite_videos_seq`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_seq`	INT	NOT NULL,
	`video_url`	VARCHAR(200)	NOT NULL
);

ALTER TABLE `test_result` ADD CONSTRAINT `FK_user_TO_test_result_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `test_result` ADD CONSTRAINT `FK_exercise_TO_test_result_1` FOREIGN KEY (
	`exercise_seq`
)
REFERENCES `exercise` (
	`exercise_seq`
);

ALTER TABLE `favorite_exercises` ADD CONSTRAINT `FK_user_TO_favorite_exercises_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `favorite_exercises` ADD CONSTRAINT `FK_exercise_TO_favorite_exercises_1` FOREIGN KEY (
	`exercise_seq`
)
REFERENCES `exercise` (
	`exercise_seq`
);

ALTER TABLE `favorite_videos` ADD CONSTRAINT `FK_user_TO_favorite_videos_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

INSERT INTO exercise (exercise_name)
VALUES ('테니스'),
('실내클라이밍'),
('수영'),
('주짓수'),
('필라테스'),
('헬스'),
('킥복싱'),
('등산'),
('GX'),
('러닝'),
('축구'),
('홈트레이닝');

INSERT INTO exercise (exercise_name)
VALUES ('발야구');

SELECT *
FROM exercise;

INSERT INTO user (id, password, email, nickname, img)
VALUES ('gowns0205' , 'ssafy1234', 'gowns0205@gmail.com', '멍게' , 'sdiofshjdiofsdhf'),
 ('gowns6413' , 'ssafy1234', 'gowns0205@gmail.com', '말미잘' , 'sdfsdfsdfsdfsdfsfs'),
 ('gowns7279' , 'ssafy1234', 'gowns0205@gmail.com', '홍삼' , 'sdfsdfsdfsdfsdfsfs'),
	('gowns234234' , 'ssafy1234', 'gowns0205@gmail.com', '산삼' , 'sdfsdfsdfsdfsdfsfs'),
 ('gowns7234234' , 'ssafy1234', 'gowns0205@gmail.com', '당근' , 'sdfsdfsdfsdfsdfsfs')
;

SELECT *
FROM user;

SELECT * FROM favorite_videos;






