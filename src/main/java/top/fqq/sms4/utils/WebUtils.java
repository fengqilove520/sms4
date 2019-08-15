package top.fqq.sms4.utils;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public abstract class WebUtils {

    public static void jsonResponse(Object object, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String json = object instanceof String ? (String)object : new Gson().toJson(object);
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return "";
    }
}
