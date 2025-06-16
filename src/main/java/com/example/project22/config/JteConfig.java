package com.example.project22.config;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.WriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@Configuration
public class JteConfig {

    @Bean
    public TemplateEngine templateEngine() throws IOException {
        // Для розробки використовуємо файли з директорії resources/templates
        ClassPathResource resource = new ClassPathResource("templates");
        File templateDir;

        if (resource.exists()) {
            templateDir = resource.getFile();
            DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of(templateDir.getAbsolutePath()));
            return TemplateEngine.create(codeResolver, ContentType.Html);
        } else {
            // Якщо директорія не існує, використовуємо вбудований TemplateEngine
            return TemplateEngine.createPrecompiled(ContentType.Html);
        }
    }

    @Bean
    public ViewResolver jteViewResolver(TemplateEngine templateEngine) {
        // Замість JteViewResolver створюємо простий UrlBasedViewResolver
        UrlBasedViewResolver resolver = new UrlBasedViewResolver() {
            @Override
            protected AbstractTemplateView buildView(String viewName) throws Exception {
                return new JteView(templateEngine, viewName + ".jte");
            }
        };
        resolver.setViewNames("*");
        return resolver;
    }

    // Власний клас для відображення JTE шаблонів
    private static class JteView extends AbstractTemplateView {
        private final TemplateEngine templateEngine;
        private final String templateName;

        public JteView(TemplateEngine templateEngine, String templateName) {
            this.templateEngine = templateEngine;
            this.templateName = templateName;
        }

        @Override
        protected void renderMergedTemplateModel(Map<String, Object> model,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
            // Wrap the PrintWriter in a WriterOutput to make it compatible with JTE
            WriterOutput output = new WriterOutput(response.getWriter());
            templateEngine.render(templateName, model, output);
        }
    }
}
