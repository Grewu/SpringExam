-- Таблица ответов
CREATE TABLE answers (
                         id SERIAL PRIMARY KEY,
                         question_id INTEGER REFERENCES questions(id) ON DELETE CASCADE,
                         answer_text TEXT NOT NULL,
                         is_correct BOOLEAN NOT NULL
);