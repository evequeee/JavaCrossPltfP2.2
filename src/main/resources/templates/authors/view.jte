@import com.example.project22.model.Author
@import com.example.project22.model.Book
@import java.time.format.DateTimeFormatter

@param Author author
@param String title = "Інформація про автора"

@template.layouts.default(title = title, content = @`
    <div class="mb-4">
        <div class="d-flex justify-content-between align-items-center">
            <h2>${author.getFirstName()} ${author.getLastName()}</h2>
            <div>
                <a href="/authors" class="btn btn-outline-secondary me-2"><i class="bi bi-arrow-left"></i> Назад до списку</a>
                <a href="/authors/${author.getId()}/edit" class="btn btn-warning"><i class="bi bi-pencil"></i> Редагувати</a>
                <button type="button" class="btn btn-danger"
                        onclick="if(confirm('Ви впевнені, що хочете видалити цього автора?')) { document.getElementById('delete-form').submit(); }">
                    <i class="bi bi-trash"></i> Видалити
                </button>
                <form id="delete-form" action="/authors/${author.getId()}/delete" method="post" class="d-none">
                    <!-- CSRF токен тут, якщо потрібно -->
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="mb-3">
                        <i class="bi bi-person-circle" style="font-size: 5rem;"></i>
                    </div>

                    <h4>${author.getFirstName()} ${author.getLastName()}</h4>
                    @if(author.getNationality() != null)
                        <p class="text-muted">${author.getNationality()}</p>
                    @endif

                    @if(author.getBirthDate() != null)
                        <p>
                            <i class="bi bi-calendar"></i>
                            ${author.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                        </p>
                    @endif
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-header">
                    <h3 class="mb-0">Біографія</h3>
                </div>
                <div class="card-body">
                    @if(author.getBiography() != null && !author.getBiography().isEmpty())
                        <p>${author.getBiography()}</p>
                    @else
                        <p class="text-muted">Біографія відсутня</p>
                    @endif
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <h3 class="mb-0">Книги автора</h3>
        </div>
        <div class="card-body">
            @if(author.getBooks() == null || author.getBooks().isEmpty())
                <div class="alert alert-info">Цей автор ще не має книг у бібліотеці.</div>
            @else
                <div class="row row-cols-1 row-cols-md-3 g-4">
                    @for(Book book : author.getBooks())
                        <div class="col">
                            <div class="card h-100">
                                @if(book.getCoverImageUrl() != null && !book.getCoverImageUrl().isEmpty())
                                    <img src="${book.getCoverImageUrl()}" class="card-img-top" alt="${book.getTitle()}" style="height: 200px; object-fit: cover;">
                                @else
                                    <div class="card-img-top bg-light d-flex align-items-center justify-content-center" style="height: 200px;">
                                        <i class="bi bi-book" style="font-size: 3rem;"></i>
                                    </div>
                                @endif
                                <div class="card-body">
                                    <h5 class="card-title">${book.getTitle()}</h5>
                                    @if(book.getPublicationYear() != null)
                                        <p class="card-text text-muted">${String.valueOf(book.getPublicationYear())}</p>
                                    @endif
                                    <a href="/books/${book.getId()}" class="btn btn-primary">Переглянути</a>
                                </div>
                            </div>
                        </div>
                    @endfor
                </div>
            @endif
        </div>
    </div>
`)
