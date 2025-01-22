<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Events</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-5">
<h1>Events</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col" class="col-md-4">Event</th>
        <th scope="col" class="col-md-4">Date</th>
        <th scope="col" class="col-md-2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${events}" var="event">
        <tr>
            <td>${event.eventName}</td>
            <td>${event.eventDate}</td>
            <td><a href="/create/booking/${event.id}" class="btn btn-primary">Book</a>
            <a href="/bookings/${event.id}" class="btn btn-secondary">View</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
