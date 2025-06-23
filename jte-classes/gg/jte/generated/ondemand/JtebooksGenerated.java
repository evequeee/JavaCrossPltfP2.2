package gg.jte.generated.ondemand;
import ua.edu.chnu.springjpaproject.model.Book;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JtebooksGenerated {
	public static final String JTE_NAME = "books.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,9,9,13,13,29,29,31,31,31,34,34,36,36,38,38,38,41,41,43,43,47,47,63,63,65,65,65,66,66,66,68,68,69,69,69,69,69,69,70,70,72,72,75,75,76,76,76,77,77,79,79,82,82,83,83,83,84,84,86,86,88,88,88,90,90,91,91,91,92,92,94,94,98,98,98,98,101,101,101,101,104,104,104,104,105,105,105,105,113,113,117,117,119,119,119,120,120,120,3,4,5,6,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String currentUser, List<Book> books, String successMessage, String errorMessage) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container mt-4\">\n        <div class=\"row mb-4\">\n            <div class=\"col\">\n                <h1>Library Book List</h1>\n            </div>\n            <div class=\"col-auto\">\n                <a href=\"/books/add\" class=\"btn btn-primary\">\n                    <i class=\"fas fa-plus\"></i> Add New Book\n                </a>\n                <a href=\"/\" class=\"btn btn-secondary\">\n                    <i class=\"fas fa-home\"></i> Home\n                </a>\n            </div>\n        </div>\n\n        ");
				if (errorMessage != null) {
					jteOutput.writeContent("\n            <div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(errorMessage);
					jteOutput.writeContent("\n                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n            </div>\n        ");
				}
				jteOutput.writeContent("\n\n        ");
				if (successMessage != null) {
					jteOutput.writeContent("\n            <div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(successMessage);
					jteOutput.writeContent("\n                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n            </div>\n        ");
				}
				jteOutput.writeContent("\n\n        ");
				if (books.isEmpty()) {
					jteOutput.writeContent("\n            <div class=\"alert alert-info\">\n                No books in the database yet. Add your first book!\n            </div>\n        ");
				} else {
					jteOutput.writeContent("\n            <div class=\"table-responsive\">\n                <table class=\"table table-striped table-hover\">\n                    <thead class=\"table-dark\">\n                        <tr>\n                            <th>ID</th>\n                            <th>Title</th>\n                            <th>Author</th>\n                            <th>Category</th>\n                            <th>Year</th>\n                            <th>ISBN</th>\n                            <th>Pages</th>\n                            <th>Actions</th>\n                        </tr>\n                    </thead>\n                    <tbody>\n                        ");
					for (Book book : books) {
						jteOutput.writeContent("\n                            <tr>\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getId());
						jteOutput.writeContent("</td>\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getTitle());
						jteOutput.writeContent("</td>\n                                <td>\n                                    ");
						if (book.getAuthor() != null) {
							jteOutput.writeContent("\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(book.getAuthor().getFirstName());
							jteOutput.writeContent(" ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(book.getAuthor().getLastName());
							jteOutput.writeContent("\n                                    ");
						} else {
							jteOutput.writeContent("\n                                        -\n                                    ");
						}
						jteOutput.writeContent("\n                                </td>\n                                <td>\n                                    ");
						if (book.getCategory() != null) {
							jteOutput.writeContent("\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(book.getCategory().getName());
							jteOutput.writeContent("\n                                    ");
						} else {
							jteOutput.writeContent("\n                                        -\n                                    ");
						}
						jteOutput.writeContent("\n                                </td>\n                                <td>\n                                    ");
						if (book.getPublicationYear() != null) {
							jteOutput.writeContent("\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(book.getPublicationYear());
							jteOutput.writeContent("\n                                    ");
						} else {
							jteOutput.writeContent("\n                                        -\n                                    ");
						}
						jteOutput.writeContent("\n                                </td>\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(book.getIsbn() != null ? book.getIsbn() : "-");
						jteOutput.writeContent("</td>\n                                <td>\n                                    ");
						if (book.getPages() != null) {
							jteOutput.writeContent("\n                                        ");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(book.getPages());
							jteOutput.writeContent("\n                                    ");
						} else {
							jteOutput.writeContent("\n                                        -\n                                    ");
						}
						jteOutput.writeContent("\n                                </td>\n                                <td>\n                                    <div class=\"btn-group btn-group-sm\">\n                                        <a href=\"/books/view/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\" class=\"btn btn-info\" title=\"View Details\">\n                                            <i class=\"fas fa-eye\"></i>\n                                        </a>\n                                        <a href=\"/books/edit/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\" class=\"btn btn-warning\" title=\"Edit\">\n                                            <i class=\"fas fa-edit\"></i>\n                                        </a>\n                                        <form action=\"/books/delete/");
						jteOutput.setContext("form", "action");
						jteOutput.writeUserContent(book.getId());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("\" method=\"post\" style=\"display:inline;\"\n                                              onsubmit=\"return confirm('Are you sure you want to delete the book ");
						jteOutput.setContext("form", "onsubmit");
						jteOutput.writeUserContent(book.getTitle());
						jteOutput.setContext("form", null);
						jteOutput.writeContent("?')\">\n                                            <button type=\"submit\" class=\"btn btn-danger\" title=\"Delete\">\n                                                <i class=\"fas fa-trash\"></i>\n                                            </button>\n                                        </form>\n                                    </div>\n                                </td>\n                            </tr>\n                        ");
					}
					jteOutput.writeContent("\n                    </tbody>\n                </table>\n            </div>\n        ");
				}
				jteOutput.writeContent("\n    </div>\n");
			}
		}, currentUser);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "Book List");
		String currentUser = (String)params.getOrDefault("currentUser", null);
		List<Book> books = (List<Book>)params.get("books");
		String successMessage = (String)params.getOrDefault("successMessage", null);
		String errorMessage = (String)params.getOrDefault("errorMessage", null);
		render(jteOutput, jteHtmlInterceptor, title, currentUser, books, successMessage, errorMessage);
	}
}
