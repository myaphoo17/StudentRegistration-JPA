<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Course List</title>
    <!-- Include Tailwind CSS from a CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Include DataTables CSS from a CDN -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
</head>

<body class="bg-gray-700 text-white ">


    <div class="max-w-6xl mx-auto p-5">
<a href="./setupaddcourse" class="mt-2 py-3 px-4 bg-gray-800 text-white rounded-md hover:bg-blue-700">
 Add course
 <br>
</a>
<br> <br>
        <!-- Messages -->
        <div class="mb-4">
            <div class="text-blue-300 mb-2">${msg}</div>
            <div class="text-red-300 mb-2">${error}</div>
        </div>
   <h4 class="ml-2 "> Search Course</h4>
 
        <!-- Table -->
        <div class="overflow-x-auto  shadow-md rounded-md text-white p-4 mt-1 bg-gray-800">
     
            <table id="courseTable" class="w-full divide-y divide-gray-800">
                <thead class="bg-gray-800">
                    <tr>
                        <th class="px-4 py-2 text-left text-gray-300">NO</th>
                        <th class="px-4 py-2 text-left text-gray-300">Course Name</th>
                        <th class="px-4 py-2 text-left text-gray-300">Course Fees</th>
                        <th class="px-4 py-2 text-left text-gray-300">Course Description</th>
                        <th class="px-4 py-2 text-left text-gray-300">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="course" varStatus="loop">
                        <tr class="hover:bg-gray-700">
                            <td class="px-4 py-2">${loop.index + 1}</td>
                            <td class="px-4 py-2">${course.name}</td>
                            <td class="px-4 py-2">${course.courseFees}</td>
                            <td class="px-4 py-2">${course.courseDescription}</td>
                            <td class="px-4 py-2">
                                <a href="./setupcourseupdate/${course.id}" class="text-blue-300 hover:text-blue-400">Update</a> |
                                <a href="./deletecourse/${course.id}" class="text-red-300 hover:text-red-400">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

    <!-- Include jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Include DataTables JavaScript -->
    <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
    <!-- Initialize DataTable -->
    <script>
        $(document).ready(function() {
            $('#courseTable').DataTable();
        });
    </script>
</body>

</html>
