-- Insert test categories
INSERT INTO categories (name, description) VALUES
                                               ('Fiction', 'Literary works of imaginative narration'),
                                               ('Science Fiction', 'Fiction dealing with futuristic concepts'),
                                               ('Fantasy', 'Fiction involving magical or supernatural elements'),
                                               ('Mystery', 'Fiction dealing with puzzling crimes'),
                                               ('Romance', 'Fiction dealing with love relationships'),
                                               ('Non-Fiction', 'Factual books and educational content');

-- Insert test authors
INSERT INTO authors (first_name, last_name, birth_date, nationality) VALUES
                                                                         ('George', 'Orwell', '1903-06-25', 'British'),
                                                                         ('Isaac', 'Asimov', '1920-01-02', 'American'),
                                                                         ('J.K.', 'Rowling', '1965-07-31', 'British'),
                                                                         ('Stephen', 'King', '1947-09-21', 'American'),
                                                                         ('Jane', 'Austen', '1775-12-16', 'British'),
                                                                         ('Frank', 'Herbert', '1920-10-08', 'American');

-- Insert test books
INSERT INTO books (title, isbn, publication_year, pages, description, author_id, category_id) VALUES
                                                                                                  ('1984', '978-0-452-28423-4', 1949, 328, 'Dystopian social science fiction novel', 1, 1),
                                                                                                  ('Animal Farm', '978-0-452-28424-1', 1945, 112, 'Allegorical novella about farm animals', 1, 1),
                                                                                                  ('Foundation', '978-0-553-29335-0', 1951, 244, 'Science fiction novel about psychohistory', 2, 2),
                                                                                                  ('I, Robot', '978-0-553-29438-8', 1950, 224, 'Collection of nine science fiction short stories', 2, 2),
                                                                                                  ('Harry Potter and the Philosopher''s Stone', '978-0-7475-3269-9', 1997, 223, 'First book in the Harry Potter series', 3, 3),
                                                                                                  ('The Shining', '978-0-385-12167-5', 1977, 447, 'Horror novel about the Overlook Hotel', 4, 4),
                                                                                                  ('Pride and Prejudice', '978-0-14-143951-8', 1813, 279, 'Romantic novel about Elizabeth Bennet', 5, 5),
                                                                                                  ('Dune', '978-0-441-17271-9', 1965, 688, 'Science fiction novel set on the desert planet Arrakis', 6, 2);