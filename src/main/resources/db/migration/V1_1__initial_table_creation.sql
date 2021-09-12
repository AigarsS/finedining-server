CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(20) DEFAULT NULL,
  last_name VARCHAR(20) DEFAULT NULL,
  username VARCHAR(20) DEFAULT NULL,
  password VARCHAR(128) DEFAULT NULL,
  email VARCHAR(120) DEFAULT NULL,
--  enabled SMALLINT NOT NULL DEFAULT 1,
--  role_id BIGINT NOT NULL,
  UNIQUE (username)
--  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS images (
  id UUID PRIMARY KEY,
  name VARCHAR(40),
  type VARCHAR(40),
  data BYTEA
);

CREATE TABLE IF NOT EXISTS product_categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) DEFAULT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) DEFAULT NULL,
  product_category_id BIGINT NOT NULL,
  UNIQUE (name),
  FOREIGN KEY (product_category_id) REFERENCES product_categories (id)
);

CREATE TABLE IF NOT EXISTS units (
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) DEFAULT NULL,
  denotation VARCHAR(10) DEFAULT NULL,
  is_si_unit BOOLEAN DEFAULT false,
  ratio_to_si FLOAT DEFAULT 1,
  CONSTRAINT UC_unit UNIQUE (name, denotation)
);

CREATE TABLE IF NOT EXISTS recipes (
  id SERIAL PRIMARY KEY,
  title VARCHAR(45) DEFAULT NULL,
  description VARCHAR(400) DEFAULT NULL,
  cooking_time INT DEFAULT NULL,
  usage_frequency INT DEFAULT NULL,
  servings_per_recipe INT DEFAULT NULL,
  image_id UUID,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (image_id) REFERENCES images (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS tags (
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) DEFAULT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS tags_recipes (
  id SERIAL PRIMARY KEY,
  recipe_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE IF NOT EXISTS ingredients (
  id SERIAL PRIMARY KEY,
  product_id BIGINT NOT NULL,
  quantity FLOAT DEFAULT NULL,
  unit_id BIGINT NOT NULL,
  recipe_id BIGINT,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (unit_id) REFERENCES units (id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (id)
);

CREATE TABLE IF NOT EXISTS cooking_steps (
  id SERIAL PRIMARY KEY,
  step_number INT DEFAULT NULL,
  description text,
  recipe_id BIGINT DEFAULT NULL,
  image_id UUID,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (image_id) REFERENCES images (id)
);

CREATE TABLE IF NOT EXISTS daily_plans (
  id SERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  target_date DATE DEFAULT NULL,
  recipe_id BIGINT NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS baskets (
  id SERIAL PRIMARY KEY,
  user_id BIGINT DEFAULT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS ingredients_baskets (
  id SERIAL PRIMARY KEY,
  ingredient_id BIGINT DEFAULT NULL,
  status SMALLINT DEFAULT 1,
  basket_id BIGINT DEFAULT NULL,
  FOREIGN KEY (ingredient_id) REFERENCES ingredients (id),
  FOREIGN KEY (basket_id) REFERENCES baskets (id)
);