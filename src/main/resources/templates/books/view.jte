@import com.example.project22.model.Book
@import com.example.project22.model.Genre
@import java.util.Optional

@param Book book
@param String title = "Інформація про книгу"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>${book.getTitle()}</h2>
            <div>
                <a href="/books" class="btn btn-outline-secondary me-2"><i class="bi bi-arrow-left"></i> Назад до списку</a>
                <a href="/books/${book.getId()}/edit" class="btn btn-warning"><i class="bi bi-pencil"></i> Редагувати</a>
                <button type="button" class="btn btn-danger"
                        onclick="if(confirm('Ви впевнені, що хочете видалити цю книгу?')) { document.getElementById('delete-form').submit(); }">
                    <i class="bi bi-trash"></i> Видалити
                </button>
                <form id="delete-form" action="/books/${book.getId()}/delete" method="post" class="d-none">
                    <!-- CSRF токен тут, якщо потрібно -->
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    @if(book.getCoverImageUrl() != null && !book.getCoverImageUrl().isEmpty())
                        <img src="${book.getCoverImageUrl()}" alt="${book.getTitle()}" class="img-fluid rounded mb-3" style="max-height: 300px;">
                    @else
                        <div class="p-5 bg-light rounded mb-3 d-flex align-items-center justify-content-center" style="height: 300px;">
                            <i class="bi bi-book" style="font-size: 5rem;"></i>
                        </div>
                    @endif

                    <div class="mt-3">
                        <h4>Наявність копій</h4>
                        <h5>
                            <span class="badge ${book.getAvailableCopies() > 0 ? "bg-success" : "bg-danger"}">
                                ${book.getAvailableCopies()} доступно з ${book.getTotalCopies()}
                            </span>
                        </h5>
                    </div>

                    @if(book.getAvailableCopies() > 0)
                        <a href="/loans/new?bookId=${book.getId()}" class="btn btn-primary mt-3 w-100">
                            <i class="bi bi-clipboard-plus"></i> Видати читачу
                        </a>
                    @else
                        <button class="btn btn-secondary mt-3 w-100" disabled>
                            <i class="bi bi-clipboard-x"></i> Немає доступних копій
                        </button>
                    @endif
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Інформація про книгу</h3>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">ISBN:</div>
                        <div class="col-md-9">${book.getIsbn() != null ? book.getIsbn() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Автор:</div>
                        <div class="col-md-9">
                            @if(book.getAuthor() != null)
                                <a href="/authors/${book.getAuthor().getId()}">${book.getAuthor().getFirstName()} ${book.getAuthor().getLastName()}</a>
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Видавництво:</div>
                        <div class="col-md-9">
                            @if(book.getPublisher() != null)
                                <a href="/publishers/${book.getPublisher().getId()}">${book.getPublisher().getName()}</a>
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Рік видання:</div>
                        <div class="col-md-9">${book.getPublicationYear() != null ? book.getPublicationYear() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Кількість сторінок:</div>
                        <div class="col-md-9">${book.getPages() != null ? book.getPages() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Мова:</div>
                        <div class="col-md-9">${book.getLanguage() != null ? book.getLanguage() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Ціна:</div>
                        <div class="col-md-9">${book.getPrice() != null ? book.getPrice() + " грн" : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Жанри:</div>
                        <div class="col-md-9">
                            @if(book.getGenres() != null && !book.getGenres().isEmpty())
                                @for(Genre genre : book.getGenres())
                                    <a href="/genres/${genre.getId()}" class="badge bg-primary text-decoration-none me-1">${genre.getName()}</a>
                                @endfor
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Опис</h3>
                </div>
                <div class="card-body">
                    @if(book.getDescription() != null && !book.getDescription().isEmpty())
                        <p>${book.getDescription()}</p>
                    @else
                        <p class="text-muted">Опис відсутній</p>
                    @endif
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <h3 class="mb-0">Історія позичень</h3>
        </div>
        <div class="card-body">
            @if(book.getLoans() == null || book.getLoans().isEmpty())
                <div class="alert alert-info">Ця книга ще не видавалася користувачам.</div>
            @else
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Користувач</th>
                                <th>Дата видачі</th>
                                <th>Термін повернення</th>
                                <th>Дата повернення</th>
                                <th>Статус</th>
                                <th>Дії</th>
                            </tr>
                        </thead>
                        <tbody>
                            @for(var loan : book.getLoans())
                                <tr>
                                    <td>${loan.getId()}</td>
                                    <td>
                                        <a href="/users/${loan.getUser().getId()}">
                                            ${loan.getUser().getFirstName()} ${loan.getUser().getLastName()}
                                        </a>
                                    </td>
                                    <td>${loan.getLoanDate()}</td>
                                    <td>${loan.getDueDate()}</td>
                                    <td>${loan.getReturnDate() != null ? loan.getReturnDate() : "-"}</td>
                                    <td>
                                        @if(loan.getIsReturned())
                                            <span class="badge bg-success">Повернуто</span>
                                        @elseif(loan.getDueDate().isBefore(java.time.LocalDate.now()))
                                            <span class="badge bg-danger">Прострочено</span>
                                        @else
                                            <span class="badge bg-warning">В користуванні</span>
                                        @endif
                                    </td>
                                    <td>
                                        <a href="/loans/${loan.getId()}" class="btn btn-sm btn-info">
                                            <i class="bi bi-eye"></i>
                                        </a>
                                    </td>
                                </tr>
                            @endfor
                        </tbody>
                    </table>
                </div>
            @endif
        </div>
    </div>
`)
