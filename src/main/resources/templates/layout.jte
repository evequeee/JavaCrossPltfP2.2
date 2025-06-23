@import gg.jte.Content
@param String title = "Library Management System"
@param gg.jte.Content content
@param String currentUser = null

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&family=Source+Code+Pro&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #00BFA6;
            --secondary-color: #7B68EE;
            --background-dark: #0A1929;
            --card-bg: #132F4C;
            --text-primary: #E2F6FF;
            --text-secondary: #99B8D1;
            --border-color: #1E4976;
            --danger-color: #FF4D6D;
            --success-color: #05C278;
            --warning-color: #FFB400;
        }

        body {
            background: var(--background-dark);
            min-height: 100vh;
            font-family: 'Montserrat', sans-serif;
            color: var(--text-primary);
            line-height: 1.6;
            position: relative;
        }

        body::before {
            content: "";
            position: absolute;
            top: 0;
            right: 0;
            width: 40%;
            height: 100%;
            background: radial-gradient(circle at top right, rgba(123, 104, 238, 0.15), transparent 70%);
            z-index: -1;
            pointer-events: none;
        }

        .main-container {
            min-height: 100vh;
            padding: 2rem 1rem;
            position: relative;
            z-index: 1;
        }

        .card {
            background: var(--card-bg);
            border: 1px solid var(--border-color);
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.25);
            color: var(--text-primary);
            overflow: hidden;
            position: relative;
        }

        .card::after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 4px;
            background: linear-gradient(90deg, var(--primary-color), var(--secondary-color));
        }

        .navbar {
            background: rgba(10, 25, 41, 0.9) !important;
            backdrop-filter: blur(10px);
            border-bottom: 1px solid var(--border-color);
            padding: 1rem 0;
        }

        .navbar-brand {
            font-weight: 700;
            color: var(--primary-color) !important;
            letter-spacing: 1px;
        }

        .nav-link {
            color: var(--text-secondary) !important;
            font-weight: 500;
            padding: 0.5rem 1rem;
            margin: 0 0.25rem;
            border-radius: 6px;
            transition: all 0.3s ease;
        }

        .nav-link:hover, .nav-link.active {
            color: var(--text-primary) !important;
            background: rgba(0, 191, 166, 0.1);
        }

        .btn {
            border-radius: 6px;
            padding: 0.6rem 1.5rem;
            font-weight: 600;
            letter-spacing: 0.5px;
            transition: all 0.3s ease;
        }

        .btn-primary {
            background: var(--primary-color);
            border: none;
            box-shadow: 0 4px 12px rgba(0, 191, 166, 0.3);
        }

        .btn-primary:hover {
            background: #00AA94;
            transform: translateY(-2px);
            box-shadow: 0 6px 15px rgba(0, 191, 166, 0.4);
        }

        .btn-outline-light {
            border-color: var(--text-secondary);
            color: var(--text-secondary);
        }

        .btn-outline-light:hover {
            background: rgba(255, 255, 255, 0.1);
            color: var(--text-primary);
        }

        .form-control {
            background-color: rgba(19, 47, 76, 0.6);
            border: 1px solid var(--border-color);
            border-radius: 6px;
            padding: 0.8rem 1rem;
            color: var(--text-primary);
            transition: all 0.3s ease;
        }

        .form-control:focus {
            background-color: rgba(19, 47, 76, 0.8);
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.25rem rgba(0, 191, 166, 0.25);
            color: var(--text-primary);
        }

        .form-label {
            color: var(--text-secondary);
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        .table {
            color: var(--text-primary);
            border-collapse: separate;
            border-spacing: 0 8px;
        }

        .table thead th {
            background: rgba(19, 47, 76, 0.5);
            color: var(--text-secondary);
            font-weight: 600;
            border: none;
            padding: 1rem;
            text-transform: uppercase;
            font-size: 0.85rem;
            letter-spacing: 1px;
        }

        .table tbody tr {
            background: rgba(19, 47, 76, 0.3);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }

        .table tbody tr:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            background: rgba(19, 47, 76, 0.5);
        }

        .table tbody td {
            border: none;
            padding: 1rem;
            vertical-align: middle;
        }

        .card-header {
            background: rgba(19, 47, 76, 0.7);
            border-bottom: 1px solid var(--border-color);
            font-weight: 600;
            padding: 1.25rem 1.5rem;
        }

        .card-footer {
            background: rgba(19, 47, 76, 0.7);
            border-top: 1px solid var(--border-color);
            padding: 1.25rem 1.5rem;
        }

        a {
            color: var(--primary-color);
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #00DDBD;
            text-decoration: none;
        }

        .alert {
            border: none;
            border-radius: 8px;
            padding: 1rem 1.5rem;
        }

        .alert-primary {
            background: rgba(0, 191, 166, 0.15);
            border-left: 4px solid var(--primary-color);
            color: var(--text-primary);
        }

        .alert-danger {
            background: rgba(255, 77, 109, 0.15);
            border-left: 4px solid var(--danger-color);
            color: var(--text-primary);
        }

        .badge {
            padding: 0.5em 0.8em;
            border-radius: 6px;
            font-weight: 500;
        }

        .page-header {
            margin-bottom: 2rem;
            position: relative;
        }

        .page-header h1 {
            font-weight: 700;
            font-size: 1.75rem;
            letter-spacing: -0.5px;
        }

        .page-header::after {
            content: "";
            display: block;
            width: 60px;
            height: 4px;
            background: var(--primary-color);
            margin-top: 0.75rem;
            border-radius: 2px;
        }

        /* Custom animation for content loads */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .main-container > .container,
        .main-container > .container-fluid {
            animation: fadeInUp 0.6s ease-out;
        }

        /* Scroll bar styling */
        ::-webkit-scrollbar {
            width: 10px;
            height: 10px;
        }

        ::-webkit-scrollbar-track {
            background: var(--background-dark);
        }

        ::-webkit-scrollbar-thumb {
            background: rgba(30, 73, 118, 0.6);
            border-radius: 5px;
        }

        ::-webkit-scrollbar-thumb:hover {
            background: rgba(30, 73, 118, 0.8);
        }

        code, pre {
            font-family: 'Source Code Pro', monospace;
            background: rgba(10, 25, 41, 0.6);
            border-radius: 4px;
            padding: 0.2rem 0.4rem;
            font-size: 0.9em;
        }

        pre {
            padding: 1rem;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="/">
                <i class="fas fa-book-open me-2"></i>Digital Library
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/"><i class="fas fa-home me-1"></i>Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/books"><i class="fas fa-book me-1"></i>Books</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/authors"><i class="fas fa-pen-nib me-1"></i>Authors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/categories"><i class="fas fa-tags me-1"></i>Categories</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center">
                    @if(currentUser != null)
                        <span class="navbar-text me-3" style="color: var(--primary-color);">
                            <i class="fas fa-user-circle me-1"></i>${currentUser}
                        </span>
                        <form action="/logout" method="post" class="d-inline">
                            <button type="submit" class="btn btn-outline-light btn-sm">
                                <i class="fas fa-sign-out-alt me-1"></i>Logout
                            </button>
                        </form>
                    @else
                        <a href="/login" class="btn btn-outline-light btn-sm me-2">
                            <i class="fas fa-sign-in-alt me-1"></i>Login
                        </a>
                        <a href="/register" class="btn btn-primary btn-sm">
                            <i class="fas fa-user-plus me-1"></i>Register
                        </a>
                    @endif
                </div>
            </div>
        </div>
    </nav>

    <div class="main-container">
        ${content}
    </div>

    <footer class="text-center py-4 mt-5" style="background: rgba(10, 25, 41, 0.9); border-top: 1px solid var(--border-color);">
        <div class="container">
            <p class="mb-1" style="color: var(--text-secondary);">© 2025 Digital Library System</p>
            <small style="color: var(--text-secondary); opacity: 0.7;">Empowering knowledge through technology</small>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

