@import gg.jte.Content
@param String title
@param Content content

<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - Бібліотечна система</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        .sidebar {
            min-height: calc(100vh - 56px);
            background-color: #f8f9fa;
        }
        .content {
            padding: 20px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Бібліотечна система</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/"><i class="bi bi-house-door"></i> Головна</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/books"><i class="bi bi-book"></i> Книги</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/authors"><i class="bi bi-person"></i> Автори</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <nav class="col-md-2 d-md-block sidebar py-3">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="/"><i class="bi bi-house-door"></i> Головна</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/books"><i class="bi bi-book"></i> Книги</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/authors"><i class="bi bi-person"></i> Автори</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/genres"><i class="bi bi-tags"></i> Жанри</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/publishers"><i class="bi bi-building"></i> Видавництва</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/users"><i class="bi bi-people"></i> Користувачі</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/loans"><i class="bi bi-clipboard-check"></i> Позичені книги</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <main class="col-md-10 content">
                <h1 class="mt-3 mb-4">${title}</h1>
                ${content}
            </main>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
