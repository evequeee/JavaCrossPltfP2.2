package gg.jte.generated.ondemand;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JtelayoutGenerated {
	public static final String JTE_NAME = "layout.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,10,10,10,10,84,84,86,86,86,93,93,100,100,107,107,107,118,118,118,1,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, gg.jte.Content content, String currentUser) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css\" rel=\"stylesheet\">\n    <style>\n        body {\n            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n            min-height: 100vh;\n            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n        }\n        .main-container {\n            min-height: 100vh;\n            display: flex;\n            align-items: center;\n            justify-content: center;\n            padding: 20px;\n        }\n        .card {\n            backdrop-filter: blur(10px);\n            background: rgba(255, 255, 255, 0.95);\n            border: none;\n            border-radius: 20px;\n            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);\n        }\n        .navbar-brand {\n            font-weight: 600;\n            color: #fff !important;\n        }\n        .btn-primary {\n            background: linear-gradient(45deg, #667eea, #764ba2);\n            border: none;\n            border-radius: 10px;\n            padding: 12px 30px;\n            font-weight: 500;\n            transition: all 0.3s ease;\n        }\n        .btn-primary:hover {\n            transform: translateY(-2px);\n            box-shadow: 0 10px 20px rgba(102, 126, 234, 0.4);\n        }\n        .form-control {\n            border-radius: 10px;\n            border: 2px solid #e0e0e0;\n            padding: 12px 15px;\n            transition: all 0.3s ease;\n        }\n        .form-control:focus {\n            border-color: #667eea;\n            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);\n        }\n        .alert {\n            border-radius: 10px;\n            border: none;\n        }\n    </style>\n</head>\n<body>\n    <nav class=\"navbar navbar-expand-lg navbar-dark\" style=\"background: linear-gradient(45deg, #667eea, #764ba2);\">\n        <div class=\"container\">\n            <a class=\"navbar-brand\" href=\"/\">\n                <i class=\"fas fa-book-open me-2\"></i>Library System\n            </a>\n            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\">\n                <span class=\"navbar-toggler-icon\"></span>\n            </button>\n            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n                <ul class=\"navbar-nav me-auto\">\n                    <li class=\"nav-item\">\n                        <a class=\"nav-link\" href=\"/\"><i class=\"fas fa-home me-1\"></i>Home</a>\n                    </li>\n                    <li class=\"nav-item\">\n                        <a class=\"nav-link\" href=\"/books\"><i class=\"fas fa-book me-1\"></i>Books</a>\n                    </li>\n                </ul>\n                <div class=\"d-flex\">\n                    ");
		if (currentUser != null) {
			jteOutput.writeContent("\n                        <span class=\"navbar-text me-3\">\n                            <i class=\"fas fa-user me-1\"></i>");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(currentUser);
			jteOutput.writeContent("\n                        </span>\n                        <form action=\"/logout\" method=\"post\" class=\"d-inline\">\n                            <button type=\"submit\" class=\"btn btn-outline-light btn-sm\">\n                                <i class=\"fas fa-sign-out-alt me-1\"></i>Logout\n                            </button>\n                        </form>\n                    ");
		} else {
			jteOutput.writeContent("\n                        <a href=\"/login\" class=\"btn btn-outline-light btn-sm me-2\">\n                            <i class=\"fas fa-sign-in-alt me-1\"></i>Login\n                        </a>\n                        <a href=\"/register\" class=\"btn btn-light btn-sm\">\n                            <i class=\"fas fa-user-plus me-1\"></i>Register\n                        </a>\n                    ");
		}
		jteOutput.writeContent("\n                </div>\n            </div>\n        </div>\n    </nav>\n\n    <div class=\"main-container\">\n        ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </div>\n\n    <footer class=\"text-center text-white py-3 mt-5\" style=\"background: rgba(0, 0, 0, 0.2);\">\n        <div class=\"container\">\n            <p class=\"mb-0\">© 2025 Library Management System</p>\n        </div>\n    </footer>\n\n    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "Library Management System");
		gg.jte.Content content = (gg.jte.Content)params.get("content");
		String currentUser = (String)params.getOrDefault("currentUser", null);
		render(jteOutput, jteHtmlInterceptor, title, content, currentUser);
	}
}
