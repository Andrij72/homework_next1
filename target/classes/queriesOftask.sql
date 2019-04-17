# Query1 Find the most expensive project (based on the salary of all developers).
SELECT *FROM projects
INNER JOIN project_developer ON projects.id = project_developer.id_project
INNER JOIN developers ON project_developer.id_developer = developers.id
GROUP BY projects.id
GROUP BY sumSalary DESC
LIMIT 1;

# Query2 Calculate total GP of only Java developers.
SELECT sum(salary) FROM developers
INNER JOIN skills ON developers.developer_id = skills.developer_id
WHERE skills.industry = "JAVA";

#Query3 Addind a field (cost) to the Projects table.
ALTER TABLE   projects
 ADD cost  int not null;

# Query4 Find the cheapest project based ON the cost .
SELECT p.name FROM projects p
WHERE p.cost = (SELECT MIN(cost) FROM projects);

# Query5 Calculate the average RFP of programmers in the cheapest project.
SELECT AVG(d.salary)
FROM developers d, project_developer pd, projects p
WHERE d.id = pd.id_developer AND p.id = pd.id_project AND p.cost = (SELECT MIN(cost) FROM projects);

# Query6 developers field (salary - salary)
ALTER TABLE developers
ADD salary int not null;
