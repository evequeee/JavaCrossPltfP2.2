package ua.edu.chnu.springjpaproject.service;

import ua.edu.chnu.springjpaproject.model.Category;
import ua.edu.chnu.springjpaproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get all categories ordered by books count
    @Transactional(readOnly = true)
    public List<Category> getAllCategoriesOrderedByBooksCount() {
        return categoryRepository.findAllOrderByBooksCount();
    }

    // Get category by ID
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Get category by ID with books
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryByIdWithBooks(Long id) {
        return categoryRepository.findByIdWithBooks(id);
    }

    // Find category by name
    @Transactional(readOnly = true)
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    // Search categories by name
    @Transactional(readOnly = true)
    public List<Category> searchCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    // Save category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update category
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Delete category by ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Check if category exists
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    // Check if category name exists
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).isPresent();
    }
}