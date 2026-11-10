package edu.web.lab04;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/** Renders the timetable in Russian or English using message bundles. */
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean russian = "ru".equalsIgnoreCase(request.getParameter("lang"));
        Locale locale = russian ? Locale.forLanguageTag("ru") : Locale.ENGLISH;
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print("""
                <!DOCTYPE html>
                <html lang="%s">
                <head><meta charset="UTF-8"><title>%s</title></head>
                <body>
                    <h1>%s</h1>
                    <p>%s</p>
                    <a href="?lang=%s" lang="%s">%s</a>
                </body>
                </html>
                """.formatted(locale.getLanguage(), messages.getString("title"), messages.getString("title"),
                messages.getString("description"), russian ? "en" : "ru", russian ? "en" : "ru",
                messages.getString("switch")));
    }
}
