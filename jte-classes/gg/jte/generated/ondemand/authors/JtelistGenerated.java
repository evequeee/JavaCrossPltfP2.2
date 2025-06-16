package gg.jte.generated.ondemand.authors;
import com.example.project22.model.Author;
import java.util.List;
import java.time.format.DateTimeFormatter;
public final class JtelistGenerated {
	public static final String JTE_NAME = "authors/list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,7,7,7,7,31,31,35,35,50,50,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,58,58,58,62,62,62,62,63,63,63,63,65,65,65,65,68,68,68,68,68,68,68,68,69,74,74,78,78,79,79,79,80,80,80,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Author> authors, String title) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layouts.JtedefaultGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"mb-4\">\r\n        <div class=\"d-flex justify-content-between align-items-center\">\r\n            <h2>Список авторів</h2>\r\n            <a href=\"/authors/new\" class=\"btn btn-success\"><i class=\"bi bi-plus-circle\"></i> Додати нового автора</a>\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"card mb-4\">\r\n        <div class=\"card-header\">\r\n            <form action=\"/authors/search\" method=\"get\" class=\"row g-3\">\r\n                <div class=\"col-md-4\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"Пошук за ім'ям\">\r\n                </div>\r\n                <div class=\"col-md-4\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"nationality\" placeholder=\"Пошук за національністю\">\r\n                </div>\r\n                <div class=\"col-md-4\">\r\n                    <button type=\"submit\" class=\"btn btn-primary w-100\"><i class=\"bi bi-search\"></i> Пошук</button>\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n\r\n    ");
				if (authors.isEmpty()) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-info\">\r\n            <i class=\"bi bi-info-circle\"></i> Авторів не знайдено.\r\n        </div>\r\n    ");
				} else {
					jteOutput.writeContent("\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-striped table-hover\">\r\n                <thead class=\"table-light\">\r\n                    <tr>\r\n                        <th>ID</th>\r\n                        <th>Ім'я</th>\r\n                        <th>Прізвище</th>\r\n                        <th>Національність</th>\r\n                        <th>Дата народження</th>\r\n                        <th>Кількість книг</th>\r\n                        <th>Дії</th>\r\n                    </tr>\r\n                </thead>\r\n                <tbody>\r\n                    ");
					for (Author author : authors) {
						jteOutput.writeContent("\r\n                        <tr>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(author.getId());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(author.getFirstName());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(author.getLastName());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(author.getNationality() != null ? author.getNationality() : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(author.getBirthDate() != null ? author.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>\r\n                                <span class=\"badge bg-primary\">");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(author.getBooks() != null ? author.getBooks().size() : 0);
						jteOutput.writeContent("</span>\r\n                            </td>\r\n                            <td>\r\n                                <div class=\"btn-group\" role=\"group\">\r\n                                    <a href=\"/authors/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(author.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\" class=\"btn btn-sm btn-info\"><i class=\"bi bi-eye\"></i></a>\r\n                                    <a href=\"/authors/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(author.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("/edit\" class=\"btn btn-sm btn-warning\"><i class=\"bi bi-pencil\"></i></a>\r\n                                    <button type=\"button\" class=\"btn btn-sm btn-danger\"\r\n                                            onclick=\"if(confirm('Ви впевнені?')) { document.getElementById('delete-form-");
						jteOutput.setContext("button", "onclick");
						jteOutput.writeUserContent(author.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("').submit(); }\">\r\n                                        <i class=\"bi bi-trash\"></i>\r\n                                    </button>\r\n                                    <form id=\"delete-form-");
						jteOutput.setContext("form", "id");
						jteOutput.writeUserContent(author.getId());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" action=\"/authors/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(author.getId());
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
		List<Author> authors = (List<Author>)params.get("authors");
		String title = (String)params.getOrDefault("title", "Автори");
		render(jteOutput, jteHtmlInterceptor, authors, title);
	}
}
