CREATE TABLE users (
  id bigint(20) AUTO_INCREMENT NOT NULL,
  username varchar(25) NOT NULL,
  password varchar(75) NOT NULL,
  enabled BIT NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);


CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
);


drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

CREATE TABLE authorities (
  username  VARCHAR(255),
  authority varchar(255)
);

CREATE TABLE wallet
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  amount   DOUBLE      NOT NULL,
  currency ENUM('UAH', 'USD', 'EUR'),
  users_id BIGINT  NULL

);
CREATE INDEX FKbs4ogwiknsup4rpw8d47qw9dx
  ON wallet (users_id);

INSERT INTO users (id, enabled, username, password) VALUES (1, true,'dimon', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');

INSERT INTO wallet (id, amount, currency, users_id) VALUES (1, 22.2, 'UAH', 1);
INSERT INTO wallet (id, amount, currency, users_id) VALUES (2, 23.2, 'UAH', 1);
INSERT INTO wallet (id, amount, currency, users_id) VALUES (3, 24.2, 'UAH', 1);

INSERT INTO oauth_client_details VALUES('dima','resource_id', 'secret', 'read,write', 'password,refresh_token', 'http://127.0.0.1', 'ROLE_ADMIN,ROLE_USER', 7200, 0, NULL, 'true');


INSERT INTO authorities (username, authority) VALUES('dimon','ROLE_ADMIN');


-- dimon / password: password