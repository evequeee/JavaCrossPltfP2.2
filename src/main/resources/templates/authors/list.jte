@import com.example.project22.model.Author
@import java.util.List
@import java.time.format.DateTimeFormatter

@param List<Author> authors
@param String title = "Автори"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список авторів</h2>
            <a href="/authors/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нового автора</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/authors/search" method="get" class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="name" placeholder="Пошук за ім'ям">
                </div>
                <div class="col-md-4">
                    <input type="text" class="form-control" name="nationality" placeholder="Пошук за національністю">
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                </div>
            </form>
        </div>
    </div>

    @if(authors.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Авторів не знайдено.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Ім'я</th>
                        <th>Прізвище</th>
                        <th>Національність</th>
                        <th>Дата народження</th>
                        <th>Кількість книг</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(Author author : authors)
                        <tr>
                            <td>${author.getId()}</td>
                            <td>${author.getFirstName()}</td>
                            <td>${author.getLastName()}</td>
                            <td>${author.getNationality() != null ? author.getNationality() : "Не вказано"}</td>
                            <td>${author.getBirthDate() != null ? author.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "Не вказано"}</td>
                            <td>
                                <span class="badge bg-primary">${author.getBooks() != null ? author.getBooks().size() : 0}</span>
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/authors/${author.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    <a href="/authors/${author.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${author.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${author.getId()}" action="/authors/${author.getId()}/delete" method="post" class="d-none">
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
