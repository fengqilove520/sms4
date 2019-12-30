package top.fqq.sms4.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
public abstract class WebUtils {

    private static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";

    public static void jsonResponse(Object object, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            response.setContentType(JSON_CONTENT_TYPE);
            String json = object instanceof String ? (String)object : new Gson().toJson(object);
            response.setContentLength(json.length());
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static String getRequestBody(HttpServletRequest req) {
        try {
            BufferedReader reader = req.getReader();
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            return json;
        } catch (IOException e) {

        }
        return "";
    }
}
