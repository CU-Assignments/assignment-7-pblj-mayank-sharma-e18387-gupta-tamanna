<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Attendance Submitted</title>
</head>
<body>
    <h2>Attendance Submitted Successfully!</h2>
    <p>Thank you, <strong><%= request.getAttribute("studentName") %></strong>. Your attendance has been recorded.</p>
</body>
</html>
