-- 코드를 입력하세요
SELECT p.product_id, p.product_name, sum(p.price * o.amount) as TOTAL_SALES
FROM FOOD_PRODUCT P 
JOIN FOOD_ORDER o
on o.product_id = p.product_id
where year(o.produce_date) = 2022 AND month(o.produce_date) = 5
group by p.product_id
order by TOTAL_SALES DESC, p.product_id
