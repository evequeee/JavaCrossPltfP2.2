@import com.example.project22.model.Artist
@import com.example.project22.model.Record

@param Artist artist
@param String title = "Інформація про виконавця"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>${artist.getName()}</h2>
            <div>
                <a href="/artists" class="btn btn-outline-secondary me-2"><i class="bi bi-arrow-left"></i> Назад до списку</a>
                <a href="/artists/${artist.getId()}/edit" class="btn btn-warning"><i class="bi bi-pencil"></i> Редагувати</a>
                <button type="button" class="btn btn-danger"
                        onclick="if(confirm('Ви впевнені, що хочете видалити цього виконавця?')) { document.getElementById('delete-form').submit(); }">
                    <i class="bi bi-trash"></i> Видалити
                </button>
                <form id="delete-form" action="/artists/${artist.getId()}/delete" method="post" class="d-none">
                    <!-- CSRF токен тут, якщо потрібно -->
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-header">
                    <h3 class="mb-0">Основна інформація</h3>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <strong>Назва:</strong>
                        <p class="mb-3">${artist.getName()}</p>
                    </div>

                    <div class="mb-3">
                        <strong>Країна:</strong>
                        <p class="mb-3">${artist.getCountry() != null ? artist.getCountry() : "Не вказано"}</p>
                    </div>

                    <div class="mb-3">
                        <strong>Дата заснування:</strong>
                        <p class="mb-3">${artist.getFormationDate() != null ? artist.getFormationDate() : "Не вказано"}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Біографія</h3>
                </div>
                <div class="card-body">
                    @if(artist.getBiography() != null && !artist.getBiography().isEmpty())
                        <p>${artist.getBiography()}</p>
                    @else
                        <p class="text-muted">Біографія відсутня</p>
                    @endif
                </div>
            </div>

            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h3 class="mb-0">Платівки виконавця</h3>
                    <a href="/records/new?artistId=${artist.getId()}" class="btn btn-sm btn-success">
                        <i class="bi bi-plus-circle"></i> Додати платівку
                    </a>
                </div>
                <div class="card-body">
                    @if(artist.getRecords() == null || artist.getRecords().isEmpty())
                        <p class="text-muted">У цього виконавця ще немає платівок у колекції</p>
                    @else
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead class="table-light">
                                    <tr>
                                        <th>Назва</th>
                                        <th>Рік випуску</th>
                                        <th>Формат</th>
                                        <th>Доступно</th>
                                        <th>Дії</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @for(Record record : artist.getRecords())
                                        <tr>
                                            <td><a href="/records/${record.getId()}">${record.getTitle()}</a></td>
                                            <td>${record.getReleaseYear() != null ? record.getReleaseYear() : "Не вказано"}</td>
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
                                                </div>
                                            </td>
                                        </tr>
                                    @endfor
                                </tbody>
                            </table>
                        </div>
                    @endif
                </div>
            </div>
        </div>
    </div>
`)
