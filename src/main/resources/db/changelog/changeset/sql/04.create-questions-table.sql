-- Таблица вопросов
CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           topic_id INTEGER REFERENCES topics(id),
                           type_id INTEGER REFERENCES question_types(id),
                           question_text TEXT NOT NULL

);