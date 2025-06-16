@import com.example.project22.model.BookLoan
@import java.util.List
@import java.time.LocalDate
@import java.time.format.DateTimeFormatter

@param List<BookLoan> loans
@param String title = "Позичені книги"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список позичених книг</h2>
            <a href="/loans/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нове позичення</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <div class="row">
                <div class="col-md-8">
                    <form action="/loans/search" method="get" class="row g-3">
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="user" placeholder="Пошук за користувачем">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="book" placeholder="Пошук за книгою">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-4">
                    <div class="btn-group w-100" role="group">
                        <a href="/loans" class="btn btn-outline-secondary">Всі</a>
                        <a href="/loans?status=active" class="btn btn-outline-primary">Активні</a>
                        <a href="/loans?status=overdue" class="btn btn-outline-danger">Прострочені</a>
                        <a href="/loans?status=returned" class="btn btn-outline-success">Повернуті</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    @if(loans.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Позичень не знайдено.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Книга</th>
                        <th>Користувач</th>
                        <th>Дата видачі</th>
                        <th>Термін повернення</th>
                        <th>Дата повернення</th>
                        <th>Статус</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(BookLoan loan : loans)
                        <tr class="${loan.getIsReturned() ? "" : (loan.getDueDate().isBefore(LocalDate.now()) ? "table-danger" : "")}">
                            <td>${loan.getId()}</td>
                            <td><a href="/books/${loan.getBook().getId()}">${loan.getBook().getTitle()}</a></td>
                            <td><a href="/users/${loan.getUser().getId()}">${loan.getUser().getFirstName()} ${loan.getUser().getLastName()}</a></td>
                            <td>${loan.getLoanDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                            <td>${loan.getDueDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                            <td>
                                @if(loan.getReturnDate() != null)
                                    ${loan.getReturnDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                                @else
                                    -
                                @endif
                            </td>
                            <td>
                                @if(loan.getIsReturned())
                                    <span class="badge bg-success">Повернуто</span>
                                @elseif(loan.getDueDate().isBefore(LocalDate.now()))
                                    <span class="badge bg-danger">Прострочено</span>
                                @else
                                    <span class="badge bg-warning">В користуванні</span>
                                @endif
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/loans/${loan.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    @if(!loan.getIsReturned())
                                        <a href="/loans/${loan.getId()}/return" class="btn btn-sm btn-success"><i class="bi bi-check-lg"></i> Повернути</a>
                                        <a href="/loans/${loan.getId()}/extend" class="btn btn-sm btn-warning"><i class="bi bi-calendar-plus"></i> Продовжити</a>
                                    @endif
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${loan.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${loan.getId()}" action="/loans/${loan.getId()}/delete" method="post" class="d-none">
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
