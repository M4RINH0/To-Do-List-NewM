CREATE TABLE tasks (
    id INTEGER PRIMARY KEY DEFAULT nextval('tasks_id_seq'),
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50)
);
