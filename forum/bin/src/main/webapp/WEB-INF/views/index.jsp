<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Forum</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>User: ${user.username}|
                    <input type="button" value="Log out" onclick="window.location.href = '/logout'"/>|
                    <input type="button" value="Log In" onclick="window.location.href = '/login'"/>|
                    <input type="button" value="Register" onclick="window.location.href = '/reg'"/>
                    <input type="button" value="Home" onclick="window.location.href = '/'"/>
                </th>
            </tr>
            <tr>
                <th><input type="button" value="Add a Topic" onclick="window.location.href = '/edit'"/></th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="row">
        <h4>Forum Topics</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Topic</th>
                <th>Description</th>
                <th>Created</th>
                <th>Author</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <c:url var="updateButton" value="/edit">
                    <c:param name="id" value="${post.id}"/>
                </c:url>
                <c:url var="deleteButton" value="/delete">
                    <c:param name="id" value="${post.id}"/>
                </c:url>
                <c:url var="goToSubposts" value="/post">
                    <c:param name="postId" value="${post.id}"/>
                </c:url>
                <tr>
                    <td><a href="${goToSubposts}"><c:out value="${post.name}"/></a></td>
                    <td><c:out value="${post.description}"/></td>
                    <td>
                        <fmt:formatDate value="${post.created.time}" type="date"
                                        dateStyle="long"/>
                    </td>
                    <td>${post.author}</td>
                    <td><input type="button" value="Update" onclick="window.location.href = '${updateButton}'"/></td>
                    <td><input type="button" value="Delete" onclick="window.location.href = '${deleteButton}'"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>