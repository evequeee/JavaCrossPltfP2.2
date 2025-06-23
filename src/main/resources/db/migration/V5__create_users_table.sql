-- Створення таблиці користувачів
CREATE TABLE IF NOT EXISTS users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Індекс для швидкого пошуку по username (додаємо, лише якщо він не існує)
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace
        WHERE c.relname = 'idx_users_username' AND n.nspname = current_schema()
    ) THEN
        CREATE INDEX idx_users_username ON users(username);
    END IF;
END
$$;

-- Додаємо коментарі, лише якщо колонки існують
DO $$
BEGIN
    -- Коментар до таблиці
    COMMENT ON TABLE users IS 'Таблиця користувачів системи';

    -- Коментарі до колонок
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'id') THEN
        COMMENT ON COLUMN users.id IS 'Унікальний ідентифікатор користувача';
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'username') THEN
        COMMENT ON COLUMN users.username IS 'Ім''я користувача (логін)';
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'password') THEN
        COMMENT ON COLUMN users.password IS 'Хешований пароль користувача';
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'role') THEN
        COMMENT ON COLUMN users.role IS 'Роль користувача (USER, ADMIN)';
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'created_at') THEN
        COMMENT ON COLUMN users.created_at IS 'Дата та час створення запису';
    END IF;

    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'updated_at') THEN
        COMMENT ON COLUMN users.updated_at IS 'Дата та час останнього оновлення';
    END IF;
END
$$;
