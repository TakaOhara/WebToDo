INSERT INTO `task_type` VALUES (1,'緊急','最優先で取り掛かるべきタスク'),
(2,'重要','期限に間に合わせるべきタスク'),
(3,'できれば','今後やってみたいアイデア');

INSERT INTO `task` VALUES (NULL,1,1,'JUnitを学習','テストの仕方を学習する','2020-07-07 00:00:00'),
(NULL,1,3,'サービスの自作','マイクロサービスを作ってみる','2020-09-13 00:00:00');

INSERT INTO `user` VALUES (1,'John','example@example.com','$2a$08$dSbk7S1tzk9I8CDB0mr6CO7ndTkTdC9/nojADBiukhWVBgHqR/g46',1,'something','temp');