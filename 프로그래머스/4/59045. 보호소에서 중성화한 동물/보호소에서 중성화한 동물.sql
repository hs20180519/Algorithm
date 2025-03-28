-- 보호소에 들어올 당시에는 중성화1되지 않았지만, 보호소를 나갈 당시에는 중성화된 동물의 아이디와 생물 종, 이름을 조회하는 아이디 순으로 조회하는 SQL 문을 작성해주세요.
SELECT ai.animal_id, ai.animal_type, ai.name
from animal_ins as ai
join animal_outs as ao
on ai.animal_id = ao.animal_id
where ai.sex_upon_intake like 'Intact%' and ao.sex_upon_outcome like 'Spayed%' or (ai.sex_upon_intake like 'Intact%' and ao.sex_upon_outcome like 'Neutered%')