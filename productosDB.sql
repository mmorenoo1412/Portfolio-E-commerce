
CREATE TABLE productos (

id SERIAL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
precio NUMERIC(10,2) NOT NULL
);

CREATE TABLE usuarios (

username varchar(100) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
rol varchar(100) not null
);

INSERT INTO usuarios (username, password, rol) VALUES
('admin', '$2a$10$K686/j/KxnKXjmfQmg28LuRFtMbsB5H5Hsf3pqQXLaPNxCOW2Yy4i', 'ROLE_ADMIN'),
('user', '$2a$10$w2hPILt.xtsBUR8hpjX2rOsGcdz/CGPFgFP5O70Z4/HjLfTf95K/S', 'ROLE_USER')
