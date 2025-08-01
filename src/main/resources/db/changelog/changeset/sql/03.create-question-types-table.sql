-- Таблица типов вопросов
CREATE TABLE question_types (
                                id SERIAL PRIMARY KEY,
                                type_name question_type NOT NULL,
                                description VARCHAR(255)
);