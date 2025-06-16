package gg.jte.generated.ondemand.books;
import com.example.project22.model.Book;
import java.util.List;
public final class JtelistGenerated {
	public static final String JTE_NAME = "books/list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,6,6,6,6,26,36,36,40,40,55,55,57,57,57,58,58,58,58,58,58,58,60,60,61,61,61,61,61,61,61,61,61,61,62,62,64,64,66,66,66,67,67,67,69,69,69,69,70,70,70,70,70,70,75,75,75,75,76,76,76,76,78,78,78,78,81,81,81,81,81,81,81,81,82,87,87,91,91,92,92,92,93,93,93,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Book> books, String title) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layouts.JtedefaultGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"mb-4\">\r\n        <div class=\"d-flex justify-content-between align-items-center\">\r\n            <h2>Список книг</h2>\r\n            <a href=\"/books/new\" class=\"btn btn-success\"><i class=\"bi bi-plus-circle\"></i> Додати нову книгу</a>\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"card mb-4\">\r\n        <div class=\"card-header\">\r\n            <form action=\"/books/search\" method=\"get\" class=\"row g-3\">\r\n                <div class=\"col-md-4\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"title\" placeholder=\"Пошук за назвою\">\r\n                </div>\r\n                <div class=\"col-md-3\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"author\" placeholder=\"Пошук за автором\">\r\n                </div>\r\n                <div class=\"col-md-3\">\r\n                    <select class=\"form-select\" name=\"genre\">\r\n                        <option value=\"\">Всі жанри</option>\r\n                        ");
				jteOutput.writeContent("\r\n                    </select>\r\n                </div>\r\n                <div class=\"col-md-2\">\r\n                    <button type=\"submit\" class=\"btn btn-primary w-100\"><i class=\"bi bi-search\"></i> Пошук</button>\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n\r\n    ");
				if (books.isEmpty()) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-info\">\r\n            <i class=\"bi bi-info-circle\"></i> Книги не знайдено.\r\n        </div>\r\n    ");
				} else {
					jteOutput.writeContent("\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-striped table-hover\">\r\n                <thead class=\"table-light\">\r\n                    <tr>\r\n                        <th>ID</th>\r\n                        <th>Назва</th>\r\n                        <th>Автор</th>\r\n                        <th>ISBN</th>\r\n                        <th>Рік видання</th>\r\n                        <th>Доступно</th>\r\n                        <th>Дії</th>\r\n                    </tr>\r\n                </thead>\r\n                <tbody>\r\n                    ");
					for (Book book : books) {
						jteOutput.writeContent("\r\n                        <tr>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getId());
						jteOutput.writeContent("</td>\r\n                            <td><a href=\"/books/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(book.getTitle());
						jteOutput.writeContent("</a></td>\r\n                            <td>\r\n                                ");
						if (book.getAuthor() != null) {
							jteOutput.writeContent("\r\n                                    <a href=\"/authors/");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(book.getAuthor().getId());
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\">");
							jteOutput.setContext("a", null);
							jteOutput.writeUserContent(book.getAuthor().getFirstName());
							jteOutput.writeContent(" ");
							jteOutput.setContext("a", null);
							jteOutput.writeUserContent(book.getAuthor().getLastName());
							jteOutput.writeContent("</a>\r\n                                ");
						} else {
							jteOutput.writeContent("\r\n                                    <span class=\"text-muted\">Не вказано</span>\r\n                                ");
						}
						jteOutput.writeContent("\r\n                            </td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getIsbn() != null ? book.getIsbn() : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getPublicationYear() != null ? String.valueOf(book.getPublicationYear()) : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>\r\n                                <span class=\"badge ");
						jteOutput.setContext("span", "class");
						jteOutput.writeUserContent(book.getAvailableCopies() > 0 ? "bg-success" : "bg-danger");
						jteOutput.setContext("span", null);
						jteOutput.writeContent("\">\r\n                                    ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(book.getAvailableCopies());
						jteOutput.writeContent(" / ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(book.getTotalCopies());
						jteOutput.writeContent("\r\n                                </span>\r\n                            </td>\r\n                            <td>\r\n                                <div class=\"btn-group\" role=\"group\">\r\n                                    <a href=\"/books/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\" class=\"btn btn-sm btn-info\"><i class=\"bi bi-eye\"></i></a>\r\n                                    <a href=\"/books/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("/edit\" class=\"btn btn-sm btn-warning\"><i class=\"bi bi-pencil\"></i></a>\r\n                                    <button type=\"button\" class=\"btn btn-sm btn-danger\"\r\n                                            onclick=\"if(confirm('Ви впевнені?')) { document.getElementById('delete-form-");
						jteOutput.setContext("button", "onclick");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("').submit(); }\">\r\n                                        <i class=\"bi bi-trash\"></i>\r\n                                    </button>\r\n                                    <form id=\"delete-form-");
						jteOutput.setContext("form", "id");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" action=\"/books/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("/delete\" method=\"post\" class=\"d-none\">\r\n                                        ");
						jteOutput.writeContent("\r\n                                    </form>\r\n                                </div>\r\n                            </td>\r\n                        </tr>\r\n                    ");
					}
					jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n    ");
				}
				jteOutput.writeContent("\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<Book> books = (List<Book>)params.get("books");
		String title = (String)params.getOrDefault("title", "Книги");
		render(jteOutput, jteHtmlInterceptor, books, title);
	}
}
