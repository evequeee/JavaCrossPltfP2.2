package ua.edu.chnu.springjpaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Book;
import ua.edu.chnu.springjpaproject.model.Category;
import ua.edu.chnu.springjpaproject.service.AuthorService;
import ua.edu.chnu.springjpaproject.service.BookService;
import ua.edu.chnu.springjpaproject.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private static final Logger logger = Logger.getLogger(BookWebController.class.getName());

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookWebController(BookService bookService,
                             AuthorService authorService,
                             CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    // Books list page
    @GetMapping
    public String listBooks(Model model) {
        try {
            logger.info("Getting list of all books");
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books != null ? books : new ArrayList<>());
            return "books";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting list of books", e);
            model.addAttribute("books", new ArrayList<>());
            model.addAttribute("errorMessage", "Error loading books: " + e.getMessage());
            return "books";
        }
    }

    // Add new book page
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        try {
            logger.info("Displaying add book form");

            // Get list of authors
            logger.info("Getting list of authors");
            List<Author> authors = authorService.getAllAuthors();
            logger.info("Authors received: " + (authors != null ? authors.size() : 0));

            // Get list of categories
            logger.info("Getting list of categories");
            List<Category> categories = categoryService.getAllCategories();
            logger.info("Categories received: " + (categories != null ? categories.size() : 0));

            // Add lists to model
            model.addAttribute("authors", authors != null ? authors : new ArrayList<>());
            model.addAttribute("categories", categories != null ? categories : new ArrayList<>());

            return "book-add";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error displaying add book form", e);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("authors", new ArrayList<>());
            model.addAttribute("categories", new ArrayList<>());
            return "book-add";
        }
    }

    // Add new book form submission
    @PostMapping("/add")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("isbn") String isbn,
                          @RequestParam(value = "publicationYear", required = false) Integer publicationYear,
                          @RequestParam(value = "pages", required = false) Integer pages,
                          @RequestParam("authorId") Long authorId,
                          @RequestParam("categoryId") Long categoryId,
                          @RequestParam(value = "description", required = false) String description,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        try {
            logger.info("Adding new book: " + title);

            // Get author
            logger.info("Getting author with ID: " + authorId);
            Optional<Author> author = authorService.getAuthorById(authorId);
            if (author.isEmpty()) {
                throw new RuntimeException("Author with ID " + authorId + " not found");
            }

            // Get category
            logger.info("Getting category with ID: " + categoryId);
            Optional<Category> category = categoryService.getCategoryById(categoryId);
            if (category.isEmpty()) {
                throw new RuntimeException("Category with ID " + categoryId + " not found");
            }

            // Create new book
            Book book = new Book();
            book.setTitle(title);
            book.setIsbn(isbn != null && !isbn.trim().isEmpty() ? isbn : null);
            book.setPublicationYear(publicationYear);
            book.setPages(pages);
            book.setAuthor(author.get());
            book.setCategory(category.get());
            book.setDescription(description != null && !description.trim().isEmpty() ? description : null);

            // Save book to database
            logger.info("Saving book to database");
            bookService.saveBook(book);

            // Add success message
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book \"" + title + "\" successfully added!");

            return "redirect:/books";

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding book", e);

            // In case of error, return to form with error message
            try {
                List<Author> authors = authorService.getAllAuthors();
                List<Category> categories = categoryService.getAllCategories();

                model.addAttribute("authors", authors != null ? authors : new ArrayList<>());
                model.addAttribute("categories", categories != null ? categories : new ArrayList<>());
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error getting authors and categories", ex);
                model.addAttribute("authors", new ArrayList<>());
                model.addAttribute("categories", new ArrayList<>());
            }

            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "book-add";
        }
    }

    // View book details page
    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Viewing details of book with ID: " + id);

            Optional<Book> bookOpt = bookService.getBookById(id);
            if (bookOpt.isEmpty()) {
                logger.warning("Book with ID " + id + " not found");
                redirectAttributes.addFlashAttribute("errorMessage", "Book with ID " + id + " not found");
                return "redirect:/books";
            }

            model.addAttribute("book", bookOpt.get());
            return "book-view";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error viewing book details", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error viewing book details: " + e.getMessage());
            return "redirect:/books";
        }
    }

    // Edit book page
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Displaying edit form for book with ID: " + id);

            Optional<Book> bookOpt = bookService.getBookById(id);
            if (bookOpt.isEmpty()) {
                logger.warning("Book with ID " + id + " not found");
                redirectAttributes.addFlashAttribute("errorMessage", "Book with ID " + id + " not found");
                return "redirect:/books";
            }

            List<Author> authors = authorService.getAllAuthors();
            List<Category> categories = categoryService.getAllCategories();

            model.addAttribute("book", bookOpt.get());
            model.addAttribute("authors", authors != null ? authors : new ArrayList<>());
            model.addAttribute("categories", categories != null ? categories : new ArrayList<>());

            return "book-edit";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error displaying edit form for book", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error displaying edit form for book: " + e.getMessage());
            return "redirect:/books";
        }
    }

    // Edit book form submission
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id,
                            @RequestParam("title") String title,
                            @RequestParam("isbn") String isbn,
                            @RequestParam(value = "publicationYear", required = false) Integer publicationYear,
                            @RequestParam(value = "pages", required = false) Integer pages,
                            @RequestParam("authorId") Long authorId,
                            @RequestParam("categoryId") Long categoryId,
                            @RequestParam(value = "description", required = false) String description,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            logger.info("Updating book with ID: " + id);

            // Check if book exists
            Optional<Book> bookOpt = bookService.getBookById(id);
            if (bookOpt.isEmpty()) {
                logger.warning("Book with ID " + id + " not found");
                redirectAttributes.addFlashAttribute("errorMessage", "Book with ID " + id + " not found");
                return "redirect:/books";
            }

            Book book = bookOpt.get();

            // Get author and category
            Optional<Author> author = authorService.getAuthorById(authorId);
            if (author.isEmpty()) {
                throw new RuntimeException("Author with ID " + authorId + " not found");
            }

            Optional<Category> category = categoryService.getCategoryById(categoryId);
            if (category.isEmpty()) {
                throw new RuntimeException("Category with ID " + categoryId + " not found");
            }

            // Update book details
            book.setTitle(title);
            book.setIsbn(isbn != null && !isbn.trim().isEmpty() ? isbn : null);
            book.setPublicationYear(publicationYear);
            book.setPages(pages);
            book.setAuthor(author.get());
            book.setCategory(category.get());
            book.setDescription(description != null && !description.trim().isEmpty() ? description : null);

            // Save updated book
            bookService.updateBook(book);

            redirectAttributes.addFlashAttribute("successMessage", "Book \"" + title + "\" successfully updated");
            return "redirect:/books";

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating book", e);

            // In case of error, return to form with message
            try {
                Optional<Book> bookOpt = bookService.getBookById(id);
                List<Author> authors = authorService.getAllAuthors();
                List<Category> categories = categoryService.getAllCategories();

                model.addAttribute("book", bookOpt.orElse(new Book()));
                model.addAttribute("authors", authors != null ? authors : new ArrayList<>());
                model.addAttribute("categories", categories != null ? categories : new ArrayList<>());
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error getting data for edit form", ex);
            }

            model.addAttribute("errorMessage", "Error updating book: " + e.getMessage());
            return "book-edit";
        }
    }

    // Delete book
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Deleting book with ID: " + id);

            // Check if book exists
            Optional<Book> bookOpt = bookService.getBookById(id);
            if (bookOpt.isEmpty()) {
                logger.warning("Book with ID " + id + " not found");
                redirectAttributes.addFlashAttribute("errorMessage", "Book with ID " + id + " not found");
                return "redirect:/books";
            }

            Book book = bookOpt.get();
            String bookTitle = book.getTitle();

            // Delete book
            bookService.deleteBook(id);

            redirectAttributes.addFlashAttribute("successMessage", "Book \"" + bookTitle + "\" successfully deleted");
            return "redirect:/books";

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting book", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting book: " + e.getMessage());
            return "redirect:/books";
        }
    }
}
