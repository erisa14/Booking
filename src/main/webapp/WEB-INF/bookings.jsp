<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-5">
<h1>Bookings: ${event.eventName}</h1>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col" class="col-md-4"> The Booker</th>
        <th scope="col" class="col-md-4">Email</th>
        <th scope="col" class="col-md-2">Event Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="booking">
        <tr>
            <td>${booking.userName}</td>
            <td>${booking.email}</td>
            <td>${booking.event.eventDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
