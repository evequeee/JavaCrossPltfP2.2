@import com.example.project22.model.Genre
@import java.util.List

@param List<Genre> genres
@param String title = "Жанри"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>Список жанрів</h2>
            <a href="/genres/new" class="btn btn-success"><i class="bi bi-plus-circle"></i> Додати новий жанр</a>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <form action="/genres/search" method="get" class="row g-3">
                <div class="col-md-8">
                    <input type="text" class="form-control" name="name" placeholder="Пошук за назвою">
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary w-100"><i class="bi bi-search"></i> Пошук</button>
                </div>
            </form>
        </div>
    </div>

    @if(genres.isEmpty())
        <div class="alert alert-info">
            <i class="bi bi-info-circle"></i> Жанри не знайдено.
        </div>
    @else
        <div class="row row-cols-1 row-cols-md-3 g-4">
            @for(Genre genre : genres)
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${genre.getName()}</h5>
                            <p class="card-text">
                                @if(genre.getDescription() != null && !genre.getDescription().isEmpty())
                                    ${genre.getDescription()}
                                @else
                                    <span class="text-muted">Опис відсутній</span>
                                @endif
                            </p>
                            <p class="card-text">
                                <small class="text-muted">Кількість книг: ${genre.getBooks() != null ? genre.getBooks().size() : 0}</small>
                            </p>
                        </div>
                        <div class="card-footer bg-transparent">
                            <div class="btn-group w-100" role="group">
                                <a href="/genres/${genre.getId()}" class="btn btn-sm btn-info"><i class="bi bi-eye"></i> Переглянути</a>
                                <a href="/genres/${genre.getId()}/edit" class="btn btn-sm btn-warning"><i class="bi bi-pencil"></i> Редагувати</a>
                                <button type="button" class="btn btn-sm btn-danger"
                                        onclick="if(confirm('Ви впевнені?')) { document.getElementById('delete-form-${genre.getId()}').submit(); }">
                                    <i class="bi bi-trash"></i> Видалити
                                </button>
                                <form id="delete-form-${genre.getId()}" action="/genres/${genre.getId()}/delete" method="post" class="d-none">
                                    <!-- CSRF токен тут, якщо потрібно -->
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            @endfor
        </div>
    @endif
`)
