<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Header Section -->
	<c:choose>


		<c:when test="${loggedInData.role == 'User'}">
			<header class="bg-violet-950 text-white py-6 sticky top-0 z-10">
				<div
					class="container mx-auto flex justify-between items-center px-4">
					<div class="text-2xl font-semibold">Moderators</div>
					<nav>
					<a href="/studentregistrationspringjpa/"
						class="mx-2 hover:underline">Home</a> <a href="/studentregistrationspringjpa/createActivities"
						class="mx-2 hover:underline">Activity</a><a href="/studentregistrationspringjpa/user_profile"
						class="mx-2 hover:underline">Profile</a> <a href="/studentregistrationspringjpa/usertimeline"
						class="mx-2 hover:underline">Timeline</a> 
						<a href="/studentregistrationspringjpa/regstudents"
						class="mx-2 hover:underline"><i class="fa-solid fa-user-group"></i></a> <a
						href="/studentregistrationspringjpa/logout"
						class="mx-2 hover:underline">logout</a>
					</nav>
					
				</div>
			</header>
		</c:when>


		<c:when test="${loggedInData == null}">
			<header class="bg-blue-950 text-white py-7 sticky top-0 z-10">
    <div class="container mx-auto flex justify-between items-center px-4">
        <div class="text-2xl">ACE Inspiration</div>
        <nav class="flex items-center">
            <a href="/studentregistrationspringjpa/" class="mx-1 hover:underline">Home</a> 
            <a href="/studentregistrationspringjpa/createActivities" class="mx-1 hover:underline">Activity</a> 
            <a href="/studentregistrationspringjpa/coursecard" class="mx-1 hover:underline"> Courses</a> 
            <a href="/studentregistrationspringjpa/login" class="mx-1 hover:underline">Login</a> 
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
		</c:when>
		<c:otherwise>
			<header class="bg-violet-950 text-white py-6 sticky top-0 z-10">
				<div
					class="container mx-auto flex justify-between items-center px-4">
					<div class="text-2xl">Student Dashboard</div>
					<nav>
						<a href="/studentregistrationspringjpa/"
							class="mx-2 hover:underline">Home</a> <a href="createActivities"
							class="mx-2 hover:underline">Discussion Forum</a> <a
							href="/studentregistrationspringjpa/student_profile" class="mx-2 hover:underline">Profile</a> <a
							href="/studentregistrationspringjpa/timeLine" class="mx-2 hover:underline">Timeline</a> 
							<a href="contact" class="mx-2 hover:underline">Contact</a>
							
                <a href="#" class="hover:underline" onclick="toggleDropdown('settings')">Others</a>
						<div id="settingsDropdown" class="hidden absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg">
							<a href="displaydeletedposts"
								class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Trash</a> <a href="likedposts"
								class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Like Post</a> <a href="/studentregistrationspringjpa/logout"
								class="block px-4 py-2 text-gray-800 hover:bg-gray-200">logout
								</a>
						</div>
						
					</nav>
				</div>
			</header>
		</c:otherwise>
	</c:choose>

</body>
</html>
<script>
    function toggleDropdown(dropdownId) {
        var dropdown = document.getElementById(dropdownId + "Dropdown");
        dropdown.classList.toggle("hidden");
    }
</script>