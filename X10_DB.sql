DROP DATABASE xtendb;
CREATE DATABASE xtendb;

USE xtendb;

CREATE TABLE `user` (
	`user_seq`	INT	NOT NULL ,
	`id`	VARCHAR(20)	NOT NULL,
	`password`	VARCHAR(20)	NULL	COMMENT '소셜로그인 할 경우에는 가져오지 않음',
	`email`	VARCHAR(50)	NULL	COMMENT '소셜로그인 할 경우에는 이 필드 지우고  sns id로 조회',
	`nickname`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `test_result` (
	`test_result_seq`	INT	NOT NULL,
	`exercise_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
	`test_date`	DATETIME	NOT NULL
);

CREATE TABLE `exercise` (
	`exercise_seq`	INT	NOT NULL,
	`exercise_name`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `favorite_exercises` (
	`favorite_exercises_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
	`exercise_seq`	INT	NOT NULL
);

CREATE TABLE `favorite_videos` (
	`favorite_videos_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
	`video_url`	VARCHAR(200)	NOT NULL
);

CREATE TABLE `image` (
	`image_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
	`image_type`	VARCHAR(100) NOT NULL,
	`image_data`	LONGBLOB	NOT NULL,
	`image_name`	VARCHAR(100) NOT NULL,
	`upload_date`	TIMESTAMP	NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_seq`
);

ALTER TABLE `user` MODIFY COLUMN `user_seq` INT AUTO_INCREMENT;

ALTER TABLE `test_result` ADD CONSTRAINT `PK_TEST_RESULT` PRIMARY KEY (
	`test_result_seq`,
	`exercise_seq`,
	`user_seq`
);

ALTER TABLE `test_result` MODIFY COLUMN `test_result_seq` INT AUTO_INCREMENT;

ALTER TABLE `exercise` ADD CONSTRAINT `PK_EXERCISE` PRIMARY KEY (
	`exercise_seq`
);

ALTER TABLE `exercise` MODIFY COLUMN `exercise_seq` INT AUTO_INCREMENT;

ALTER TABLE `favorite_exercises` ADD CONSTRAINT `PK_FAVORITE_EXERCISES` PRIMARY KEY (
	`favorite_exercises_seq`,
	`user_seq`,
	`exercise_seq`
);

ALTER TABLE `favorite_exercises` MODIFY COLUMN `favorite_exercises_seq` INT AUTO_INCREMENT;

ALTER TABLE `favorite_videos` ADD CONSTRAINT `PK_FAVORITE_VIDEOS` PRIMARY KEY (
	`favorite_videos_seq`,
	`user_seq`
);

ALTER TABLE `favorite_videos` MODIFY COLUMN `favorite_videos_seq` INT AUTO_INCREMENT;

ALTER TABLE `image` ADD CONSTRAINT `PK_IMAGE` PRIMARY KEY (
	`image_seq`,
	`user_seq`
);

ALTER TABLE `image` MODIFY COLUMN `image_seq` INT AUTO_INCREMENT;

ALTER TABLE `test_result` ADD CONSTRAINT `FK_exercise_TO_test_result_1` FOREIGN KEY (
	`exercise_seq`
)
REFERENCES `exercise` (
	`exercise_seq`
);

ALTER TABLE `test_result` ADD CONSTRAINT `FK_user_TO_test_result_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
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

ALTER TABLE `image` ADD CONSTRAINT `FK_user_TO_image_1` FOREIGN KEY (
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

SELECT * FROM exercise;

SELECT * FROM user;

SELECT * FROM favorite_videos;

SELECT * FROM image;



