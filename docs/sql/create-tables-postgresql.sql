CREATE TABLE users (
  id           VARCHAR(36) NOT NULL,
  username     VARCHAR(50) NOT NULL UNIQUE,
  password     VARCHAR(50) DEFAULT NULL,
  status       VARCHAR(20) NOT NULL,
  created_time TIMESTAMP   NOT NULL,
  updated_time TIMESTAMP   DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_facts (
  id           VARCHAR(36)  NOT NULL,
  user_id      VARCHAR(36)  NOT NULL REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  name         VARCHAR(100) NOT NULL,
  value        TEXT      DEFAULT NULL,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE resources (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL,
  pattern      VARCHAR(100) NOT NULL,
  method       VARCHAR(20)  NOT NULL,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL UNIQUE,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE role_resources (
  role_id     VARCHAR(36) NOT NULL REFERENCES roles (id),
  resource_id VARCHAR(36) NOT NULL REFERENCES resources (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  PRIMARY KEY (role_id, resource_id)
);

CREATE TABLE role_users (
  role_id VARCHAR(36) NOT NULL REFERENCES roles (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  user_id VARCHAR(36) NOT NULL REFERENCES users (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  PRIMARY KEY (role_id, user_id)
);

CREATE TABLE menus (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL UNIQUE,
  icon         VARCHAR(20) DEFAULT NULL,
  order_num    SMALLINT    DEFAULT 1,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP   DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE menu_resources (
  id           VARCHAR(36) NOT NULL,
  menu_id      VARCHAR(36) NOT NULL REFERENCES menus (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  resource_id  VARCHAR(36) NOT NULL REFERENCES resources (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  order_num    SMALLINT  DEFAULT 1,
  created_time TIMESTAMP   NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);