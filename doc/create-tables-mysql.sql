/*
DROP DATABASE IF EXISTS basic-auth;

CREATE DATABASE basic-auth
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE basic-auth;
*/

CREATE TABLE users (
  id           VARCHAR(36) NOT NULL,
  username     VARCHAR(12) NOT NULL UNIQUE,
  password     VARCHAR(50) DEFAULT NULL,
  status       VARCHAR(20) NOT NULL,
  created_time DATETIME    NOT NULL,
  updated_time DATETIME    DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `user_facts` (
  `id`           VARCHAR(36)  NOT NULL,
  `user_id`      VARCHAR(36)  NOT NULL,
  `name`         VARCHAR(100) NOT NULL,
  `value`        LONGTEXT DEFAULT NULL,
  `created_time` DATETIME     NOT NULL,
  `updated_time` DATETIME DEFAULT NULL,
  KEY `k_user_facts_name` (`name`),
  UNIQUE KEY (`user_id`, `name`),
  KEY `fk_user_facts_user` (`user_id`),
  CONSTRAINT `fk_user_facts_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

CREATE TABLE resources (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL,
  pattern      VARCHAR(100) NOT NULL UNIQUE,
  method       VARCHAR(10)  NOT NULL,
  status       VARCHAR(20)  NOT NULL,
  created_time DATETIME     NOT NULL,
  updated_time DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id           VARCHAR(36)  NOT NULL,
  name         VARCHAR(100) NOT NULL UNIQUE,
  status       VARCHAR(20)  NOT NULL,
  created_time DATETIME     NOT NULL,
  updated_time DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE role_resources (
  role_id     VARCHAR(36) NOT NULL,
  resource_id VARCHAR(36) NOT NULL,
  CONSTRAINT `fk_role_resources_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role_resources_resource` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (role_id, resource_id)
);

CREATE TABLE role_users (
  role_id VARCHAR(36) NOT NULL,
  user_id VARCHAR(36) NOT NULL,
  CONSTRAINT `fk_role_users_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role_users_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (role_id, user_id)
);