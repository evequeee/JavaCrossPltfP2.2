@import com.example.project22.model.Record
@import com.example.project22.model.Genre
@import java.util.Optional

@param Record record
@param String title = "Інформація про платівку"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>${record.getTitle()}</h2>
            <div>
                <a href="/records" class="btn btn-outline-secondary me-2"><i class="bi bi-arrow-left"></i> Назад до списку</a>
                <a href="/records/${record.getId()}/edit" class="btn btn-warning"><i class="bi bi-pencil"></i> Редагувати</a>
                <button type="button" class="btn btn-danger"
                        onclick="if(confirm('Ви впевнені, що хочете видалити цю платівку?')) { document.getElementById('delete-form').submit(); }">
                    <i class="bi bi-trash"></i> Видалити
                </button>
                <form id="delete-form" action="/records/${record.getId()}/delete" method="post" class="d-none">
                    <!-- CSRF токен тут, якщо потрібно -->
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    @if(record.getCoverImageUrl() != null && !record.getCoverImageUrl().isEmpty())
                        <img src="${record.getCoverImageUrl()}" alt="${record.getTitle()}" class="img-fluid rounded mb-3" style="max-height: 300px;">
                    @else
                        <div class="p-5 bg-light rounded mb-3 d-flex align-items-center justify-content-center" style="height: 300px;">
                            <i class="bi bi-disc" style="font-size: 5rem;"></i>
                        </div>
                    @endif

                    <div class="mt-3">
                        <h4>Наявність примірників</h4>
                        <h5>
                            <span class="badge ${record.getAvailableCopies() > 0 ? "bg-success" : "bg-danger"}">
                                ${record.getAvailableCopies()} доступно з ${record.getTotalCopies()}
                            </span>
                        </h5>
                    </div>

                    @if(record.getAvailableCopies() > 0)
                        <a href="/loans/new?recordId=${record.getId()}" class="btn btn-primary mt-3 w-100">
                            <i class="bi bi-clipboard-plus"></i> Позичити слухачу
                        </a>
                    @else
                        <button class="btn btn-secondary mt-3 w-100" disabled>
                            <i class="bi bi-clipboard-x"></i> Немає доступних примірників
                        </button>
                    @endif
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Інформація про платівку</h3>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Каталожний номер:</div>
                        <div class="col-md-9">${record.getCatalogNumber() != null ? record.getCatalogNumber() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Виконавець:</div>
                        <div class="col-md-9">
                            @if(record.getArtist() != null)
                                <a href="/artists/${record.getArtist().getId()}">${record.getArtist().getName()}</a>
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Лейбл/Видавець:</div>
                        <div class="col-md-9">
                            @if(record.getPublisher() != null)
                                <a href="/publishers/${record.getPublisher().getId()}">${record.getPublisher().getName()}</a>
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Рік випуску:</div>
                        <div class="col-md-9">${record.getReleaseYear() != null ? record.getReleaseYear() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Кількість треків:</div>
                        <div class="col-md-9">${record.getTrackCount() != null ? record.getTrackCount() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Формат:</div>
                        <div class="col-md-9">${record.getFormat() != null ? record.getFormat() : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Жанри:</div>
                        <div class="col-md-9">
                            @if(record.getGenres() != null && !record.getGenres().isEmpty())
                                @for(Genre genre : record.getGenres())
                                    <a href="/genres/${genre.getId()}" class="badge bg-info text-decoration-none me-1">${genre.getName()}</a>
                                @endfor
                            @else
                                <span class="text-muted">Не вказано</span>
                            @endif
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Ціна:</div>
                        <div class="col-md-9">${record.getPrice() != null ? record.getPrice() + " грн" : "Не вказано"}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-3 fw-bold">Опис:</div>
                        <div class="col-md-9">
                            @if(record.getDescription() != null && !record.getDescription().isEmpty())
                                <p>${record.getDescription()}</p>
                            @else
                                <span class="text-muted">Немає опису</span>
                            @endif
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Історія позичань</h3>
                </div>
                <div class="card-body">
                    @if(record.getLoans() == null || record.getLoans().isEmpty())
                        <p class="text-muted">Ця платівка ще не позичалась</p>
                    @else
                        <div class="table-responsive">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Користувач</th>
                                        <th>Дата позичання</th>
                                        <th>Дедлайн повернення</th>
                                        <th>Статус</th>
                                        <th>Дії</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @for(var loan : record.getLoans())
                                        <tr>
                                            <td>
                                                <a href="/users/${loan.getUser().getId()}">
                                                    ${loan.getUser().getFirstName()} ${loan.getUser().getLastName()}
                                                </a>
                                            </td>
                                            <td>${loan.getLoanDate()}</td>
                                            <td>${loan.getDueDate()}</td>
                                            <td>
                                                @if(loan.getIsReturned())
                                                    <span class="badge bg-success">Повернуто ${loan.getReturnDate()}</span>
                                                @else
                                                    <span class="badge bg-warning">Очікує повернення</span>
                                                @endif
                                            </td>
                                            <td>
                                                <a href="/loans/${loan.getId()}" class="btn btn-sm btn-info">Деталі</a>
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
