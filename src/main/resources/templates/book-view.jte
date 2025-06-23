@import ua.edu.chnu.springjpaproject.model.Book
@param String title = "Book Details"
@param String currentUser = null
@param Book book
@param String errorMessage = null

@template.layout(
title = title,
currentUser = currentUser,
content = @`
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col">
                <h1>Book Details</h1>
            </div>
            <div class="col-auto">
                <a href="/books" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Back to List
                </a>
                <a href="/books/edit/${book.getId()}" class="btn btn-warning">
                    <i class="fas fa-edit"></i> Edit
                </a>
            </div>
        </div>

        @if(errorMessage != null)
            <div class="alert alert-danger">${errorMessage}</div>
        @endif

        <div class="card">
            <div class="card-header bg-primary text-white">
                <h2>${book.getTitle()}</h2>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-8">
                        <table class="table">
                            <tr>
                                <th style="width: 150px;">ID:</th>
                                <td>${book.getId()}</td>
                            </tr>
                            <tr>
                                <th>Author:</th>
                                <td>
                                    @if(book.getAuthor() != null)
                                        ${book.getAuthor().getFirstName()} ${book.getAuthor().getLastName()}
                                    @else
                                        Not specified
                                    @endif
                                </td>
                            </tr>
                            <tr>
                                <th>Category:</th>
                                <td>
                                    @if(book.getCategory() != null)
                                        ${book.getCategory().getName()}
                                    @else
                                        Not specified
                                    @endif
                                </td>
                            </tr>
                            <tr>
                                <th>Publication Year:</th>
                                <td>
                                    @if(book.getPublicationYear() != null)
                                        ${book.getPublicationYear()}
                                    @else
                                        Not specified
                                    @endif
                                </td>
                            </tr>
                            <tr>
                                <th>ISBN:</th>
                                <td>${book.getIsbn() != null ? book.getIsbn() : "Not specified"}</td>
                            </tr>
                            <tr>
                                <th>Number of Pages:</th>
                                <td>
                                    @if(book.getPages() != null)
                                        ${book.getPages()}
                                    @else
                                        Not specified
                                    @endif
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-3">
                            <div class="card-header">Information</div>
                            <div class="card-body">
                                <p><strong>Creation Date:</strong>
                                    @if(book.getCreatedAt() != null)
                                        ${book.getCreatedAt().toLocalDate().toString()}
                                    @else
                                        Not specified
                                    @endif
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card mt-3">
                    <div class="card-header">Description</div>
                    <div class="card-body">
                        @if(book.getDescription() != null && !book.getDescription().isEmpty())
                            <p>${book.getDescription()}</p>
                        @else
                            <p class="text-muted">No description available</p>
                        @endif
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <form action="/books/delete/${book.getId()}" method="post" style="display:inline;"
                      onsubmit="return confirm('Are you sure you want to delete this book?')">
                    <button type="submit" class="btn btn-danger">
                        <i class="fas fa-trash"></i> Delete Book
                    </button>
                </form>
            </div>
        </div>
    </div>
`
)
