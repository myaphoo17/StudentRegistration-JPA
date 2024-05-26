<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
    <script src="https://cdn.tailwindcss.com"></script>
   
</head>

<body class="bg-gray-700 text-white p-4">

<!-- <a href="./setadduser" class="mt-2 py-3 px-4 bg-gray-800 text-white rounded-md hover:bg-blue-700">
 Add Student Manager

</a> -->
    <div class="mt-4 text-blue-300">${msg}</div>
    <div class="mt-4 text-red-300">${error}</div>

  

    <table id="userTable" class="min-w-full mt-4 bg-gray-800 border border-gray-600 rounded-lg shadow-md">
        <thead class="bg-gray-800 text-white">
            <tr>
                <th class="py-2 px-4">NO</th>
                <th class="py-2 px-4">Photo</th>
                <th class="py-2 px-4">Name</th>
                <th class="py-2 px-4">Email</th>
                <th class="py-2 px-4">Course</th>
                <th class="py-2 px-4">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${registerStudents}" var="post" varStatus="loop">
                <tr>
                    <td class="py-2 px-4 border-b border-gray-600">${loop.index + 1}</td>
                    <td class="py-2 px-4 border-b border-gray-600">
                       <img src="data:image/jpeg;base64,${post.student.bindprofilephoto}" alt="User Photo" class="w-8 h-8 rounded-full" />
                    </td>
                    <td class="py-2 px-2 border-b border-gray-600">${post.student.name}</td>
                    <td class="py-2 px-4 border-b border-gray-600">${post.student.email}</td>
                    <td class="py-2 px-4 border-b border-gray-600">${post.course.name}</td>
                    <td class="py-2 px-4 border-b border-gray-600">
                        <a href="/studentregistrationspringjpa/deletestudent/${post.student.id}" class="text-red-300 hover:underline">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#userTable').DataTable({
                searching: true, // Enable search feature
                paging: true // Enable pagination
            });
        });
    </script>
</body>

</html>
