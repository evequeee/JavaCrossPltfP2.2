@import gg.jte.Content
@param String title = "Головна сторінка"

@template.layouts.default(title = title, content = @`
    <div class="row">
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title"><i class="bi bi-disc"></i> Управління платівками</h2>
                    <p class="card-text">Додавайте, редагуйте та видаляйте платівки з колекції.</p>
                    <a href="/records" class="btn btn-primary">Перейти до платівок</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title"><i class="bi bi-person"></i> Управління виконавцями</h2>
                    <p class="card-text">Керуйте інформацією про виконавців платівок.</p>
                    <a href="/artists" class="btn btn-primary">Перейти до виконавців</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title"><i class="bi bi-people"></i> Користувачі колекції</h2>
                    <p class="card-text">Управління користувачами колекції платівок та їх членством.</p>
                    <a href="/users" class="btn btn-primary">Перейти до користувачів</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title"><i class="bi bi-clipboard-check"></i> Позичені платівки</h2>
                    <p class="card-text">Перегляд та управління платівками, що були видані користувачам.</p>
                    <a href="/loans" class="btn btn-primary">Перейти до позичень</a>
                </div>
            </div>
        </div>
    </div>
`)
