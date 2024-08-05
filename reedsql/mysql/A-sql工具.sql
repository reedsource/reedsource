-- 获取数据库中各个索引的使用情况
SELECT table_name    AS '表名',
       index_name    AS '索引名',
       non_unique    AS '是否唯一',
       index_type    AS '索引类型',
       seq_in_index  AS '索引顺序',
       column_name   AS '列名',
       cardinality   AS '基数',
       sub_part      AS '部分长度',
       packed        AS '是否压缩',
       nuLL          AS '是否允许空值',
       index_comment AS '索引备注'
FROM information_schema.statistics
WHERE table_schema = 'your_database_name';