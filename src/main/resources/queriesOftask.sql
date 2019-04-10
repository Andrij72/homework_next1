# Query1 Find the most expensive project (based on the salary of all developers).
select *
from projects
       inner join project_developer on projects.id = project_developer.id_project
       inner join developers on project_developer.id_developer = developers.id
group by projects.id
order by sumSalary DESC
LIMIT 1;

# Query2 Calculate total GP of only Java developers.
select sum(salary)
from developers
       inner join skills on developers.developer_id = skills.developer_id
where skills.industry = "JAVA";

#Query3 Addind a field (cost) to the Projects table.
alter table projects
  add cost  int not null;

# Query4 Find the cheapest project based on the cost .
select p.name
from projects p
where p.cost = (select MIN(cost) from projects);

# Query5 Calculate the average RFP of programmers in the cheapest project.
select AVG(d.salary)
from developers d, project_developer pd, projects p
where d.id = pd.id_developer and p.id = pd.id_project and p.cost = (select MIN(cost) from projects);

# Query6 developers field (salary - salary)
alter table developers
  add salary int not null;