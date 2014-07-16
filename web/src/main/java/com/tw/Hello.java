package com.tw;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by twer on 7/15/14.
 */
public class Hello extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello Servlet Get</h1>");
        out.println("<h2>" + new Application() + "</h2>");
        out.println("</body>");
        out.println("</html>");
    }

}
