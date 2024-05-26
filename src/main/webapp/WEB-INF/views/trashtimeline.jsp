<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Student Home</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>

<body class="flex flex-col min-h-screen bg-gray-300">

    <!-- Header Section -->
     <%@ include file="header.jsp" %>
<main class="flex-grow mx-auto justify-content-center max-w-lg w-full py-6">
        <div id="posts-container" class="space-y-4 w-full">


    <main class="flex-grow mx-auto max-w-lg py-6">
 
        <div class="space-y-4">
        <c:forEach items="${postDTOList}" var="post">
            <!-- Post 1 -->
            <div class="bg-white p-4 rounded-lg shadow-lg">
                <!-- Wrapper container for h3 and buttons -->
                <div class="flex justify-between items-center">
                
                    <h3 class="font-semibold text-md">${post.title}</h3>
                    
                    <!-- Edit and Delete buttons shifted to the right -->
                    <div class="flex space-x-2">
                        
                        <a href="recover/${post.id}"><button class="text-gray-500 hover:text-violet-700">
                            <i class="fas fa-trash"></i> Recover
                        </button></a>
                        
                    </div>
                </div>
<br>
            <!-- Bind post description here pls change p tag into input type  -->
                <p>${post.detail}</p>
        
        		 <c:if test="${not empty post.stuphotoactivities}">
                <div class="mt-2">
                    <!-- Adjusting image width and height -->
                    <img src="data:image/jpeg;base64,${post.stuphotoactivities}" alt="Post Image" class="w-full max-w-md h-full object-cover rounded-lg">
                </div>
                </c:if>
                <!-- Reaction counts -->
                <div class="mt-2 text-sm">
                    <span>10 Likes</span> <span>5 Comments</span>
                </div>
            </div><br><hr><br>
        </c:forEach>
            <!-- Add more posts as needed -->
        </div>
        </main>
    </div>
    </main>

    <!-- Footer Section -->
    <footer class="bg-gray-800 text-white py-4">
        <div class="container mx-auto text-center">
            <p>&copy; 2024 ACE Inspiration. All rights reserved.</p>
        </div>
    </footer>

    <!-- Include Font Awesome for icons -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>

</html>
