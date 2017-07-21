select count(*)--전체row세줌
from employees;

select count(commission_pct),count(manager_id)
from employees;

select * from employees
where salary>16000;

select count(*)--숫자를 세주세요
from employees
where salary>16000;

select count(*),sum(salary)
from employees;

select count(*),sum(salary),avg(salary)
from employees;

select count(*),sum(salary),round( avg( nvl(salary,0) ) ,2 ) ---
from employees;

select count(*),max(salary),min(salary)
from employees;

select * from employees--위에 결과 맞나 확인
order by salary asc;
------------------------------------------
 --이 줄에 그룹바이에 참여한거랑 그룹함수만 쓸수있음
select department_id,avg(salary)
--,first_name--그룹으로 합쳐졌는데 누구꺼 찍을야. 오류뜸
from employees
group by department_id--합쳐
order by department_id asc;

select department_id,count(*),sum(salary)
from employees
where sum(salary)>=20000--이케 못씀
group by department_id;

select department_id,count(*),sum(salary)
from employees
group by department_id
having sum(salary)>20000--그룹별로 합계가 20000이상인거
and department_id=100;

select employee_id,salary,
case 
when job_id='AC_ACCOUNT'THEN salary+salary*0.1
when job_id='AC_MGR'THEN salary+salary*0.2
else salary
end salary2
from employees;

select first_name 이름,
    department_id 부서,
    case when department_id between 10 AND 50 then 'A-TEAM'
         when department_id between 60 and 100 then'B-TEAM'
         when department_id between 110 and 150 then 'C-TEAM'
        else '팀없음'
        end 팀
from employees
ordeR by department_id desc;
-----------------------------------------
--문제1
SELECT max(salary) 최고급여
      ,min(salary) 최저급여
from employees;

select max(salary) || '-' ||min(salary) "최고급여-최저급여"
from employees;
    
--문제2
select max(hire_date),
    to_char(max(hire_date),'YYYY"년"MM"월"DD"일"')
from employees;

select hire_date --맞나 확인용
from employees
order by hire_date desc;

--문제3
select department_id
      ,avg(salary)
      ,max(salary)
      ,min(salary)
from employees
group by department_id
order by avg(salary) desc
        ,max(salary) desc
        ,min(salary) desc;

--문제4
select job_id 
      ,avg(salary) 
      ,max(salary) 
      ,min(salary)
from employees
group by job_id
order by avg(salary) desc
        ,max(salary) desc
        ,min(salary) desc;

--문제5
select to_char(min(hire_date),'YYYY"년"MM"월"DD"일"')
from employees;

--문제6
select department_id
      ,avg(salary)평균급여
      ,min(salary)최저급여
      ,avg(salary)-min(salary) "(평균급여 - 최저급여)"
from employees
group by department_id
having avg(salary)-min(salary)<2000
order by avg(salary) desc,
      min(salary) desc,
      avg(salary)-min(salary) desc;

--문제7
select job_id
      ,max(salary)
      ,min(salary)
      ,max(salary)-min(salary)
from employees
group by job_id
order by max(salary)-min(salary)desc;
------------------------------------
--직원이름 부서명
select e.first_name, d.department_name,e.department_id,d.department_id
from employees e, departments d
where e.department_id=d.department_id;

---직원이름,부서명,업무명
select first_name
    , department_name
    , job_title
from employees em, departments de, jobs jo
where em.department_id=de.department_id
and em.job_id=jo.job_id;

--직원이름,부서코드,매니저이름
select e.EMPLOYEE_ID,e.first_name 직원명,e.manager_id 매니저아이디,m.first_name 매니저이름,m.employee_id 매니저직원아이디
from employees e, employees m
where e.manager_id=m.employee_id;

----문제1
SELECT em.employee_id 직원아이디,em.first_name 직원이름,d.department_name 부서명,m.first_name 매니저이름
from employees em, employees m, departments d
where em.manager_id=m.employee_id
and em.department_id=d.department_id;

---문제2
select r.region_name, c.country_name
from regions r, countries c
where r.region_id=c.REGION_ID
order by r.region_name desc,
    c.country_name desc;

---문제3
select d.department_id, d.department_name, e.first_name, l.city, c.country_name, r.region_name
from departments d, locations l, countries c, regions r ,employees e
where d.LOCATION_ID=l.LOCATION_ID
and l.country_id=c.country_id
and c.region_id=r.region_id
and e.department_id=d.department_id;

---문제4 과거일했던 사람
select h.employee_id, first_name || '-' || last_name, job_title, h.end_date
from jobs j,job_history h, employees e
where h.job_id=j.job_id
and e.employee_id=h.employee_id
and job_title='Public Accountant';
--현재일하는 사람
select employee_id, first_name || '-' || last_name, hire_date, job_title
from jobs j, employees e
where e.job_id=j.job_id
and job_title='Public Accountant';

select job_title
from jobs
where job_title='Public Accountant';

---문제5
select employee_id, first_name, last_name, department_name
from employees e, departments d
where e.department_id=d.department_id
order by last_name asc;

---문제6
select e.employee_id, e.last_name, e.hire_date, m.first_name, m.hire_date
from employees e, employees m
where e.manager_id=m.employee_id
and e.hire_date<m.hire_date;

