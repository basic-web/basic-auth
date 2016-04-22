INSERT INTO users (id, username, password, status, created_time)
VALUES ('58bb4399-084f-11e6-8927-c03fd592b5f9', 'admin', '7f3b41c9487adc40b70c7ac38dc7990a', 'NORMAL', now());
INSERT INTO user_facts (id, user_id, name, value, created_time)
VALUES (uuid(), '58bb4399-084f-11e6-8927-c03fd592b5f9', 'DISPLAY_NAME', '管理员', now());