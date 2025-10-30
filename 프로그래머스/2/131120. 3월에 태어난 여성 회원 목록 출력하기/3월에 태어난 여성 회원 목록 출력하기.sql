-- 코드를 입력하세요
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(date_of_birth, '%Y-%m-%d') as DATE_OF_BIRTH
from member_profile
where month(date_of_birth) = 3 and gender = 'W' and tlno is not null
order by member_id