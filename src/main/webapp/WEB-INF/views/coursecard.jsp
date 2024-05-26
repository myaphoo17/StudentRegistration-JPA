<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Enrollment</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    
	<header class="bg-blue-950 text-white py-7 sticky top-0 z-10">
    <div class="container mx-auto flex justify-between items-center px-4">
        <div class="text-2xl">ACE Inspiration</div>
        <nav class="flex items-center">
            <a href="/studentregistrationspringjpa/" class="mx-1 hover:underline">Home</a> 
            <a href="createActivities" class="mx-1 hover:underline">Activity</a> 
            <a href="coursecard" class="mx-1 hover:underline"> Courses</a> 
            <a href="login" class="mx-1 hover:underline">Login</a> 
            <a href="contact" class="mx-1 hover:underline">Contact</a> 
            <!-- Dropdown for "Others" -->
            <div class="relative mx-1">
                <a href="#" class="hover:underline" onclick="toggleDropdown('others')">Others</a>
                <!-- Dropdown Content for "Others" -->
                <div id="othersDropdown" class="hidden absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg">
                    <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Option 1</a>
                    <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Option 2</a>
                    <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Option 3</a>
                </div>
            </div>
        </nav>
    </div>
</header>
    <style>
        /* Add a simple scale animation for when a card is clicked */
        .card:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body class="flex flex-col min-h-screen bg-gray-300">

    
    <div class="container mx-auto">
      
        <form action="#" method="POST" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
           
            <c:forEach var="course" items="${allcourses}">
                <div class="card border bg-gray-200 p-4 mt-3 mb-2 ml-2 rounded-lg shadow-lg transition-transform duration-300 ease-in-out">
                    <h2 class="text-2xl font-bold mb-2">${course.name}</h2>
                    <p class="text-gray-600 mb-2">${course.courseDescription}</p>
                    <p class="text-gray-800 mb-4"><strong>Amount:</strong> ${course.courseFees}</p>
                    <a href="/studentregistrationspringjpa/createstudent?cid=${course.id}"><button type="button" name="courseId" value="1"
                    class="w-full py-2 bg-blue-950 text-white rounded hover:bg-blue-800 transition">
                    Enroll
                </button></a>
                </div>
            </c:forEach>
        </form>
    </div>
</body>
</html>
