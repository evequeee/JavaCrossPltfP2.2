@import ua.edu.chnu.springjpaproject.model.Book
@import java.util.List

@param String title = "Book List"
@param String currentUser = null
@param List<Book> books
@param String successMessage = null
@param String errorMessage = null

@template.layout(
title = title,
currentUser = currentUser,
content = @`
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col">
                <h1>Library Book List</h1>
            </div>
            <div class="col-auto">
                <a href="/books/add" class="btn btn-primary">
                    <i class="fas fa-plus"></i> Add New Book
                </a>
                <a href="/" class="btn btn-secondary">
                    <i class="fas fa-home"></i> Home
                </a>
            </div>
        </div>

        @if(errorMessage != null)
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif

        @if(successMessage != null)
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif

        @if(books.isEmpty())
            <div class="alert alert-info">
                No books in the database yet. Add your first book!
            </div>
        @else
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Year</th>
                            <th>ISBN</th>
                            <th>Pages</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        @for(Book book : books)
                            <tr>
                                <td>${book.getId()}</td>
                                <td>${book.getTitle()}</td>
                                <td>
                                    @if(book.getAuthor() != null)
                                        ${book.getAuthor().getFirstName()} ${book.getAuthor().getLastName()}
                                    @else
                                        -
                                    @endif
                                </td>
                                <td>
                                    @if(book.getCategory() != null)
                                        ${book.getCategory().getName()}
                                    @else
                                        -
                                    @endif
                                </td>
                                <td>
                                    @if(book.getPublicationYear() != null)
                                        ${book.getPublicationYear()}
                                    @else
                                        -
                                    @endif
                                </td>
                                <td>${book.getIsbn() != null ? book.getIsbn() : "-"}</td>
                                <td>
                                    @if(book.getPages() != null)
                                        ${book.getPages()}
                                    @else
                                        -
                                    @endif
                                </td>
                                <td>
                                    <div class="btn-group btn-group-sm">
                                        <a href="/books/view/${book.getId()}" class="btn btn-info" title="View Details">
                                            <i class="fas fa-eye"></i>
                                        </a>
                                        <a href="/books/edit/${book.getId()}" class="btn btn-warning" title="Edit">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <form action="/books/delete/${book.getId()}" method="post" style="display:inline;"
                                              onsubmit="return confirm('Are you sure you want to delete the book ${book.getTitle()}?')">
                                            <button type="submit" class="btn btn-danger" title="Delete">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        @endfor
                    </tbody>
                </table>
            </div>
        @endif
    </div>
`
)
