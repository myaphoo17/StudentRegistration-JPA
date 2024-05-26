<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ACE Inspiration</title>

<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="flex flex-col min-h-screen bg-gray-200">

	
	<%@ include file="header.jsp" %>
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


	<main class="flex-grow">


		
		<section class="bg-gray-100 py-8 mt-1">
			<div class="container mx-auto px-4">

				<div>
					<h1 class="text-2xl font-bold text-gray-800 mb-3">Join ACE
						Inspiration</h1>
					<p class="text-1xl text-gray-700 mb-8">Your journey to becoming
						a skilled professional starts here. Specializing in web
						development and programming courses.</p>

					<div>
						<a href="coursecard"
							class="bg-blue-950 text-white py-3 px-8 rounded-md font-semibold hover:bg-blue-800 transition">Register Now!</a> <a href="contact"
							class="ml-4 bg-gray-400 text-white py-3 px-8 rounded-md font-semibold hover:bg-gray-300 transition">Contact
							Us</a>
					</div>
				</div>
			</div>

		</section>




		<section class="bg-gray-200 py-10">
			<h2 class="text-center text-2xl font-bold mb-5">Announcements</h2>
			<div
				class="container mx-auto grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

				<c:forEach items="${postDTOList}" var="post">
					<div class="bg-white rounded-lg shadow-md p-4">
						<!-- Post Image -->
						<div class="flex justify-center">
							<img src="data:image/jpeg;base64,${post.base64photo}"
								alt="Post Photo"
								class="w-32 h-32 object-cover rounded-full mb-4">
						</div>
						<!-- Post Title and Details -->
						<h3 class="text-lg font-semibold mb-2">${post.title}</h3>
						<p class="text-gray-700 mb-4">${post.detail}</p>
						<!-- Action Buttons -->
						<c:if test="${loggedInData.role == 'User'}">
						
                        <a href="setupupdatepost/${post.id}"><button type="button" class="btn btn-info">Info</button></a>
                        <a href="deletepost/${post.id}"><button type="button" class="btn btn-secondary">Secondary</button></a>
                    	</c:if>
					</div>
				</c:forEach>

			</div>
		</section>


		
  <section class="bg-white py-12">
            <div class="container mx-auto px-4">
                <h2 class="text-2xl font-bold text-gray-800 mb-4">What Our Students Say</h2>
                <!-- Testimonials content -->
                <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
                    <div class="bg-gray-100 p-6 rounded-lg">
                        <p class="text-gray-700 italic">"ACE Inspiration helped me kickstart my career in web development! The trainers are amazing and the courses are very comprehensive." - Jane D.</p>
                    </div>
                    <div class="bg-gray-100 p-6 rounded-lg">
                        <p class="text-gray-700 italic">"The Android programming course was exactly what I needed to advance my career. Highly recommend!" - John S.</p>
                    </div>
                </div>
            </div>
        </section>
        
        <div class="container mx-auto px-4 mb-3 bg-gray-100">
			<h2 class="text-2xl font-bold text-gray-800 mb-6 mt-4">Why Choose ACE
				Inspiration?</h2>
			<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
				<!-- Add your benefit list here -->
				<div class="text-gray-700">
					<h3 class="font-semibold mb-2">Expert Trainers</h3>
					<p>With industry experience.</p>
				</div>
				<div class="text-gray-700">
					<h3 class="font-semibold mb-2">Comprehensive Courses</h3>
					<p>In web development and Android programming.</p>
				</div>
				<div class="text-gray-700">
					<h3 class="font-semibold mb-2">
						Flexible Learning
						</h3>
						<p>Options to suit your schedule.</p>
				</div>
				<div class="text-gray-700">
					<h3 class="font-semibold mb-2">
						Supportive Environment
						</h3>
						<p>For a great learning experience.</p>
				</div>
			</div>
		</div>
        

	</main>

	<!-- Footer Section -->
	<footer class="bg-gray-800 text-white py-3">
		<div class="container mx-auto text-center px-4">
			<p>&copy; 2024 ACE Inspiration. All rights reserved.</p>
		</div>
	</footer>


</body>
</html>
