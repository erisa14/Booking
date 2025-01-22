<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-5">
    <h1>Booking</h1>
    <h3>${error}</h3>
    <form:form method="POST" action="/create/booking" modelAttribute="booking"
               class="col-md-5 border border-light rounded border-4 p-5">
        <form:hidden path="event" value="${event.id}" />

        <form:errors path="userName" class="alert-danger"/>
        <div class="input-group mb-3">
            <span class="input-group-text">Name:</span>
            <form:input path="userName" id="userName" class="form-control"/>
        </div>
        <form:errors path="email" class="alert-danger"/>
        <div class="input-group mb-3">
            <span class="input-group-text">Email</span>
            <form:input path="email" id="email" type="email" class="form-control"/>
        </div>
        <button type="submit" class="btn col-3 btn-success float-end ">Create</button>
    </form:form>

</body>
</html>
