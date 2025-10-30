-- 코드를 입력하세요
select I.rest_id, I.rest_name, I.food_type, I.favorites, I.address, round(avg(R.review_score), 2) AS score
from rest_review r
join REST_INFO I
on r.rest_id = I.rest_id
WHERE I.ADDRESS LIKE ('서울%')
group by I.rest_id
order by avg(R.review_score) desc, I.favorites desc
