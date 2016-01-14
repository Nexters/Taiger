
mysql demo 스키마 생성 후,
demo.hello_info 테이블 생성
<html>
<pre>
CREATE TABLE demo.`hello_info` (
  `id` int(11) NOT NULL,
  `message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
</pre>
<html>
