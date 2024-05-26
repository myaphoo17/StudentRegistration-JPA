<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Post</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<%@ include file="header.jsp" %>

<div class="max-w-md mx-auto mt-8 bg-white p-8 rounded shadow-lg">
        <button onclick="goBack()" class="text-blue-500 mb-4"><i class="fas fa-arrow-left"></i> Back</button>

    <h2 class="text-2xl font-bold mb-4">Update Post</h2>
    <c:if test="${not empty error}">
        <div class="text-red-500 mb-4">${error}</div>
    </c:if>
    <form action="/studentregistrationspringjpa/updatestudentpost" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${post.id}">
        
        <div class="mb-4">
            <label for="title" class="block font-medium text-gray-700">Title:</label>
            <input type="text" id="title" name="title" value="${post.title}" required
                   class="mt-1 p-2 border border-gray-300 rounded-md w-full">
        </div>
        
        <div class="mb-4">
            <label for="detail" class="block font-medium text-gray-700">Detail:</label>
            <textarea id="detail" name="detail" rows="2" cols="50" required
                      class="mt-1 p-2 border border-gray-300 rounded-md w-full">${post.detail}</textarea>
        </div>
        
        <div class="mb-4">
            <label for="photo" class="block font-medium text-gray-700">Photo:</label>
            <input type="file" id="photo" name="photo" accept="image/*"
                   class="mt-1 p-2 border border-gray-300 rounded-md w-full">
        </div>
        
        <div class="mb-4">
            <label for="stuphotoactivities" class="block font-medium text-gray-700">Student Photo Activities:</label>
            <img src="data:image/jpeg;base64,${post.stuphotoactivities}" alt="Student Photo Activities"
                 class="mt-1 rounded-md">
        </div>
        
      <%--   <div class="mb-4">
            <label for="stuphotoprofile" class="block font-medium text-gray-700">Student Profile Photo:</label>
            <img src="data:image/jpeg;base64,${post.stuphotoprofile}" alt="Student Profile Photo"
                 class="mt-1 rounded-md">
        </div> --%>
        
        <div class="mt-6">
            <input type="submit" value="Update Post"
                   class="px-4 py-2 bg-blue-500 text-white rounded-md cursor-pointer hover:bg-blue-600">
        </div>
    </form>
</div>
</body>
</html>
  <script>
        function goBack() {
            window.history.back();
        }
    </script>
