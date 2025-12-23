



WITH filter_product_by_category AS (
  SELECT 
    c.id AS product_id 
  FROM 
    product p 
    JOIN product_category c ON p.id = c.id 
    START WITH c.id = 4 CONNECT BY c.father_category = PRIOR c.id
) , 
group_by_filter AS(
  SELECT 
    sr.branch_office_id,
    sr.product_id,
     EXTRACT(
      DAY 
      FROM 
        sr.sell_date 
    ) AS time_unit,
    SUM(sr.units) AS units,
    SUM(p.price) AS price
  FROM 
    sell_registry sr
    JOIN
    filter_product_by_category cf 
     ON sr.product_id = cf.product_id 
    JOIN product p ON sr.product_id = p.id
    GROUP BY 
    sr.branch_office_id,sr.product_id,
    EXTRACT(
      DAY 
      FROM 
        sr.sell_date 
    ) 
)SELECT  mx.mxUnits , mn.mnunits,group_by_filter.*
FROM group_by_filter, (SELECT gf.branch_office_id , MAX(gf.units)mxUnits FROM group_by_filter gf GROUP BY gf.branch_office_id) mx, 
(SELECT gf.branch_office_id, MIN(gf.units) mnunits FROM group_by_filter gf GROUP BY gf.branch_office_id)mn 
WHERE group_by_filter.branch_office_id =mx.branch_office_id
AND group_by_filter.branch_office_id =mn.branch_office_id;





