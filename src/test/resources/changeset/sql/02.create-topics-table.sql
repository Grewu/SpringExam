-- Таблица тем
CREATE TABLE topics (
                        id SERIAL PRIMARY KEY,
                        name topic_type NOT NULL,
                        description VARCHAR(255)
);