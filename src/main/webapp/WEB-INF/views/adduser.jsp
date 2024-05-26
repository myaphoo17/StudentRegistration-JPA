<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff Registration form</title>
    <!-- Include Tailwind CSS from a CDN -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="bg-gray-700 py-8">

    <div class="max-w-md mx-auto bg-gray-100 rounded-md shadow-md p-8">
        <h2 class="text-2xl font-semibold mb-4 text-center">Staff Registration form</h2>
	<div style="color:red;">${error }</div>
        <!-- Display errors if any -->

        <form action="/studentregistrationspringjpa/adduser" method="post" enctype="multipart/form-data" class="space-y-4">

            <div class="mb-4">
                <label for="name" class="block text-gray-700 text-sm font-bold mb-2">Name</label>
                <input type="text" name="name" id="name" value="${data.name }" class="form-input rounded-md shadow-sm mt-1 block w-full px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Enter your name" />
            </div>

            <div class="mb-4">
                <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email</label>
                <input type="email" name="email" id="email" value="${data.email }" class="form-input rounded-md shadow-sm mt-1 block w-full px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Enter your email" />
            </div>

            <div class="mb-4">
                <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password</label>
                <input type="password" name="password" id="password" value="${data.password }" class="form-input rounded-md shadow-sm mt-1 block w-full px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Enter your password" />
            </div>

            <div class="mb-4">
                <label for="role" class="block text-gray-700 text-sm font-bold mb-2">Role</label>
                <select name="role" id="role" class="form-select rounded-md shadow-sm mt-1 block w-full px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                    <option value="User">Student Manager</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>

            <div class="mb-4">
                <label for="photo" class="block text-gray-700 text-sm font-bold mb-2">Photo</label>
                <input type="file" name="photo" id="photo" class="form-input rounded-md shadow-sm mt-1 block w-full px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" accept="image/*" />
            </div>

            <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out">Register</button>
        </form>
    </div>

</body>

</html>
