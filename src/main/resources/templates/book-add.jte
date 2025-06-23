@import ua.edu.chnu.springjpaproject.model.Author
@import ua.edu.chnu.springjpaproject.model.Category
@import java.util.List

@param String title = "Add New Book"
@param String currentUser = null
@param String errorMessage = null
@param List<Author> authors
@param List<Category> categories

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Add New Book</h1>

        @if(errorMessage != null)
            <div class="alert alert-danger">${errorMessage}</div>
        @endif

        <form action="/books/add" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Book Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>

            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" class="form-control" id="isbn" name="isbn">
            </div>

            <div class="mb-3">
                <label for="publicationYear" class="form-label">Publication Year</label>
                <input type="number" class="form-control" id="publicationYear" name="publicationYear">
            </div>

            <div class="mb-3">
                <label for="pages" class="form-label">Number of Pages</label>
                <input type="number" class="form-control" id="pages" name="pages">
            </div>

            <div class="mb-3">
                <label for="author" class="form-label">Author</label>
                <select class="form-control" id="author" name="authorId" required>
                    <option value="">Select Author</option>
                    @for(Author author : authors)
                        <option value="${author.getId()}">${author.getFirstName()} ${author.getLastName()}</option>
                    @endfor
                </select>
            </div>

            <div class="mb-3">
                <label for="category" class="form-label">Category</label>
                <select class="form-control" id="category" name="categoryId" required>
                    <option value="">Select Category</option>
                    @for(Category category : categories)
                        <option value="${category.getId()}">${category.getName()}</option>
                    @endfor
                </select>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description"></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Add Book</button>
        </form>
    </div>
</body>
</html>
