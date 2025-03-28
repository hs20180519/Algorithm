-- 코드를 입력하세요
SELECT MONTH(start_date) AS MONTH, car_id AS CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE START_DATE >= '2022-08-01' AND START_DATE <= '2022-10-31'
    AND CAR_ID IN(
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE >= '2022-08-01' AND START_DATE <= '2022-10-31'
        GROUP BY CAR_ID
        HAVING COUNT(*) >= 5
    )
GROUP BY MONTH(start_date), car_id
ORDER BY MONTH ASC, car_id DESC
