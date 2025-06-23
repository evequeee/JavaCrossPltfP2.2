package gg.jte.generated.ondemand;
import ua.edu.chnu.springjpaproject.user.UserRole;
@SuppressWarnings("unchecked")
public final class JteregisterGenerated {
	public static final String JTE_NAME = "register.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,4,4,7,7,14,14,16,16,16,18,18,42,42,42,42,42,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String errorMessage, String currentUser) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, "Registration", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container mt-5\">\n        <div class=\"row justify-content-center\">\n            <div class=\"col-md-6\">\n                <div class=\"card\">\n                    <div class=\"card-header\">Registration</div>\n                    <div class=\"card-body\">\n                        ");
				if (errorMessage != null) {
					jteOutput.writeContent("\n                            <div class=\"alert alert-danger\" role=\"alert\">\n                                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(errorMessage);
					jteOutput.writeContent("\n                            </div>\n                        ");
				}
				jteOutput.writeContent("\n                        <form action=\"/register\" method=\"post\">\n                            <div class=\"mb-3\">\n                                <label for=\"username\" class=\"form-label\">Username</label>\n                                <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" required minlength=\"3\">\n                            </div>\n                            <div class=\"mb-3\">\n                                <label for=\"password\" class=\"form-label\">Password</label>\n                                <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" required minlength=\"6\">\n                            </div>\n                            <div class=\"mb-3\">\n                                <label for=\"role\" class=\"form-label\">Role</label>\n                                <select class=\"form-select\" id=\"role\" name=\"role\">\n                                    <option value=\"USER\">User</option>\n                                    <option value=\"ADMIN\">Administrator</option>\n                                </select>\n                            </div>\n                            <button type=\"submit\" class=\"btn btn-primary\">Register</button>\n                        </form>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n");
			}
		}, currentUser);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String errorMessage = (String)params.getOrDefault("errorMessage", null);
		String currentUser = (String)params.getOrDefault("currentUser", null);
		render(jteOutput, jteHtmlInterceptor, errorMessage, currentUser);
	}
}
