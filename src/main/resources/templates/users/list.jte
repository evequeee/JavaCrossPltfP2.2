@import com.example.project22.model.LibraryUser
@import java.util.List
@import java.time.LocalDate
@import java.time.format.DateTimeFormatter

@param List<LibraryUser> users
@param String title = "Користувачі бібліотеки"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список користувачів</h2>
            <a href="/users/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нового користувача</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/users/search" method="get" class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="name" placeholder="Пошук за ім'ям або прізвищем">
                </div>
                <div class="col-md-4">
                    <input type="email" class="form-control" name="email" placeholder="Пошук за email">
                </div>
                <div class="col-md-2">
                    <select class="form-select" name="status">
                        <option value="">Будь-який статус</option>
                        <option value="active">Активні</option>
                        <option value="inactive">Неактивні</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                </div>
            </form>
        </div>
    </div>

    @if(users.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Користувачів не знайдено.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Ім'я</th>
                        <th>Прізвище</th>
                        <th>Email</th>
                        <th>Телефон</th>
                        <th>Дата реєстрації</th>
                        <th>Статус</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(LibraryUser user : users)
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getPhone() != null ? user.getPhone() : "-"}</td>
                            <td>
                                @if(user.getRegistrationDate() != null)
                                    ${user.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                                @else
                                    -
                                @endif
                            </td>
                            <td>
                                @if(user.getIsActive() != null && user.getIsActive())
                                    <span class="badge bg-success">Активний</span>
                                @else
                                    <span class="badge bg-danger">Неактивний</span>
                                @endif

                                @if(user.getMembershipExpires() != null && user.getMembershipExpires().isBefore(LocalDate.now()))
                                    <span class="badge bg-warning">Членство прострочено</span>
                                @endif
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/users/${user.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    <a href="/users/${user.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${user.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${user.getId()}" action="/users/${user.getId()}/delete" method="post" class="d-none">
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
