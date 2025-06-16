@import com.example.project22.model.Record
@import com.example.project22.model.Artist
@import com.example.project22.model.Publisher
@import com.example.project22.model.Genre
@import java.util.List

@param Record record
@param List<Artist> artists
@param List<Publisher> publishers
@param List<Genre> genres

@template.layouts.default(
    title = "Додати нову платівку",
    content = @`
    <div class="container">
        <h1 class="mb-4">Додати нову платівку</h1>

        <form action="/records" method="post">
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="title" class="form-label">Назва*</label>
                    <input type="text" class="form-control" id="title" name="title" value="${record.getTitle() != null ? record.getTitle() : ""}" required>
                </div>
                <div class="col-md-6">
                    <label for="catalogNumber" class="form-label">Каталожний номер</label>
                    <input type="text" class="form-control" id="catalogNumber" name="catalogNumber" value="${record.getCatalogNumber() != null ? record.getCatalogNumber() : ""}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="artistId" class="form-label">Виконавець*</label>
                    <select class="form-control" id="artistId" name="artistId" required>
                        <option value="">-- Оберіть виконавця --</option>
                        @for(Artist artist : artists)
                            <option value="${artist.getId()}">${artist.getName()}</option>
                        @endfor
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="publisherId" class="form-label">Видавець</label>
                    <select class="form-control" id="publisherId" name="publisherId">
                        <option value="">-- Оберіть видавця --</option>
                        @for(Publisher publisher : publishers)
                            <option value="${publisher.getId()}">${publisher.getName()}</option>
                        @endfor
                    </select>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="releaseYear" class="form-label">Рік випуску</label>
                    <input type="number" class="form-control" id="releaseYear" name="releaseYear" value="${record.getReleaseYear() != null ? String.valueOf(record.getReleaseYear()) : ""}">
                </div>
                <div class="col-md-4">
                    <label for="format" class="form-label">Формат</label>
                    <select class="form-control" id="format" name="format">
                        <option value="LP" ${record.getFormat() != null && record.getFormat().equals("LP") ? "selected" : ""}>LP</option>
                        <option value="EP" ${record.getFormat() != null && record.getFormat().equals("EP") ? "selected" : ""}>EP</option>
                        <option value="Сингл" ${record.getFormat() != null && record.getFormat().equals("Сингл") ? "selected" : ""}>Сингл</option>
                        <option value="Інше" ${record.getFormat() != null && record.getFormat().equals("Інше") ? "selected" : ""}>Інше</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="trackCount" class="form-label">Кількість треків</label>
                    <input type="number" class="form-control" id="trackCount" name="trackCount" value="${record.getTrackCount() != null ? String.valueOf(record.getTrackCount()) : ""}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-12">
                    <label for="description" class="form-label">Опис</label>
                    <textarea class="form-control" id="description" name="description" rows="3">${record.getDescription() != null ? record.getDescription() : ""}</textarea>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="coverImageUrl" class="form-label">URL обкладинки</label>
                    <input type="url" class="form-control" id="coverImageUrl" name="coverImageUrl" value="${record.getCoverImageUrl() != null ? record.getCoverImageUrl() : ""}">
                </div>
                <div class="col-md-6">
                    <label for="price" class="form-label">Ціна</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="${record.getPrice() != null ? String.valueOf(record.getPrice()) : ""}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="availableCopies" class="form-label">Доступна кількість</label>
                    <input type="number" class="form-control" id="availableCopies" name="availableCopies" value="${record.getAvailableCopies() != null ? String.valueOf(record.getAvailableCopies()) : "0"}">
                </div>
                <div class="col-md-6">
                    <label for="totalCopies" class="form-label">Загальна кількість</label>
                    <input type="number" class="form-control" id="totalCopies" name="totalCopies" value="${record.getTotalCopies() != null ? String.valueOf(record.getTotalCopies()) : "0"}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-12">
                    <label class="form-label">Жанри</label>
                    <div class="row">
                        @for(Genre genre : genres)
                            <div class="col-md-3 mb-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="genreIds" value="${genre.getId()}" id="genre${genre.getId()}">
                                    <label class="form-check-label" for="genre${genre.getId()}">
                                        ${genre.getName()}
                                    </label>
                                </div>
                            </div>
                        @endfor
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Зберегти</button>
                    <a href="/records" class="btn btn-secondary">Скасувати</a>
                </div>
            </div>
        </form>
    </div>
`)
