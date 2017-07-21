----------------------SELECT/FROM절-----------------------
select * from employees;
select employee_id, first_name, hire_date from employees;
select last_name, phone_number, hire_date, salary from employees;
select first_name,
       last_name, 
       salary, 
       phone_number, 
       email, 
       hire_date 
from employees;
select first_name"성", last_name 이름 from employees;
select first_name 이름, 
       phone_number 전화번호,
       hire_date 입사일,
       salary 급여
from employees;

select first_name "사원번호 이름"
      ,last_name 성
      ,salary 급여
      ,phone_number 전화번호
      ,email 이메일
      ,hire_date 입사일
from employees;

select first_name || '  ' || last_name 이름 
      ,salary 급여
from employees;

select first_name, salary, salary*12 s,salary+3000 bonus 
from employees;

select job_id from employees;

select first_name || '-' || last_name 이름
      ,salary 급여
      ,salary*12 연봉
      ,salary*12+5000 "연봉+보너스"
      ,phone_number 폰번호
from employees;

select * from departments;
select department_id, department_name from departments;

--------------------------WHERE절------------------------
select first_name
from employees
where employee_id=100;

select first_name || '-' || last_name,salary
from employees
where salary>=1500;

select first_name || '-' || last_name, hire_date
from employees
where hire_date>'07/01/01';

select salary
from employees
where first_name='Lex';
-----(조건이2개일때)-----
select first_name,salary
from employees
where salary >= 14000
and salary<=17000;

select first_name, salary
from employees
where salary <=14000
or salary >=17000;

select first_name,hire_date
from employees
where hire_date>='04/01/01'
and hire_date<='05/12/31';
--------(BETWEEN)---------
select first_name,salary
from employees
where salary between 14000 and 17000;
--------(IN연산자)---------
select first_name, last_name, salary
from employees
where first_name in('Neena','Lex','John');

select first_name, salary
from employees
where salary in(2100,3100,4100,2100);
---------(LIKE연산자)---------
select first_name,last_name,salary
from employees
where first_name like 'L%';

select first_name, salary
from employees
where first_name like '%am';

select first_name, salary
from emlpoyees
where first_name like '_a%';

select first_name, salary
from employees
where first_name like '____a%';

select first_name, salary
from employees
where first_name like '__a_';
------------(NULL)------------
select first_name,salary,commission_pct, salary*commission_pct
from employees
where commission_pct is null;

select first_name,salary, commission_pct
from employees
where commission_pct is not null;

select first_name
from employees
where manager_id is null
and commission_pct is null;

----------------------------ORDER BY절----------------------------
select first_name,salary
from employees
where salary>=9000
and salary<=11000
order by salary asc;

select department_id ,salary,first_name
from employees
order by department_id asc; 

select first_name, salary
from employees
where salary>=1000
order by salary desc;

select department_id,first_name,salary
from employees
order by department_id asc
,salary desc;

------------------------------단일행 함수---------------------------
select email,initcap(email),department_id
from employees
where department_id=100;

select first_name, lower(first_name),upper(first_name)
from employees;

select first_name,substr(first_name,1,3),substr(first_name,-3,2)
from employees
where department_id=100;
----------(LPAD/RPAD)-----------
select first_name,
    lpad(first_name,10,'*'),
    rpad(first_name,10,'*')
    from employees;

select country_name,
    lpad(country_name,10,'*')--글자수보다 많게 잡아야 공백을 채우지.
from countries;
------------(TRIM)---------------
select first_name,ltrim(first_name,'D'),rtrim(first_name,'l')
from employees
where department_id=100;

select ltrim('aaaaabbbabbccacac','ab') -왼쪽부터 a또는b를 지워가다 아닌걸 만나면 그만.
from dual;

select trim('*' from '** Data Base**')  --별표만 짤라
from dual;
------------(REPLACE)--------------
select first_name,
    replace(first_name,'a','*'), --a를 *로 바꿔라
    replace(first_name, substr(first_name,2,3) ,'***')    
from employees
where department_id=100;
------------(ROUND)--------------
select round(123.346,2) "r2",
    round(123.456,0)"r0",
    round(123.456,-1)"r-1"  --저 자리를 0으로
    from dual;

select sysdate
from employees;

select month_between(sysdate, '17/07/03')--7/3일부터 오늘까지 달중에 
from dual;

---------(숫자를 문자로)------------
select first_name,to_char(salary*12,'$999,999.99')as "SALARY"
from employees
where department_id=100;

---------(날짜를 문자로)------------
select sysdate,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')
FROM dual;

select sysdate,to_char(sysdate,'YYYY"년"MM"월"DD"일" HH24:MI:SS')
FROM dual;

select commission_pct,nvl(commission_pct,0)
from employees;

select commission_pct,nvl2(commission_pct,100,0)
from employees;
--문제1
select hire_date
    ,first_name "이름"
    ,salary"월급"
    ,phone_number"전화번호"
from employees
order by hire_date asc;
--문제2
select job_title,max_salary
from jobs
order by max_salary asc;
--문제3 V
--select employee_id
select count(*)
from employees
where manager_id is null;
--문제4 
select job_title,max_salary
from jobs
order by max_salary desc;
--문제5
--select department_name "부서 개수"
select count(*)
from departments;
--문제6 V
select department_name
from departments
order by length(department_name) desc;
--문제7
select count(*)
from departments
where manager_id is null;
--문제8
select  upper(country_name)
from countries
order by country_name asc;
--문제9 x
select region_id,region_name
from regions
order by length(region_name) asc;
--문제10 
select lower(city)
from locations
order by city asc;