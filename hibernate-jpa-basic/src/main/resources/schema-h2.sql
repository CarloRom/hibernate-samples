-- Eliminar la tabla TBL_TASK si existe
DROP TABLE IF EXISTS TBL_TASK;

-- Eliminar la tabla TBL_SCOPE si existe
DROP TABLE IF EXISTS TBL_SCOPE; -- Eliminar la tabla si existe

-- Eliminar la tabla TBL_EMPLOYEE si existe
DROP TABLE IF EXISTS TBL_EMPLOYEE;

-- Crear la tabla TBL_EMPLOYEE
CREATE TABLE TBL_EMPLOYEE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) DEFAULT NULL
);

-- Crear la tabla TBL_SCOPE
CREATE TABLE TBL_SCOPE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250) DEFAULT NULL
);

-- Crear la tabla TBL_TASK
CREATE TABLE TBL_TASK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(250) NOT NULL,
    task_description VARCHAR(250) NOT NULL,
    employee_id INT, -- Clave foránea para la relación con TBL_EMPLOYEE
    scope_id INT,    -- Clave foránea para la relación con TBL_SCOPE
    FOREIGN KEY (employee_id) REFERENCES TBL_EMPLOYEE(id) ON DELETE CASCADE, -- Relación con TBL_EMPLOYEE
    FOREIGN KEY (scope_id) REFERENCES TBL_SCOPE(id) ON DELETE CASCADE -- Relación con TBL_SCOPE
);
