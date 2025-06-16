package gg.jte.generated.ondemand.records;
import com.example.project22.model.Record;
import java.util.List;
public final class JtelistGenerated {
	public static final String JTE_NAME = "records/list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,6,6,6,6,26,36,36,40,40,56,56,58,58,58,59,59,59,59,59,59,59,61,61,62,62,62,62,62,62,62,63,63,65,65,67,67,67,68,68,68,69,69,69,71,71,71,71,72,72,72,72,72,72,77,77,77,77,78,78,78,78,80,80,80,80,83,83,83,83,83,83,83,83,84,89,89,93,93,94,94,94,95,95,95,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Record> records, String title) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layouts.JtedefaultGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"mb-4\">\r\n        <div class=\"d-flex justify-content-between align-items-center\">\r\n            <h2>Список платівок</h2>\r\n            <a href=\"/records/new\" class=\"btn btn-success\"><i class=\"bi bi-plus-circle\"></i> Додати нову платівку</a>\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"card mb-4\">\r\n        <div class=\"card-header\">\r\n            <form action=\"/records/search\" method=\"get\" class=\"row g-3\">\r\n                <div class=\"col-md-4\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"title\" placeholder=\"Пошук за назвою\">\r\n                </div>\r\n                <div class=\"col-md-3\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"artist\" placeholder=\"Пошук за виконавцем\">\r\n                </div>\r\n                <div class=\"col-md-3\">\r\n                    <select class=\"form-select\" name=\"genre\">\r\n                        <option value=\"\">Всі жанри</option>\r\n                        ");
				jteOutput.writeContent("\r\n                    </select>\r\n                </div>\r\n                <div class=\"col-md-2\">\r\n                    <button type=\"submit\" class=\"btn btn-primary w-100\"><i class=\"bi bi-search\"></i> Пошук</button>\r\n                </div>\r\n            </form>\r\n        </div>\r\n    </div>\r\n\r\n    ");
				if (records.isEmpty()) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-info\">\r\n            <i class=\"bi bi-info-circle\"></i> Платівки не знайдено.\r\n        </div>\r\n    ");
				} else {
					jteOutput.writeContent("\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-striped table-hover\">\r\n                <thead class=\"table-light\">\r\n                    <tr>\r\n                        <th>ID</th>\r\n                        <th>Назва</th>\r\n                        <th>Виконавець</th>\r\n                        <th>Каталожний номер</th>\r\n                        <th>Рік випуску</th>\r\n                        <th>Формат</th>\r\n                        <th>Доступно</th>\r\n                        <th>Дії</th>\r\n                    </tr>\r\n                </thead>\r\n                <tbody>\r\n                    ");
					for (Record record : records) {
						jteOutput.writeContent("\r\n                        <tr>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(record.getId());
						jteOutput.writeContent("</td>\r\n                            <td><a href=\"/records/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(record.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(record.getTitle());
						jteOutput.writeContent("</a></td>\r\n                            <td>\r\n                                ");
						if (record.getArtist() != null) {
							jteOutput.writeContent("\r\n                                    <a href=\"/artists/");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(record.getArtist().getId());
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\">");
							jteOutput.setContext("a", null);
							jteOutput.writeUserContent(record.getArtist().getName());
							jteOutput.writeContent("</a>\r\n                                ");
						} else {
							jteOutput.writeContent("\r\n                                    <span class=\"text-muted\">Не вказано</span>\r\n                                ");
						}
						jteOutput.writeContent("\r\n                            </td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(record.getCatalogNumber() != null ? record.getCatalogNumber() : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(record.getReleaseYear() != null ? String.valueOf(record.getReleaseYear()) : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(record.getFormat() != null ? record.getFormat() : "Не вказано");
						jteOutput.writeContent("</td>\r\n                            <td>\r\n                                <span class=\"badge ");
						jteOutput.setContext("span", "class");
						jteOutput.writeUserContent(record.getAvailableCopies() > 0 ? "bg-success" : "bg-danger");
						jteOutput.setContext("span", null);
						jteOutput.writeContent("\">\r\n                                    ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(record.getAvailableCopies());
						jteOutput.writeContent(" / ");
						jteOutput.setContext("span", null);
						jteOutput.writeUserContent(record.getTotalCopies());
						jteOutput.writeContent("\r\n                                </span>\r\n                            </td>\r\n                            <td>\r\n                                <div class=\"btn-group\" role=\"group\">\r\n                                    <a href=\"/records/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(record.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\" class=\"btn btn-sm btn-info\"><i class=\"bi bi-eye\"></i></a>\r\n                                    <a href=\"/records/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(record.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("/edit\" class=\"btn btn-sm btn-warning\"><i class=\"bi bi-pencil\"></i></a>\r\n                                    <button type=\"button\" class=\"btn btn-sm btn-danger\"\r\n                                            onclick=\"if(confirm('Ви впевнені?')) { document.getElementById('delete-form-");
						jteOutput.setContext("button", "onclick");
						jteOutput.writeUserContent(record.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("').submit(); }\">\r\n                                        <i class=\"bi bi-trash\"></i>\r\n                                    </button>\r\n                                    <form id=\"delete-form-");
						jteOutput.setContext("form", "id");
						jteOutput.writeUserContent(record.getId());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" action=\"/records/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(record.getId());
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
		List<Record> records = (List<Record>)params.get("records");
		String title = (String)params.getOrDefault("title", "Платівки");
		render(jteOutput, jteHtmlInterceptor, records, title);
	}
}
