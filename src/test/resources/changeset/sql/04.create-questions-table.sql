-- Таблица вопросов
CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           topic_id INTEGER REFERENCES topics(id),
                           question_text CHARACTER VARYING(1000) NOT NULL

);