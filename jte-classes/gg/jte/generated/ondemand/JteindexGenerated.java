package gg.jte.generated.ondemand;
import gg.jte.Content;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,3,3,44,44,44,45,45,45,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layouts.JtedefaultGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"row\">\r\n        <div class=\"col-md-6\">\r\n            <div class=\"card mb-4\">\r\n                <div class=\"card-body\">\r\n                    <h2 class=\"card-title\"><i class=\"bi bi-disc\"></i> Управління платівками</h2>\r\n                    <p class=\"card-text\">Додавайте, редагуйте та видаляйте платівки з колекції.</p>\r\n                    <a href=\"/records\" class=\"btn btn-primary\">Перейти до платівок</a>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class=\"col-md-6\">\r\n            <div class=\"card mb-4\">\r\n                <div class=\"card-body\">\r\n                    <h2 class=\"card-title\"><i class=\"bi bi-person\"></i> Управління виконавцями</h2>\r\n                    <p class=\"card-text\">Керуйте інформацією про виконавців платівок.</p>\r\n                    <a href=\"/artists\" class=\"btn btn-primary\">Перейти до виконавців</a>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    <div class=\"row\">\r\n        <div class=\"col-md-6\">\r\n            <div class=\"card mb-4\">\r\n                <div class=\"card-body\">\r\n                    <h2 class=\"card-title\"><i class=\"bi bi-people\"></i> Користувачі колекції</h2>\r\n                    <p class=\"card-text\">Управління користувачами колекції платівок та їх членством.</p>\r\n                    <a href=\"/users\" class=\"btn btn-primary\">Перейти до користувачів</a>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class=\"col-md-6\">\r\n            <div class=\"card mb-4\">\r\n                <div class=\"card-body\">\r\n                    <h2 class=\"card-title\"><i class=\"bi bi-clipboard-check\"></i> Позичені платівки</h2>\r\n                    <p class=\"card-text\">Перегляд та управління платівками, що були видані користувачам.</p>\r\n                    <a href=\"/loans\" class=\"btn btn-primary\">Перейти до позичень</a>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "Головна сторінка");
		render(jteOutput, jteHtmlInterceptor, title);
	}
}
