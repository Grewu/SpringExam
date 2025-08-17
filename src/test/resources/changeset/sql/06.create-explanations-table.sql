-- Таблица теория по каждому из вопросов
CREATE TABLE explanations (
                              id SERIAL PRIMARY KEY,
                              question_id INTEGER REFERENCES questions(id),
                              content TEXT NOT NULL
);