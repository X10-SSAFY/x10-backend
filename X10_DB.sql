CREATE DATABASE IF NOT EXISTS xtendb;

use xtendb;

CREATE TABLE IF NOT EXISTS `user` (
	`user_seq`	INT	NOT NULL AUTO_INCREMENT,
	`id`	VARCHAR(20)	NOT NULL,
	`password`	VARCHAR(20)	NULL	COMMENT '소셜로그인 할 경우에는 가져오지 않음',
	`email`	VARCHAR(50)	NULL	COMMENT '소셜로그인 할 경우에는 이 필드 지우고  sns id로 조회',
	`name`	VARCHAR(50)	NOT NULL,
	`img`	VARCHAR(50)	NULL	COMMENT '사용자가 업로드한 프로필 이미지',
    PRIMARY KEY (`user_seq`)
);

CREATE TABLE IF NOT EXISTS `test_result` (
	`test_result_seq`	INT	NOT NULL AUTO_INCREMENT,
	`user_seq`	INT	NOT NULL,
	`test_date`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`test_result_seq`)
);

CREATE TABLE IF NOT EXISTS `exercise` (
	`exercise_seq`	INT	NOT NULL AUTO_INCREMENT,
	`test_result_seq`	INT	NULL,
	`favorite_exercises_seq`	INT	NULL,
	`exercise_name`	VARCHAR(20)	NOT NULL,
	`exercise_image`	VARCHAR(200)	NOT NULL,
	PRIMARY KEY (`exercise_seq`)
);

CREATE TABLE IF NOT EXISTS `favorite_exercises` (
	`favorite_exercises_seq`	INT	NOT NULL AUTO_INCREMENT,
	`user_seq`	INT	NOT NULL,
    PRIMARY KEY (`favorite_exercises_seq`)
);

CREATE TABLE IF NOT EXISTS `favorite_videos` (
	`favorite_videos_seq`	INT	NOT NULL AUTO_INCREMENT,
	`user_seq`	INT	NOT NULL,
	`video_url`	VARCHAR(200)	NOT NULL,
     PRIMARY KEY (`favorite_videos_seq`)
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_seq`
);

ALTER TABLE `test_result` ADD CONSTRAINT `PK_TEST_RESULT` PRIMARY KEY (
	`test_result_seq`,
	`user_seq`
);

ALTER TABLE `exercise` ADD CONSTRAINT `PK_EXERCISE` PRIMARY KEY (
	`exercise_seq`,
	`test_result_seq`,
	`favorite_exercises_seq`
);

ALTER TABLE `favorite_exercises` ADD CONSTRAINT `PK_FAVORITE_EXERCISES` PRIMARY KEY (
	`favorite_exercises_seq`,
	`user_seq`
);

ALTER TABLE `favorite_videos` ADD CONSTRAINT `PK_FAVORITE_VIDEOS` PRIMARY KEY (
	`favorite_videos_seq`,
	`user_seq`
);

ALTER TABLE `test_result` ADD CONSTRAINT `FK_user_TO_test_result_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `exercise` ADD CONSTRAINT `FK_test_result_TO_exercise_1` FOREIGN KEY (
	`test_result_seq`
)
REFERENCES `test_result` (
	`test_result_seq`
);

ALTER TABLE `exercise` ADD CONSTRAINT `FK_favorite_exercises_TO_exercise_1` FOREIGN KEY (
	`favorite_exercises_seq`
)
REFERENCES `favorite_exercises` (
	`favorite_exercises_seq`
);

ALTER TABLE `favorite_exercises` ADD CONSTRAINT `FK_user_TO_favorite_exercises_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

ALTER TABLE `favorite_videos` ADD CONSTRAINT `FK_user_TO_favorite_videos_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `user` (
	`user_seq`
);

