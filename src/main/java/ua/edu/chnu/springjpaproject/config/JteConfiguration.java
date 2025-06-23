package ua.edu.chnu.springjpaproject.config;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.WriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import gg.jte.resolve.ResourceCodeResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;

@Configuration
public class JteConfiguration {

    @Value("${gg.jte.template-location:templates}")
    String templateLocation;

    @Value("${gg.jte.development-mode:false}")
    boolean developmentMode;

    @Bean
    public TemplateEngine templateEngine() {
        try {
            if (developmentMode) {
                // В режимі розробки використовуємо DirectoryCodeResolver
                Path templatePath = Paths.get(new ClassPathResource(templateLocation).getURI());
                return TemplateEngine.create(new DirectoryCodeResolver(templatePath), ContentType.Html);
            } else {
                // В продакшн використовуємо прекомпільовані шаблони
                return TemplateEngine.createPrecompiled(ContentType.Html);
            }
        } catch (Exception e) {
            // У разі помилки використовуємо ResourceCodeResolver як запасний варіант
            ResourceCodeResolver codeResolver = new ResourceCodeResolver(templateLocation);
            return TemplateEngine.create(codeResolver, ContentType.Html);
        }
    }

    // Визначаємо клас JteView для використання в ViewResolver
    public static class JteView implements View {
        private final TemplateEngine templateEngine;
        private final String viewName;

        public JteView(TemplateEngine templateEngine, String viewName) {
            this.templateEngine = templateEngine;
            this.viewName = viewName;
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.setContentType(getContentType());
            WriterOutput output = new WriterOutput(response.getWriter());
            templateEngine.render(viewName, model, output);
        }

        @Override
        public String getContentType() {
            return "text/html;charset=UTF-8";
        }
    }

    @Bean
    public ViewResolver customJteViewResolver(TemplateEngine templateEngine) {
        return new ViewResolver() {
            @Override
            public View resolveViewName(String viewName, Locale locale) throws Exception {
                // Якщо viewName не закінчується на .jte, додаємо суфікс
                String resolvedViewName = viewName.endsWith(".jte") ? viewName : viewName + ".jte";
                return new JteView(templateEngine, resolvedViewName);
            }
        };
    }
}
