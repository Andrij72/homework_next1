select p.create_date, p.name, count(d.id)
from projects p, project_developer pd, developers d
where p.id = pd.id_project and d.id = pd.id_project
group by p.name;
