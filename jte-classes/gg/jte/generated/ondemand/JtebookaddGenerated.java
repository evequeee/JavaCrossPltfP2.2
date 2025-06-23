package gg.jte.generated.ondemand;
import ua.edu.chnu.springjpaproject.model.Author;
import ua.edu.chnu.springjpaproject.model.Category;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JtebookaddGenerated {
	public static final String JTE_NAME = "book-add.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,4,14,14,14,14,21,21,22,22,22,23,23,50,50,51,51,51,51,51,51,51,51,51,51,51,51,51,51,51,52,52,60,60,61,61,61,61,61,61,61,61,61,61,61,61,62,62,76,76,76,4,5,6,7,8,8,8,8};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String currentUser, String errorMessage, List<Author> authors, List<Category> categories) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html>\n<head>\n    <meta charset=\"UTF-8\">\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n</head>\n<body>\n    <div class=\"container mt-5\">\n        <h1>Add New Book</h1>\n\n        ");
		if (errorMessage != null) {
			jteOutput.writeContent("\n            <div class=\"alert alert-danger\">");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(errorMessage);
			jteOutput.writeContent("</div>\n        ");
		}
		jteOutput.writeContent("\n\n        <form action=\"/books/add\" method=\"post\">\n            <div class=\"mb-3\">\n                <label for=\"title\" class=\"form-label\">Book Title</label>\n                <input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" required>\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"isbn\" class=\"form-label\">ISBN</label>\n                <input type=\"text\" class=\"form-control\" id=\"isbn\" name=\"isbn\">\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"publicationYear\" class=\"form-label\">Publication Year</label>\n                <input type=\"number\" class=\"form-control\" id=\"publicationYear\" name=\"publicationYear\">\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"pages\" class=\"form-label\">Number of Pages</label>\n                <input type=\"number\" class=\"form-control\" id=\"pages\" name=\"pages\">\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"author\" class=\"form-label\">Author</label>\n                <select class=\"form-control\" id=\"author\" name=\"authorId\" required>\n                    <option value=\"\">Select Author</option>\n                    ");
		for (Author author : authors) {
			jteOutput.writeContent("\n                        <option");
			var __jte_html_attribute_0 = author.getId();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("option", "value");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("option", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">");
			jteOutput.setContext("option", null);
			jteOutput.writeUserContent(author.getFirstName());
			jteOutput.writeContent(" ");
			jteOutput.setContext("option", null);
			jteOutput.writeUserContent(author.getLastName());
			jteOutput.writeContent("</option>\n                    ");
		}
		jteOutput.writeContent("\n                </select>\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"category\" class=\"form-label\">Category</label>\n                <select class=\"form-control\" id=\"category\" name=\"categoryId\" required>\n                    <option value=\"\">Select Category</option>\n                    ");
		for (Category category : categories) {
			jteOutput.writeContent("\n                        <option");
			var __jte_html_attribute_1 = category.getId();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("option", "value");
				jteOutput.writeUserContent(__jte_html_attribute_1);
				jteOutput.setContext("option", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">");
			jteOutput.setContext("option", null);
			jteOutput.writeUserContent(category.getName());
			jteOutput.writeContent("</option>\n                    ");
		}
		jteOutput.writeContent("\n                </select>\n            </div>\n\n            <div class=\"mb-3\">\n                <label for=\"description\" class=\"form-label\">Description</label>\n                <textarea class=\"form-control\" id=\"description\" name=\"description\"></textarea>\n            </div>\n\n            <button type=\"submit\" class=\"btn btn-primary\">Add Book</button>\n        </form>\n    </div>\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "Add New Book");
		String currentUser = (String)params.getOrDefault("currentUser", null);
		String errorMessage = (String)params.getOrDefault("errorMessage", null);
		List<Author> authors = (List<Author>)params.get("authors");
		List<Category> categories = (List<Category>)params.get("categories");
		render(jteOutput, jteHtmlInterceptor, title, currentUser, errorMessage, authors, categories);
	}
}
