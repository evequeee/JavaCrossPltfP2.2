package ua.edu.chnu.springjpaproject.controller;

import ua.edu.chnu.springjpaproject.model.Category;
import ua.edu.chnu.springjpaproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /api/categories - Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // GET /api/categories/ordered-by-books - Get categories ordered by books count
    @GetMapping("/ordered-by-books")
    public ResponseEntity<List<Category>> getAllCategoriesOrderedByBooksCount() {
        List<Category> categories = categoryService.getAllCategoriesOrderedByBooksCount();
        return ResponseEntity.ok(categories);
    }

    // GET /api/categories/{id} - Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/categories/{id}/with-books - Get category by ID with books
    @GetMapping("/{id}/with-books")
    public ResponseEntity<Category> getCategoryByIdWithBooks(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryByIdWithBooks(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/categories/search?name=... - Search categories by name
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategoriesByName(@RequestParam String name) {
        List<Category> categories = categoryService.searchCategoriesByName(name);
        return ResponseEntity.ok(categories);
    }

    // GET /api/categories/name/{name} - Find category by exact name
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> findCategoryByName(@PathVariable String name) {
        Optional<Category> category = categoryService.findCategoryByName(name);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/categories - Create new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            // Check if category name already exists
            if (categoryService.existsByName(category.getName())) {
                return ResponseEntity.badRequest().build();
            }

            Category savedCategory = categoryService.saveCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/categories/{id} - Update category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        category.setId(id);
        try {
            Category updatedCategory = categoryService.updateCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/categories/{id} - Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}