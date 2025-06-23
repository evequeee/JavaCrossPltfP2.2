-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraint for category name
    CONSTRAINT chk_category_name_not_empty
    CHECK (LENGTH(TRIM(name)) > 0)
);

-- Add category_id column to books table if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'books' AND column_name = 'category_id'
    ) THEN
        ALTER TABLE books ADD COLUMN category_id BIGINT REFERENCES categories(id);
    END IF;
END $$;
