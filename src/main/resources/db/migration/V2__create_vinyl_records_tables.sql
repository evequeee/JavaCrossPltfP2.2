-- Створення таблиці виконавців замість авторів
CREATE TABLE IF NOT EXISTS artists (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100),
    formation_date DATE,
    biography TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraint для назви виконавця
    CONSTRAINT chk_artist_name_not_empty
    CHECK (LENGTH(TRIM(name)) > 0)
);

-- Таблиця жанрів залишається, але використовується для платівок
CREATE TABLE IF NOT EXISTS genres (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraint для назви жанру
    CONSTRAINT chk_genre_name_not_empty
    CHECK (LENGTH(TRIM(name)) > 0)
);

-- Створення таблиці видавців залишається майже незмінною
CREATE TABLE IF NOT EXISTS publishers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    address TEXT,
    website VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    established_year INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_publisher_name_not_empty
    CHECK (LENGTH(TRIM(name)) > 0),
    CONSTRAINT chk_established_year_valid
    CHECK (established_year IS NULL OR (established_year >= 1900 AND established_year <= EXTRACT(YEAR FROM CURRENT_DATE)))
);

-- Створення таблиці платівок замість книг
CREATE TABLE IF NOT EXISTS records (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    catalog_number VARCHAR(50) UNIQUE,
    release_year INTEGER,
    track_count INTEGER,
    format VARCHAR(50) DEFAULT 'LP',
    description TEXT,
    cover_image_url VARCHAR(500),
    price DECIMAL(10,2),
    available_copies INTEGER DEFAULT 0,
    total_copies INTEGER DEFAULT 0,

    -- Foreign Keys
    artist_id BIGINT REFERENCES artists(id) ON DELETE SET NULL,
    publisher_id BIGINT REFERENCES publishers(id) ON DELETE SET NULL,

    -- Timestamps
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_record_title_not_empty
    CHECK (LENGTH(TRIM(title)) > 0),
    CONSTRAINT chk_track_count_positive
    CHECK (track_count IS NULL OR track_count > 0),
    CONSTRAINT chk_copies_valid
    CHECK (available_copies >= 0 AND total_copies >= 0 AND available_copies <= total_copies),
    CONSTRAINT chk_price_positive
    CHECK (price IS NULL OR price >= 0),
    CONSTRAINT chk_release_year_valid
    CHECK (release_year IS NULL OR (release_year >= 1900 AND release_year <= EXTRACT(YEAR FROM CURRENT_DATE) + 1))
);

-- Створення таблиці зв'язку платівок та жанрів (many-to-many)
CREATE TABLE IF NOT EXISTS record_genres (
    record_id BIGINT NOT NULL REFERENCES records(id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (record_id, genre_id)
);

-- Створення таблиці користувачів бібліотеки - залишається незмінною
CREATE TABLE IF NOT EXISTS library_users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    date_of_birth DATE,
    registration_date DATE DEFAULT CURRENT_DATE,
    membership_expires DATE,
    is_active BOOLEAN DEFAULT TRUE,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_user_name_not_empty
    CHECK (LENGTH(TRIM(first_name)) > 0 AND LENGTH(TRIM(last_name)) > 0),
    CONSTRAINT chk_email_format
    CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    CONSTRAINT chk_registration_date_valid
    CHECK (registration_date <= CURRENT_DATE)
);

-- Створення таблиці позичань платівок
CREATE TABLE IF NOT EXISTS record_loans (
    id BIGSERIAL PRIMARY KEY,
    record_id BIGINT NOT NULL REFERENCES records(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES library_users(id) ON DELETE CASCADE,
    loan_date DATE NOT NULL DEFAULT CURRENT_DATE,
    due_date DATE NOT NULL,
    return_date DATE,
    is_returned BOOLEAN DEFAULT FALSE,
    late_fee DECIMAL(10,2) DEFAULT 0.00,
    condition_on_loan VARCHAR(50) DEFAULT 'Good',
    condition_on_return VARCHAR(50),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_loan_dates_valid
    CHECK (due_date > loan_date),
    CONSTRAINT chk_return_date_valid
    CHECK (return_date IS NULL OR return_date >= loan_date),
    CONSTRAINT chk_late_fee_positive
    CHECK (late_fee >= 0),
    CONSTRAINT chk_return_consistency
    CHECK ((is_returned = FALSE AND return_date IS NULL) OR
           (is_returned = TRUE AND return_date IS NOT NULL))
);

-- ============================================
-- СТВОРЕННЯ ІНДЕКСІВ ДЛЯ КРАЩОЇ ПРОДУКТИВНОСТІ
-- ============================================

-- Індекси для таблиці records
CREATE INDEX IF NOT EXISTS idx_records_title ON records(title);
CREATE INDEX IF NOT EXISTS idx_records_catalog ON records(catalog_number);
CREATE INDEX IF NOT EXISTS idx_records_artist ON records(artist_id);
CREATE INDEX IF NOT EXISTS idx_records_publisher ON records(publisher_id);
CREATE INDEX IF NOT EXISTS idx_records_format ON records(format);
CREATE INDEX IF NOT EXISTS idx_records_release_year ON records(release_year);

-- Індекси для таблиці artists
CREATE INDEX IF NOT EXISTS idx_artists_name ON artists(name);
CREATE INDEX IF NOT EXISTS idx_artists_country ON artists(country);

-- Індекси для таблиці library_users
CREATE INDEX IF NOT EXISTS idx_users_email ON library_users(email);
CREATE INDEX IF NOT EXISTS idx_users_full_name ON library_users(last_name, first_name);
CREATE INDEX IF NOT EXISTS idx_users_active ON library_users(is_active);

-- Індекси для таблиці record_loans
CREATE INDEX IF NOT EXISTS idx_loans_user ON record_loans(user_id);
CREATE INDEX IF NOT EXISTS idx_loans_record ON record_loans(record_id);
CREATE INDEX IF NOT EXISTS idx_loans_dates ON record_loans(loan_date, due_date);
CREATE INDEX IF NOT EXISTS idx_loans_returned ON record_loans(is_returned);
CREATE INDEX IF NOT EXISTS idx_loans_overdue ON record_loans(due_date, return_date)
    WHERE is_returned = FALSE;

-- Індекси для таблиці record_genres
CREATE INDEX IF NOT EXISTS idx_record_genres_record ON record_genres(record_id);
CREATE INDEX IF NOT EXISTS idx_record_genres_genre ON record_genres(genre_id);

-- ============================================
-- СТВОРЕННЯ VIEW ДЛЯ ЗРУЧНИХ ЗАПИТІВ
-- ============================================

-- View для повної інформації про платівки
CREATE OR REPLACE VIEW records_full_info AS
SELECT
    r.id,
    r.title,
    r.catalog_number,
    r.release_year,
    r.track_count,
    r.format,
    r.description,
    r.available_copies,
    r.total_copies,
    r.price,

    -- Інформація про виконавця
    a.name AS artist_name,
    a.country AS artist_country,

    -- Інформація про видавця
    p.name AS publisher_name,
    p.website AS publisher_website,

    -- Жанри (як текст)
    STRING_AGG(g.name, ', ' ORDER BY g.name) AS genres,

    r.created_at,
    r.updated_at
FROM records r
LEFT JOIN artists a ON r.artist_id = a.id
LEFT JOIN publishers p ON r.publisher_id = p.id
LEFT JOIN record_genres rg ON r.id = rg.record_id
LEFT JOIN genres g ON rg.genre_id = g.id
GROUP BY r.id, a.name, a.country, p.name, p.website;
