<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- Include Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 antialiased p-8 bg-gray-700 text-white">
    <!-- Dashboard Section -->
    <section id="dashboard">
        <h2 class="text-3xl font-semibold mb-6">Dashboard</h2>
        <div class="grid grid-cols-2 md:grid-cols-2 gap-6">
         
            <div class="bg-white p-6 rounded shadow-lg">
                <h3 class="text-lg font-semibold text-gray-600">Total Courses</h3>
                <p class="text-3xl font-bold text-gray-900">${totalCourses}</p>
            </div>
            
            <div class="bg-white p-6 rounded shadow-lg">
                <h3 class="text-lg font-semibold text-gray-600">Total Students</h3>
                <p class="text-3xl font-bold text-gray-900">${totalStudents}</p>
            </div> 


            <div class="bg-white p-6 rounded shadow-lg">
                <h3 class="text-lg font-semibold text-gray-600">Total Moderators</h3>
                <p class="text-3xl font-bold text-gray-900">${totalStudentManagers}</p>
            </div>
            <!-- Fourth Summary Card -->
            <div class="bg-white p-6 rounded shadow-lg">
                <h3 class="text-lg font-semibold text-gray-600">Total Earnings</h3>
                <p class="text-3xl font-bold text-gray-900">${totalEarnings}0 MMK</p>
            </div>
        </div>
    </section>
</body>


</html>
