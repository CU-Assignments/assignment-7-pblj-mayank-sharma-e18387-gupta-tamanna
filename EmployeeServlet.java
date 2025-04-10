import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String showAll = request.getParameter("showAll");
        String id = request.getParameter("id");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Employee Details</h2>");

        try {
            Properties props = new Properties();
            props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs;

            if ("true".equals(showAll)) {
                rs = stmt.executeQuery("SELECT * FROM employees");
            } else if (id != null && !id.trim().isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM employees WHERE id = " + id);
            } else {
                out.println("<p>Please enter an ID or click Show All.</p>");
                return;
            }

            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>"
                        + rs.getString("name") + "</td><td>"
                        + rs.getString("department") + "</td><td>"
                        + rs.getString("email") + "</td></tr>");
            }

            if (!hasData) {
                out.println("<tr><td colspan='4'>No employee found</td></tr>");
            }

            out.println("</table>");
            conn.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
