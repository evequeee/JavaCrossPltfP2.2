@import com.example.project22.model.Artist
@import java.util.List

@param List<Artist> artists
@param String title = "Виконавці"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список виконавців</h2>
            <a href="/artists/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати нового виконавця</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/artists/search" method="get" class="row g-3">
                <div class="col-md-5">
                    <input type="text" class="form-control" name="name" placeholder="Пошук за назвою">
                </div>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="country" placeholder="Пошук за країною">
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                </div>
            </form>
        </div>
    </div>

    @if(artists.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Виконавці не знайдені.
        </div>
    @else
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Назва</th>
                        <th>Країна</th>
                        <th>Рік заснування</th>
                        <th>Кількість платівок</th>
                        <th>Дії</th>
                    </tr>
                </thead>
                <tbody>
                    @for(Artist artist : artists)
                        <tr>
                            <td>${artist.getId()}</td>
                            <td><a href="/artists/${artist.getId()}">${artist.getName()}</a></td>
                            <td>${artist.getCountry() != null ? artist.getCountry() : "Не вказано"}</td>
                            <td>${artist.getFormationDate() != null ? artist.getFormationDate().getYear() : "Не вказано"}</td>
                            <td>${artist.getRecords() != null ? artist.getRecords().size() : 0}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="/artists/${artist.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
                                    <a href="/artists/${artist.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn btn-sm btn-danger"
                                            onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${artist.getId()}').submit(); }">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                    <form id="delete-form-${artist.getId()}" action="/artists/${artist.getId()}/delete" method="post" class="d-none">
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
