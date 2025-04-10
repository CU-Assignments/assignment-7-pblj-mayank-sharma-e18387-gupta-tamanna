import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String roll = request.getParameter("roll");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", "root", "your_password");

            String sql = "INSERT INTO attendance (name, roll, date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setString(3, date);
            ps.setString(4, status);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();

            if (i > 0) {
                request.setAttribute("studentName", name);
                RequestDispatcher rd = request.getRequestDispatcher("attendance-success.jsp");
                rd.forward(request, response);
            } else {
                response.getWriter().println("Failed to record attendance.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
