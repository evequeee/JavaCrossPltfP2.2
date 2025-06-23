package gg.jte.generated.ondemand;
import ua.edu.chnu.springjpaproject.model.Book;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Category;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JtebookeditGenerated {
	public static final String JTE_NAME = "book-edit.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,5,12,12,16,16,26,26,26,26,32,32,33,33,33,34,34,38,38,38,41,41,41,41,44,44,44,44,44,44,44,44,44,49,49,49,49,49,49,49,49,49,57,57,57,57,57,57,62,62,62,62,62,62,70,70,71,71,72,72,72,72,72,72,72,72,72,73,73,73,73,73,73,75,75,76,76,76,76,76,76,76,76,76,77,77,77,77,77,77,79,79,80,80,88,88,89,89,90,90,90,90,90,90,90,90,90,91,91,91,93,93,94,94,94,94,94,94,94,94,94,95,95,95,97,97,98,98,104,104,104,116,116,116,117,117,117,5,6,7,8,9,10,10,10,10};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String currentUser, Book book, List<Author> authors, List<Category> categories, String errorMessage) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container mt-4\">\n        <div class=\"row mb-4\">\n            <div class=\"col\">\n                <h1>Edit Book</h1>\n            </div>\n            <div class=\"col-auto\">\n                <a href=\"/books\" class=\"btn btn-secondary\">\n                    <i class=\"fas fa-arrow-left\"></i> Back to List\n                </a>\n                <a href=\"/books/view/");
				jteOutput.setContext("a", "href");
				jteOutput.writeUserContent(book.getId());
				jteOutput.setContext("a", null);
				jteOutput.writeContent("\" class=\"btn btn-info\">\n                    <i class=\"fas fa-eye\"></i> View Details\n                </a>\n            </div>\n        </div>\n\n        ");
				if (errorMessage != null) {
					jteOutput.writeContent("\n            <div class=\"alert alert-danger\">");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(errorMessage);
					jteOutput.writeContent("</div>\n        ");
				}
				jteOutput.writeContent("\n\n        <div class=\"card\">\n            <div class=\"card-header bg-warning text-dark\">\n                <h2>Edit Book: ");
				jteOutput.setContext("h2", null);
				jteOutput.writeUserContent(book.getTitle());
				jteOutput.writeContent("</h2>\n            </div>\n            <div class=\"card-body\">\n                <form action=\"/books/edit/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(book.getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("\" method=\"post\">\n                    <div class=\"mb-3\">\n                        <label for=\"title\" class=\"form-label\">Book Title*</label>\n                        <input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\"");
				var __jte_html_attribute_0 = book.getTitle();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" required>\n                    </div>\n\n                    <div class=\"mb-3\">\n                        <label for=\"isbn\" class=\"form-label\">ISBN</label>\n                        <input type=\"text\" class=\"form-control\" id=\"isbn\" name=\"isbn\"");
				var __jte_html_attribute_1 = book.getIsbn() != null ? book.getIsbn() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n                        <small class=\"text-muted\">International Standard Book Number</small>\n                    </div>\n\n                    <div class=\"row\">\n                        <div class=\"col-md-6 mb-3\">\n                            <label for=\"publicationYear\" class=\"form-label\">Publication Year</label>\n                            <input type=\"number\" class=\"form-control\" id=\"publicationYear\" name=\"publicationYear\"\n                                   value=\"");
				if (book.getPublicationYear() != null) {
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(book.getPublicationYear());
					jteOutput.setContext("input", null);
				}
				jteOutput.writeContent("\" min=\"1800\" max=\"2099\">\n                        </div>\n                        <div class=\"col-md-6 mb-3\">\n                            <label for=\"pages\" class=\"form-label\">Number of Pages</label>\n                            <input type=\"number\" class=\"form-control\" id=\"pages\" name=\"pages\"\n                                   value=\"");
				if (book.getPages() != null) {
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(book.getPages());
					jteOutput.setContext("input", null);
				}
				jteOutput.writeContent("\" min=\"1\">\n                        </div>\n                    </div>\n\n                    <div class=\"mb-3\">\n                        <label for=\"author\" class=\"form-label\">Author*</label>\n                        <select class=\"form-select\" id=\"author\" name=\"authorId\" required>\n                            <option value=\"\">Select Author</option>\n                            ");
				for (Author author : authors) {
					jteOutput.writeContent("\n                                ");
					if (book.getAuthor() != null && book.getAuthor().getId().equals(author.getId())) {
						jteOutput.writeContent("\n                                    <option");
						var __jte_html_attribute_2 = author.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("option", "value");
							jteOutput.writeUserContent(__jte_html_attribute_2);
							jteOutput.setContext("option", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" selected>\n                                        ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(author.getFirstName());
						jteOutput.writeContent(" ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(author.getLastName());
						jteOutput.writeContent("\n                                    </option>\n                                ");
					} else {
						jteOutput.writeContent("\n                                    <option");
						var __jte_html_attribute_3 = author.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("option", "value");
							jteOutput.writeUserContent(__jte_html_attribute_3);
							jteOutput.setContext("option", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                        ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(author.getFirstName());
						jteOutput.writeContent(" ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(author.getLastName());
						jteOutput.writeContent("\n                                    </option>\n                                ");
					}
					jteOutput.writeContent("\n                            ");
				}
				jteOutput.writeContent("\n                        </select>\n                    </div>\n\n                    <div class=\"mb-3\">\n                        <label for=\"category\" class=\"form-label\">Category*</label>\n                        <select class=\"form-select\" id=\"category\" name=\"categoryId\" required>\n                            <option value=\"\">Select Category</option>\n                            ");
				for (Category category : categories) {
					jteOutput.writeContent("\n                                ");
					if (book.getCategory() != null && book.getCategory().getId().equals(category.getId())) {
						jteOutput.writeContent("\n                                    <option");
						var __jte_html_attribute_4 = category.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("option", "value");
							jteOutput.writeUserContent(__jte_html_attribute_4);
							jteOutput.setContext("option", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" selected>\n                                        ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(category.getName());
						jteOutput.writeContent("\n                                    </option>\n                                ");
					} else {
						jteOutput.writeContent("\n                                    <option");
						var __jte_html_attribute_5 = category.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("option", "value");
							jteOutput.writeUserContent(__jte_html_attribute_5);
							jteOutput.setContext("option", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                        ");
						jteOutput.setContext("option", null);
						jteOutput.writeUserContent(category.getName());
						jteOutput.writeContent("\n                                    </option>\n                                ");
					}
					jteOutput.writeContent("\n                            ");
				}
				jteOutput.writeContent("\n                        </select>\n                    </div>\n\n                    <div class=\"mb-3\">\n                        <label for=\"description\" class=\"form-label\">Description</label>\n                        <textarea class=\"form-control\" id=\"description\" name=\"description\" rows=\"4\">");
				jteOutput.setContext("textarea", null);
				jteOutput.writeUserContent(book.getDescription() != null ? book.getDescription() : "");
				jteOutput.writeContent("</textarea>\n                    </div>\n\n                    <div class=\"d-grid gap-2\">\n                        <button type=\"submit\" class=\"btn btn-warning\">\n                            <i class=\"fas fa-save\"></i> Save Changes\n                        </button>\n                    </div>\n                </form>\n            </div>\n        </div>\n    </div>\n");
			}
		}, currentUser);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "Edit Book");
		String currentUser = (String)params.getOrDefault("currentUser", null);
		Book book = (Book)params.get("book");
		List<Author> authors = (List<Author>)params.get("authors");
		List<Category> categories = (List<Category>)params.get("categories");
		String errorMessage = (String)params.getOrDefault("errorMessage", null);
		render(jteOutput, jteHtmlInterceptor, title, currentUser, book, authors, categories, errorMessage);
	}
}
