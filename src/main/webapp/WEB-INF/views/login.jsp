<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style type="text/css">
    .error{
    color:red;
    }
    </style>
</head>

<body class="flex items-center justify-center min-h-screen bg-slate-900">
    <!-- Login form container -->
    <div class="w-3/4 max-w-md p-6 bg-white shadow-lg rounded-lg">
        <!-- Branding section -->
        <div class="text-center mb-6">
            <div class="text-3xl text-blue-950 mb-2">ACE Inspiration</div>
            <p class="text-gray-500">Welcome Back!</p>
            <div class="error">${error }</div>
        </div>

        <!-- Login form -->
            <form:form action="login" method="post" modelAtribute="user" enctype="multipart/form-data">
            <!-- Username field -->
            <div class="mb-4">
                <label for="username" class="block text-blue-950 font-semibold">Useremail</label>
                <div class="relative">
                    <input type="text" id="username" name="email" value="${user.email }" placeholder="Enter User Email" required class="w-full p-2 mt-1 border rounded-md focus:border-blue-500 focus:outline-none text-blue-950">
                    <i class="bx bx-user absolute top-2 right-2 text-blue-950"></i>
                </div>
            </div>

            <!-- Password field -->
            <div class="mb-4">
                <label for="password" class="block text-blue-950 font-semibold">Password</label>
                <div class="relative">
                    <input type="password" id="password" name="password" placeholder="Enter User Password" required class="w-full p-2 mt-1 border rounded-md focus:border-blue-500 focus:outline-none text-blue-950">
                    <i id="show-password" class="bx bx-lock-alt absolute top-2 right-2 text-blue-950 cursor-pointer"></i>
                </div>
            </div>

            <!-- Remember Me and Forgot Password links -->
            <div class="flex justify-between items-center">
                <div>
                    <label for="remember" class="inline-flex items-center text-blue-950">
                        <input type="checkbox" id="remember" name="remember" class="form-checkbox text-blue-500">
                        <span class="ml-2" >Remember me</span>
                    </label>
                </div>
                <div>
                    <a href="forgetpassword" class="text-blue-500 hover:underline text-sm text-blue-950">Forgot password?</a>
                </div>
            </div>

            <!-- Login button -->
            <div class="mt-4">
                <button type="submit" class="w-full py-2 bg-blue-950 text-white font-semibold rounded-md hover:bg-blue-600 transition">Login</button>
            </div>

            <!-- Register link -->
            <div class="text-center mt-4 text-sm text-blue-950">
                <span>Don't have an account?</span>
                <a href="createstudent" class="text-blue-800 hover:underline">Register</a>
            </div>
        </form:form>
    </div>

    <!-- Optional JavaScript for showing/hiding password -->
    <script>
        const showPasswordIcon = document.getElementById('show-password');
        const passwordField = document.getElementById('password');

        // Function to toggle password visibility
        function togglePasswordVisibility() {
            if (passwordField.type === 'password') {
                // Change the type to 'text' to show the password
                passwordField.type = 'text';
                // Change the icon to indicate the password is visible
                showPasswordIcon.classList.remove('bx-lock-alt');
                showPasswordIcon.classList.add('bx-show');
            } else {
                // Change the type back to 'password' to hide the password
                passwordField.type = 'password';
                // Change the icon back to indicate the password is hidden
                showPasswordIcon.classList.remove('bx-show');
                showPasswordIcon.classList.add('bx-lock-alt');
            }
        }

        // Add event listener to the show-password icon
        showPasswordIcon.addEventListener('click', togglePasswordVisibility);
    </script>
</body>

</html>
    