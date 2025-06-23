package gg.jte.generated.ondemand;
import ua.edu.chnu.springjpaproject.user.dto.UserRegistrationDto;
import java.util.Map;
@SuppressWarnings("unchecked")
public final class JteloginGenerated {
	public static final String JTE_NAME = "login.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,8,8,12,12,21,22,22,26,26,28,28,32,32,35,65,65,65,66,66,66,3,4,5,6,6,6,6};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Map<String, String> errors, String error, String logout, String currentUser) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtelayoutGenerated.render(jteOutput, jteHtmlInterceptor, "Login", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div class=\"container mt-5\">\n    <div class=\"row justify-content-center\">\n        <div class=\"col-md-6\">\n            <div class=\"card\">\n                <div class=\"card-header\">\n                    <h3 class=\"text-center\">Login</h3>\n                </div>\n                <div class=\"card-body\">\n                    ");
				jteOutput.writeContent("\n                    ");
				if (error != null) {
					jteOutput.writeContent("\n                        <div class=\"alert alert-danger\">\n                            Invalid username or password.\n                        </div>\n                    ");
				}
				jteOutput.writeContent("\n\n                    ");
				if (logout != null) {
					jteOutput.writeContent("\n                        <div class=\"alert alert-success\">\n                            You have been logged out successfully.\n                        </div>\n                    ");
				}
				jteOutput.writeContent("\n\n                    <form action=\"/login\" method=\"post\">\n                        ");
				jteOutput.writeContent("\n\n                        <div class=\"mb-3\">\n                            <label for=\"username\" class=\"form-label\">Username</label>\n                            <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" required autofocus>\n                        </div>\n\n                        <div class=\"mb-3\">\n                            <label for=\"password\" class=\"form-label\">Password</label>\n                            <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" required>\n                        </div>\n\n                        <div class=\"mb-3 form-check\">\n                            <input type=\"checkbox\" class=\"form-check-input\" id=\"remember-me\" name=\"remember-me\">\n                            <label class=\"form-check-label\" for=\"remember-me\">Remember me</label>\n                        </div>\n\n                        <div class=\"d-grid gap-2\">\n                            <button type=\"submit\" class=\"btn btn-primary\">Login</button>\n                        </div>\n                    </form>\n\n                    <div class=\"mt-3 text-center\">\n                        <p>Don't have an account? <a href=\"/register\">Register</a></p>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n");
			}
		}, currentUser);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Map<String, String> errors = (Map<String, String>)params.getOrDefault("errors", Map.of());
		String error = (String)params.getOrDefault("error", null);
		String logout = (String)params.getOrDefault("logout", null);
		String currentUser = (String)params.getOrDefault("currentUser", null);
		render(jteOutput, jteHtmlInterceptor, errors, error, logout, currentUser);
	}
}
