INSERT INTO sec_roles (id, name, status) VALUES
  (1,'ROLE_ADMIN', 'ACT'),
  (2,'ROLE_BUSINESS_MANAGER', 'ACT'),
  (3,'ROLE_AREA_MANAGER', 'ACT'),
  (4,'ROLE_TEAM_LEADER', 'ACT'),
  (5,'ROLE_TASK_MANAGER', 'ACT');

INSERT INTO sec_permissions (id, name, status) VALUES
  (1,'ALL_PERMISSIONS', 'ACT');

INSERT INTO sec_users (id, created_at, updated_at, "username", email, "password", status)
	   VALUES(1, now(), null, 'Administrador', 'diego1503bsc@gmail.com',
	  '$2a$13$HaVk8.fvlrvUa.uKcFWd9.NuaIkbUSeAZs8quDtPzAIyLACjGG9qa', 'ACT'::character varying);

INSERT INTO public.sec_user_rol (user_id, rol_id) values
			((select id from sec_users where username = 'Administrador'),
			 (select id from sec_roles where name = 'ROLE_ADMIN'));

INSERT INTO public.sec_rol_permissions (rol_id, permission_id) values
			((select id from sec_roles where name = 'ROLE_ADMIN'),
			 (select id from sec_permissions where name = 'ALL_PERMISSIONS'));
