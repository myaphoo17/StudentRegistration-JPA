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

<c:if test="${loggedInData.role == 'User'}">
    <main class="flex-grow mx-auto justify-content-center max-w-lg w-full py-6">
        <div id="posts-container" class="space-y-4 w-full">
<c:if test="${loggedInData.role == 'User'}">
    <div class="mb-6 bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold mb-2">Create a New Post</h2>
        
        <form:form action="adduserpost" method="post" modelAttribute="post" enctype="multipart/form-data">
             <div style="color:red;">${success}</div>
            <div style="color:red;">${error}</div>
            <!-- Post content -->
            <div class="mb-4">
                <input id="content" name="title" class="w-full p-2 border rounded" placeholder="What's title?" />
            </div>
            <div class="mb-4">
                <textarea id="content" name="detail" class="w-full p-2 border rounded" placeholder="What's on your mind?"></textarea>
            </div>

            <!-- Image selection area -->
            <div class="mb-4">
                <div class="w-full border rounded p-2 flex justify-center items-center bg-gray-100 cursor-pointer">
                    <!-- Hidden file input -->
                    <input type="file" id="image-upload" name="photo" accept="image/*" class="hidden">
                    <!-- Label to trigger file input -->
                    <label for="image-upload" class="flex items-center cursor-pointer">
                        <i class="fas fa-camera text-gray-600 text-2xl"></i>
                        <span class="ml-2 text-gray-600">Photo/Video</span>
                    </label>
                </div>
            </div>

            <!-- Submit button -->
            <button type="submit" class="w-full py-2 bg-violet-900 text-white rounded hover:bg-violet-700">
                <i class="fas fa-paper-plane"></i> Post
            </button>
        </form:form>
    </div>
</c:if>
</div></main>
</c:if>
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
                        <a href="setupupdatepost/${post.id}"><button class="text-gray-500 hover:text-violet-700">
                            <i class="fas fa-edit"></i> Edit
                        </button></a>
                        <a href="deletepost/${post.id}"><button class="text-gray-500 hover:text-violet-700">
                            <i class="fas fa-trash"></i> Delete
                        </button></a>
                        
                    </div>
                </div>
<br>
            <!-- Bind post description here pls change p tag into input type  -->
                <p>${post.detail}</p>
        
        		 <c:if test="${not empty post.base64photo}">
                <div class="mt-2">
                    <!-- Adjusting image width and height -->
                    <img src="data:image/jpeg;base64,${post.base64photo}" alt="Post Image" class="w-full max-w-md h-full object-cover rounded-lg">
                </div>
                </c:if>
                <!-- Reaction counts -->
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
