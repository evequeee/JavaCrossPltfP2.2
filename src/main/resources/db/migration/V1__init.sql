-- Create authors table
CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         birth_date DATE,
                         nationality VARCHAR(100),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create categories table
CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE,
                            description TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create books table
CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       isbn VARCHAR(20) UNIQUE,
                       publication_year INTEGER,
                       pages INTEGER,
                       description TEXT,
                       author_id BIGINT NOT NULL,
                       category_id BIGINT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id),
                       CONSTRAINT fk_books_category FOREIGN KEY (category_id) REFERENCES categories(id)
);