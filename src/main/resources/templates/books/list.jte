@import com.example.project22.model.Book
@import java.util.List

@param List<Book> books
@param String title = "Книги"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список книг</h2>
            <a href="/books/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нову книгу</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/books/search" method="get" class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="title" placeholder="Пошук за назвою">
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="author" placeholder="Пошук за автором">
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="genre">
                        <option value="">Всі жанри</option>
                        <!-- Тут буде список жанрів -->
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                </div>
            </form>
        </div>
    </div>

    @if(books.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Книги не знайдено.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Назва</th>
                        <th>Автор</th>
                        <th>ISBN</th>
                        <th>Рік видання</th>
                        <th>Доступно</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(Book book : books)
                        <tr>
                            <td>${book.getId()}</td>
                            <td><a href="/books/${book.getId()}">${book.getTitle()}</a></td>
                            <td>
                                @if(book.getAuthor() != null)
                                    <a href="/authors/${book.getAuthor().getId()}">${book.getAuthor().getFirstName()} ${book.getAuthor().getLastName()}</a>
                                @else
                                    <span class="text-muted">Не вказано</span>
                                @endif
                            </td>
                            <td>${book.getIsbn() != null ? book.getIsbn() : "Не вказано"}</td>
                            <td>${book.getPublicationYear() != null ? String.valueOf(book.getPublicationYear()) : "Не вказано"}</td>
                            <td>
                                <span class="badge ${book.getAvailableCopies() > 0 ? "bg-success" : "bg-danger"}">
                                    ${book.getAvailableCopies()} / ${book.getTotalCopies()}
                                </span>
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/books/${book.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    <a href="/books/${book.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${book.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${book.getId()}" action="/books/${book.getId()}/delete" method="post" class="d-none">
                                        <!-- CSRF токен тут, якщо потрібно -->
                                    </form>
                                </div>
                            </td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        </div>
    @endif
`)
