CREATE TABLE student (
id            INTEGER        NOT NULL, -- 学生ID
name          VARCHAR(128)   NOT NULL, -- 氏名
birthday      DATE           NOT NULL, -- 生年月日
class_name    VARCHAR(8)             , -- 所属クラス
PRIMARY KEY(id)
);
 
INSERT INTO student (id, name, birthday, class_name) VALUES (1, '大谷 誠', '1977/1/28', 'CG');
INSERT INTO student (id, name, birthday, class_name) VALUES (2, '中森 雄大', '1982/9/9', 'Web');
INSERT INTO student (id, name, birthday, class_name) VALUES (3, '佐々木 真一', '1972/3/10', 'Web');
INSERT INTO student (id, name, birthday, class_name) VALUES (4, '稲田 睦美', '1988/11/1', 'CG');
INSERT INTO student (id, name, birthday, class_name) VALUES (5, '谷 嘉人', '1980/5/4', NULL);
 
 
 
CREATE TABLE exam (
id           INTEGER      NOT NULL, -- ID
student_id   INTEGER      NOT NULL, -- 学生ID
subject      VARCHAR(128) NOT NULL, -- 科目
score        INTEGER      NOT NULL, -- 点数
UNIQUE (student_id, subject),
PRIMARY KEY(id)
);
 
 
INSERT INTO exam (id, student_id, subject, score) VALUES (1, 1, 'PC基礎', 92);
INSERT INTO exam (id, student_id, subject, score) VALUES (2, 1, 'デザイン', 77);
INSERT INTO exam (id, student_id, subject, score) VALUES (3, 2, 'PC基礎', 51);
INSERT INTO exam (id, student_id, subject, score) VALUES (4, 2, 'デザイン', 80);
INSERT INTO exam (id, student_id, subject, score) VALUES (5, 3, 'デザイン', 74);
INSERT INTO exam (id, student_id, subject, score) VALUES (6, 4, 'PC基礎', 85);
INSERT INTO exam (id, student_id, subject, score) VALUES (7, 4, 'デザイン', 64);