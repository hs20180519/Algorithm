-- 코드를 작성해주세요
select d1.id,  d1.genotype, d2.genotype as parent_genotype
from ecoli_data d1
join ecoli_data d2
on d1.parent_id = d2.id
where d1.genotype & d2.genotype = d2.genotype
order by d1.id