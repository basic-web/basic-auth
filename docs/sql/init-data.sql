INSERT INTO roles (id, name, created_time)
VALUES ('role_user', '系统用户', now());
INSERT INTO roles (id, name, created_time)
VALUES ('role_admin', '超级管理员', now());

INSERT INTO users (id, username, password, status, created_time)
VALUES ('admin', 'admin', '7f3b41c9487adc40b70c7ac38dc7990a', 'NORMAL', now());
INSERT INTO user_facts (id, user_id, name, value, created_time)
VALUES (uuid(), 'admin', 'DISPLAY_NAME', '管理员', now());
INSERT INTO role_users (role_id, user_id) VALUES ('role_user', 'admin');
INSERT INTO role_users (role_id, user_id) VALUES ('role_admin', 'admin');

INSERT INTO menus (id, name, icon, order_num, created_time)
VALUES ('home', '主页', 'fa fa-home', 1, now());
INSERT INTO menus (id, name, icon, order_num, created_time)
VALUES ('system', '系统管理', 'fa fa-gears', 100, now());

INSERT INTO resources (id, name, pattern, created_time)
VALUES ('dashboard', 'Dashboard', '/dashboard', now());
INSERT INTO role_resources (role_id, resource_id) VALUES ('role_user', 'dashboard');
INSERT INTO menu_resources (id, menu_id, resource_id, order_num, created_time)
VALUES ('home_dashboard', 'home', 'dashboard', 1, now());

INSERT INTO resources (id, name, pattern, created_time)
VALUES ('users', '用户管理', '/users', now());
INSERT INTO role_resources (role_id, resource_id) VALUES ('role_admin', 'users');
INSERT INTO menu_resources (id, menu_id, resource_id, order_num, created_time)
VALUES ('system_users', 'system', 'users', 1, now());

INSERT INTO resources (id, name, pattern, created_time)
VALUES ('roles', '角色管理', '/roles', now());
INSERT INTO role_resources (role_id, resource_id) VALUES ('role_admin', 'roles');
INSERT INTO menu_resources (id, menu_id, resource_id, order_num, created_time)
VALUES ('system_roles', 'system', 'roles', 2, now());

INSERT INTO resources (id, name, pattern, created_time)
VALUES ('resources', '资源管理', '/resources', now());
INSERT INTO role_resources (role_id, resource_id) VALUES ('role_admin', 'resources');
INSERT INTO menu_resources (id, menu_id, resource_id, order_num, created_time)
VALUES ('system_resources', 'system', 'resources', 3, now());

INSERT INTO resources (id, name, pattern, created_time)
VALUES ('menus', '菜单管理', '/menus', now());
INSERT INTO role_resources (role_id, resource_id) VALUES ('role_admin', 'menus');
INSERT INTO menu_resources (id, menu_id, resource_id, order_num, created_time)
VALUES ('system_menus', 'system', 'menus', 4, now());