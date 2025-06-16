@import com.example.project22.model.Record
@import java.util.List

@param List<Record> records
@param String title = "Платівки"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список платівок</h2>
            <a href="/records/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нову платівку</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/records/search" method="get" class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="title" placeholder="Пошук за назвою">
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="artist" placeholder="Пошук за виконавцем">
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

    @if(records.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Платівки не знайдено.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Назва</th>
                        <th>Виконавець</th>
                        <th>Каталожний номер</th>
                        <th>Рік випуску</th>
                        <th>Формат</th>
                        <th>Доступно</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(Record record : records)
                        <tr>
                            <td>${record.getId()}</td>
                            <td><a href="/records/${record.getId()}">${record.getTitle()}</a></td>
                            <td>
                                @if(record.getArtist() != null)
                                    <a href="/artists/${record.getArtist().getId()}">${record.getArtist().getName()}</a>
                                @else
                                    <span class="text-muted">Не вказано</span>
                                @endif
                            </td>
                            <td>${record.getCatalogNumber() != null ? record.getCatalogNumber() : "Не вказано"}</td>
                            <td>${record.getReleaseYear() != null ? String.valueOf(record.getReleaseYear()) : "Не вказано"}</td>
                            <td>${record.getFormat() != null ? record.getFormat() : "Не вказано"}</td>
                            <td>
                                <span class="badge ${record.getAvailableCopies() > 0 ? "bg-success" : "bg-danger"}">
                                    ${record.getAvailableCopies()} / ${record.getTotalCopies()}
                                </span>
                            </td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/records/${record.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    <a href="/records/${record.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${record.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${record.getId()}" action="/records/${record.getId()}/delete" method="post" class="d-none">
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
