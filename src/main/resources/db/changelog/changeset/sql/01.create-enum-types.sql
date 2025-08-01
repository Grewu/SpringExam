-- Создание пользовательского типа ENUM для тем
CREATE TYPE topic_type AS ENUM (
    'ALL_DOMAINS',
    'TESTING',
    'SPRING_CORE',
    'SECURITY',
    'SPRING_AOP',
    'SPRING_MVC',
    'DATA_MANAGEMENT',
    'CONFIGURATION',
    'SPRING_ACTUATOR'
    );

-- Создаем пользовательский тип ENUM для типов вопросов
CREATE TYPE question_type AS ENUM (
    'SINGLE_CHOICE',
    'MULTIPLE_CHOICE'
);