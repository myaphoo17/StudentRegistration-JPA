<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto">
        <c:choose>
            <c:when test="${num == 1}">
                <form action="/studentregistrationspringjpa/forgetpassword" method="post" class="max-w-md mx-auto mt-8 bg-white p-8 rounded-md shadow-md">
                    <div class="text-center mb-8">
                        <h2 class="text-2xl font-bold text-gray-800">Forgot Password</h2>
                        <p class="text-sm text-gray-600">Enter your email address to reset your password</p>
                    </div>
                    <div class="error text-red-500">${error}</div>
                    <input type="text" name="email" placeholder="Enter Your Email Address" class="border-2 border-gray-300 rounded-md px-4 py-2 w-full mb-4 focus:outline-none focus:border-blue-500">
                    <input type="submit" value="Enter" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-300 w-full">
                </form>
            </c:when>
            <c:when test="${num == 2}">
                <form action="/studentregistrationspringjpa/confirmupdatepassword" method="post" class="max-w-md mx-auto mt-8 bg-white p-8 rounded-md shadow-md">
                    <div class="text-center mb-8">
                        <h2 class="text-2xl font-bold text-gray-800">Create New Password</h2>
                        <p class="text-sm text-gray-600">Enter a new password for your account</p>
                    </div>
                    <input type="hidden" name="email" value="${email}">
                    <span id="passwordError" class="error-message text-red-500"></span>
                    <input type="password" class="border-2 border-gray-300 rounded-md px-4 py-2 w-full mb-4 focus:outline-none focus:border-blue-500" name="password" id="pass" placeholder="Create New Password" oninput="validatePasswords()">
                    <span id="confirmPasswordError" class="error-message text-red-500"></span>
                    <input type="password" class="border-2 border-gray-300 rounded-md px-4 py-2 w-full mb-4 focus:outline-none focus:border-blue-500" id="confirmPass" placeholder="Confirm Password" oninput="validatePasswords()">
                    <input type="submit" value="Submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-300 w-full">
                </form>
            </c:when>
            <c:otherwise>
                <form action="/studentregistrationspringjpa/updatepassword" method="post" class="max-w-md mx-auto mt-8 bg-white p-8 rounded-md shadow-md">
                    <div class="text-center mb-8">
                        <h2 class="text-2xl font-bold text-gray-800">Update Password</h2>
                        <p class="text-sm text-gray-600">Enter the OTP code sent to your email</p>
                    </div>
                    <div class="success text-green-500">${alert}</div>
                    <div class="error text-red-500">${otpfail}</div>
                    <input type="text" name="email" value="${emailvalue}" class="border-2 border-gray-300 rounded-md px-4 py-2 w-full mb-4 focus:outline-none focus:border-blue-500"><br>
                    <input type="text" name="otpcode" placeholder="Enter OTP Code !" class="border-2 border-gray-300 rounded-md px-4 py-2 w-full mb-4 focus:outline-none focus:border-blue-500">
                    <input type="submit" value="Send" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-300 w-full">
                    <!-- <div class="popup" id="popup">
                        <img src="https://www.shutterstock.com/image-vector/green-check-mark-icon-circle-260nw-576516469.jpg" class="w-20 h-20 mx-auto mb-4">
                        <p class="text-lg font-semibold mb-4">Password Update Successful!</p>
                        <a href="/studentregistrationspringjpa/login" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-300 inline-block">Ok</a>
                    </div> -->
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <script>
        function validatePasswords() {
            const password = document.getElementById("pass").value;
            const confirmPassword = document.getElementById("confirmPass").value;

            if (password.length < 8) {
                // Display error message for length requirement
                document.getElementById("passwordError").textContent = "Password must be at least 8 characters long.";
            } else {
                // Clear error message for length requirement
                document.getElementById("passwordError").textContent = "";
            }

            if (password !== confirmPassword) {
                // Display error message for mismatched passwords
                document.getElementById("confirmPasswordError").textContent = "Passwords do not match.";
            } else {
                // Clear error message for mismatched passwords
                document.getElementById("confirmPasswordError").textContent = "";
            }
        }
    </script>
    <!-- <script>
        const popup = document.getElementById("popup");
        var result = ${result};
        const openPopup = () => {
            popup.classList.add("open-popup");
        }
        if (result === 1) {
            openPopup();
        }
    </script> -->
</body>
</html>
