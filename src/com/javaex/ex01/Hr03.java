select first_name,salary
from employees
where salary<(select avg(salary)
              from employees);

select first_name,salary,department_id
from employees
where department_id=110;

select first_name,salary
from employees
where salary in(select salary
              from employees
              where department_id=110);
-----------예제              
select first_name
from employees
where (department_id,salary) in (select department_id,max(salary)
                                 from employees 
                                 group by department_id);

select first_name,salary,department_name------x
from employees e,departments d
where e.department_id=d.department_id
group by d.department_id
having max(salary);

select department_id,max(salary)
from employees
group by department_id;--그룹으로 만들어서 각그룹(한줄)당 뭘알고 싶어(컬럼)

select rownum
      ,first_name
      ,salary
      from employees
      order by desc;
      
select rownum,first_name,salary --정렬때문에 이렇게 씀
from(select first_name,salary
     from employees
     order by salary desc);
------------------------ROWNUM------------------
select r,first_name,salary
from( select rownum r,first_name,salary
      from( select first_name,salary,hire_date
            from employees
            where hire_date>='07/01/01'
            and hire_date<='07/12/31'
            order by salary desc)    
     )     
where r<4;
----문제1
 select count(*)
 from employees
 where salary<(select avg(salary)
               from employees);
----문제2
select employee_id,last_name,salary
from employees
where (department_id,salary) in(select department_id,max(salary)
                                from employees
                                group by department_id)
order by salary desc;
----문제3
select job_title 업무명,sums 연봉총합
from jobs j,(select job_id,sum(salary) sums--별명
                from employees
                group by job_id)s
where j.job_id=s.job_id
order by sums desc;

----문제4
select employee_id,last_name,department_id
from employees
where(department_id,salary)in(select department_id,avg(salary) avgs
                       from employees
                       group by department_id);

select e.employee_id,e.last_name,e.department_id,e.salary,avgs
from employees e,(select department_id,avg(salary) avgs
                  from employees
                  group by department_id)d
where e.department_id=d.department_id
and e.salary>d.avgs;
--------------------------------------
-----문제1-------o-----
---입사일 뽑아봄
select first_name,MAXhire_date,salary
from employees;
---부서이름만 나옴
select department_name
from departments
where(department_id)in(select department_id
                       from(select rownum r,first_name,department_id,hire_date,salary ---번호매김
                            from(select first_name,department_id,hire_date,salary ---입사일뽑아봄
                                 from employees
                                 order by hire_date desc)
                             )
                         where r<2);
-----부서번호,이름,급여
select department_name,first_name,salary--부서명만 하면 되지만 나머진 출력안되지
from departments
where(department_id)in(select department_id,first_name,salary--이름이랑 급여지워야 되는데 
                        from employees
                        where(department_id,first_name,salary)in(select department_id,first_name,salary
                                               from(select rownum r,first_name,department_id,hire_date,salary ---번호매김
                                                    from(select first_name,department_id,hire_date,salary ---입사일뽑아봄
                                                         from employees
                                                         order by hire_date desc)
                                                     )
                                                 where r<2));
-------------성공-------------
select first_name,salary,department_name
from departments d,(select department_id,first_name,salary
                   from employees
                   where(department_id,first_name,salary)in(select department_id
                                                                   ,first_name,salary
                                                            from(select rownum r
                                                                        ,first_name
                                                                        ,department_id
                                                                        ,hire_date
                                                                        ,salary ---번호매김
                                                                 from(select first_name
                                                                            ,department_id
                                                                            ,hire_date,salary ---입사일뽑아봄
                                                                      from employees
                                                                      order by hire_date desc)
                                                                  )
                                                              where r<2))n
where d.department_id=n.department_id;


                
----문제3--------o-----------
select employee_id,first_name,salary,department_id 
from employees
where(department_id) in (select department_id
                         from( select rownum r,department_id,avgs
                               from(select department_id,avg(salary) avgs
                                    from employees
                                    group by department_id
                                    order by avgs desc)
                                )
                         where r<2);



select employee_id,first_name,last_name,job_titlr,salary
from employees e,(select department_id,avg(salary) avgs
                  from employees
                  group by department_id
                  order by avgs desc)d
where e.department_id=d.department_id;


SELECT max(avgs),e.department_id
from employees e ,(select department_id,avg(salary) avgs
                     from employees
                     group by department_id
                     order by avgs desc)d;
     
----문제4
select department_id,department_name
from departments;
select department_id,avg(salary) avgs
                  from employees
                  group by department_id
                  order by avgs desc;
-----------o-----------
select department_name
from departments  
where(department_id) in (select department_id
                         from( select rownum r,department_id,avgs
                               from(select department_id,avg(salary) avgs
                                     from employees
                                       group by department_id
                                        order by avgs desc)
                                )
                         where r<2);
----------O------------
select department_name
from departments
where(department_id)in(select department_id
                        from employees
                        group by department_id
                        having avg(salary)=(select max(avg(salary))
                                                from employees
                                                group by department_id));

---문제5-----o----------------
select region_name
from regions
where(region_id)in(select region_id
                from countries
                where(country_id)in(select country_id
                                    from locations
                                    where(location_id)in(select location_id
                                                        from departments
                                                        where(department_id)in(select department_id
                                                                            from(select rownum r
                                                                                        ,department_id
                                                                                        ,avgs
                                                                                 from(select department_id
                                                                                            ,avg(salary)avgs
                                                                                      from employees
                                                                                      group by department_id
                                                                                      order by avgs desc))
                                                                             where r<2))));


select l.location_id,d.department_id,d.department_name
from locations l,departments d
where l.location_id=d.location_id;
----문제6-------o-----------
select job_title
from jobs
where (job_id) in ( select job_id
                    from(   select rownum r,job_id,avgs
                            from(   select avg(salary) avgs ,job_id
                                    from employees
                                    group by job_id
                                    order by avg(salary) desc)
                        )
                        where r<2
                    );
-------추가문제1--
SELECT count(*),department_id,sum(salary)
from employees
group by department_id
having count(*)>4;
--추가문제2----
--select rownum ,count(*),department_id 여기에 카운트써주면 또 기능을 하니깐 별명을로 써줘야지 
select rownum ,c,department_id 
from(select count(*) c,department_id
    from employees
    group by department_id
    order by count(*) desc);
 
 select count(*),department_id
 from employees
 group by department_id
 having count(*)=(select max(count(*))
                    from employees
                    group by department_id);
-----추가문제3---

    