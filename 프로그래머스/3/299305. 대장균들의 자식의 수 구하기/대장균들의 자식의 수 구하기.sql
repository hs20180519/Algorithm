-- 코드를 작성해주세요
select d1.id, (select count(*) FROM ecoli_data where parent_id = d1.id) as child_count
from ecoli_data d1
order by d1.id