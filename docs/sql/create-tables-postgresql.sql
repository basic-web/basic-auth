CREATE TABLE users (
  id           VARCHAR(36) NOT NULL,
  username     VARCHAR(12) NOT NULL UNIQUE,
  password     VARCHAR(50) DEFAULT NULL,
  status       VARCHAR(20) NOT NULL,
  created_time TIMESTAMP   NOT NULL,
  updated_time TIMESTAMP   DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE resources (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL,
  pattern      VARCHAR(100) NOT NULL UNIQUE,
  method       VARCHAR(10)  NOT NULL,
  status       VARCHAR(20)  NOT NULL,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL UNIQUE,
  status       VARCHAR(20)  NOT NULL,
  created_time TIMESTAMP    NOT NULL,
  updated_time TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE role_resources (
  role_id     VARCHAR(36) NOT NULL REFERENCES roles (id),
  resource_id VARCHAR(36) NOT NULL REFERENCES resources (id),
  PRIMARY KEY (role_id, resource_id)
);

CREATE TABLE role_users (
  role_id VARCHAR(36) NOT NULL REFERENCES roles (id),
  user_id VARCHAR(36) NOT NULL REFERENCES users (id),
  PRIMARY KEY (role_id, user_id)
);