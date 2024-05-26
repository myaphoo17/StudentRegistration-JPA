<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Update Course</title>
    <!-- Include Tailwind CSS from a CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-700 text-gray-300 p-4">
    <div class="container mx-auto bg-gray-800 p-6 rounded-lg shadow-lg">
        <h1 class="text-2xl font-bold mb-6 text-gray-100">Update Course</h1>

        <form:form action="/studentregistrationspringjpa/updatecourse" method="post" modelAttribute="bean" class="bg-gray-800 p-6 rounded-md shadow-md">
            <form:hidden path="id"/>

            <div class="mb-4">
                <form:label path="name" class="block text-sm font-medium text-gray-400">Course Name</form:label>
                <form:input path="name" class="mt-1 p-2 w-full bg-gray-700 border rounded-md text-gray-300"/>
                <div class="text-red-400">
                    <form:errors path="name"/>
                </div>
            </div>

            <div class="mb-4">
                <form:label path="courseFees" class="block text-sm font-medium text-gray-400">Course Fees</form:label>
                <form:input type="number" path="courseFees" class="mt-1 p-2 w-full bg-gray-700 border rounded-md text-gray-300"/>
                <div class="text-red-400">
                    <form:errors path="courseFees"/>
                </div>
            </div>

            <div class="mb-4">
                <form:label path="courseDescription" class="block text-sm font-medium text-gray-400">Course Description</form:label>
                <form:textarea path="courseDescription" class="mt-1 p-2 w-full bg-gray-700 border rounded-md text-gray-300" rows="4"/>
                <div class="text-red-400">
                    <form:errors path="courseDescription"/>
                </div>
            </div>

            <div class="mb-4">
                <button type="submit" class="py-2 px-4 bg-gray-700 text-white rounded-md hover:bg-blue-700">Update Course</button>
            </div>
        </form:form>
    </div>
</body>

</html>
