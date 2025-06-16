-- Створення таблиці авторів
CREATE TABLE IF NOT EXISTS authors (
                                       id BIGSERIAL PRIMARY KEY,
                                       first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    nationality VARCHAR(100),
    biography TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraint для повного імені
    CONSTRAINT chk_author_name_not_empty
    CHECK (LENGTH(TRIM(first_name)) > 0 AND LENGTH(TRIM(last_name)) > 0)
    );

-- Створення таблиці жанрів
CREATE TABLE IF NOT EXISTS genres (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraint для назви жанру
    CONSTRAINT chk_genre_name_not_empty
    CHECK (LENGTH(TRIM(name)) > 0)
    );

-- Створення таблиці видавців
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
    CHECK (established_year IS NULL OR (established_year >= 1450 AND established_year <= EXTRACT(YEAR FROM CURRENT_DATE)))
    );

-- Створення таблиці книг
CREATE TABLE IF NOT EXISTS books (
                                     id BIGSERIAL PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    isbn VARCHAR(17) UNIQUE, -- ISBN-13 format: 978-3-16-148410-0
    publication_year INTEGER,
    pages INTEGER,
    language VARCHAR(50) DEFAULT 'Ukrainian',
    description TEXT,
    cover_image_url VARCHAR(500),
    price DECIMAL(10,2),
    available_copies INTEGER DEFAULT 0,
    total_copies INTEGER DEFAULT 0,

    -- Foreign Keys
    author_id BIGINT REFERENCES authors(id) ON DELETE SET NULL,
    publisher_id BIGINT REFERENCES publishers(id) ON DELETE SET NULL,

    -- Timestamps
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_book_title_not_empty
    CHECK (LENGTH(TRIM(title)) > 0),
    CONSTRAINT chk_pages_positive
    CHECK (pages IS NULL OR pages > 0),
    CONSTRAINT chk_copies_valid
    CHECK (available_copies >= 0 AND total_copies >= 0 AND available_copies <= total_copies),
    CONSTRAINT chk_price_positive
    CHECK (price IS NULL OR price >= 0),
    CONSTRAINT chk_publication_year_valid
    CHECK (publication_year IS NULL OR (publication_year >= 1450 AND publication_year <= EXTRACT(YEAR FROM CURRENT_DATE) + 1))
    );

-- Створення таблиці зв'язку книг та жанрів (many-to-many)
CREATE TABLE IF NOT EXISTS book_genres (
                                           book_id BIGINT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, genre_id)
    );

-- Створення таблиці користувачів бібліотеки
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

-- Створення таблиці позичень книг
CREATE TABLE IF NOT EXISTS book_loans (
                                          id BIGSERIAL PRIMARY KEY,
                                          book_id BIGINT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
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

-- Індекси для таблиці books
CREATE INDEX IF NOT EXISTS idx_books_title ON books(title);
CREATE INDEX IF NOT EXISTS idx_books_isbn ON books(isbn);
CREATE INDEX IF NOT EXISTS idx_books_author ON books(author_id);
CREATE INDEX IF NOT EXISTS idx_books_publisher ON books(publisher_id);
CREATE INDEX IF NOT EXISTS idx_books_language ON books(language);
CREATE INDEX IF NOT EXISTS idx_books_publication_year ON books(publication_year);

-- Індекси для таблиці authors
CREATE INDEX IF NOT EXISTS idx_authors_full_name ON authors(last_name, first_name);
CREATE INDEX IF NOT EXISTS idx_authors_nationality ON authors(nationality);

-- Індекси для таблиці library_users
CREATE INDEX IF NOT EXISTS idx_users_email ON library_users(email);
CREATE INDEX IF NOT EXISTS idx_users_full_name ON library_users(last_name, first_name);
CREATE INDEX IF NOT EXISTS idx_users_active ON library_users(is_active);

-- Індекси для таблиці book_loans
CREATE INDEX IF NOT EXISTS idx_loans_user ON book_loans(user_id);
CREATE INDEX IF NOT EXISTS idx_loans_book ON book_loans(book_id);
CREATE INDEX IF NOT EXISTS idx_loans_dates ON book_loans(loan_date, due_date);
CREATE INDEX IF NOT EXISTS idx_loans_returned ON book_loans(is_returned);
CREATE INDEX IF NOT EXISTS idx_loans_overdue ON book_loans(due_date, return_date)
    WHERE is_returned = FALSE;

-- Індекси для таблиці book_genres
CREATE INDEX IF NOT EXISTS idx_book_genres_book ON book_genres(book_id);
CREATE INDEX IF NOT EXISTS idx_book_genres_genre ON book_genres(genre_id);

-- ============================================
-- СТВОРЕННЯ VIEW ДЛЯ ЗРУЧНИХ ЗАПИТІВ
-- ============================================

-- View для повної інформації про книги
CREATE OR REPLACE VIEW books_full_info AS
SELECT
    b.id,
    b.title,
    b.isbn,
    b.publication_year,
    b.pages,
    b.language,
    b.description,
    b.available_copies,
    b.total_copies,
    b.price,

    -- Інформація про автора
    a.first_name AS author_first_name,
    a.last_name AS author_last_name,
    a.nationality AS author_nationality,

    -- Інформація про видавця
    p.name AS publisher_name,
    p.website AS publisher_website,

    -- Жанри (як текст)
    STRING_AGG(g.name, ', ' ORDER BY g.name) AS genres,

    b.created_at,
    b.updated_at
FROM books b
         LEFT JOIN authors a ON b.author_id = a.id
         LEFT JOIN publishers p ON b.publisher_id = p.id
         LEFT JOIN book_genres bg ON b.id = bg.book_id
         LEFT JOIN genres g ON bg.genre_id = g.id
GROUP BY b.id, a.first_name, a.last_name, a.nationality, p.name, p.website;
