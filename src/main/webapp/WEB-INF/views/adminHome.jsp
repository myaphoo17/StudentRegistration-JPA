<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Panel</title>
<!-- Include Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-800 text-white antialiased">
	<div class="flex min-h-screen">

		<aside
			class="w-64 bg-gray-900 text-white flex-shrink-0 fixed top-0 left-0 h-full">
			<div class="p-6">
				<h2 class="text-2xl font-semibold mb-4">Admin Panel</h2>
				<nav class="space-y-2">
					<a href="./admindashboard"
						class="block p-2 rounded hover:bg-gray-700"
						onclick="loadContent(this)"> <i
						class="fas fa-tachometer-alt mr-2"></i> Dashboard
					</a> <!-- <a href="earnings.html"
						class="block p-2 rounded hover:bg-gray-700"
						onclick="loadContent(this)"> <i
						class="fas fa-dollar-sign mr-2"></i> Earnings
					</a> --> <a href="./courses" class="block p-2 rounded hover:bg-gray-700"
						onclick="loadContent(this)"> <i class="fas fa-book mr-2"></i>
						Courses
					</a> <a href="./students" class="block p-2 rounded hover:bg-gray-700"
						onclick="loadContent(this)"> <i class="fa-solid fa-user-group"></i>
						Students
					</a> <a href="./users" class="block p-2 rounded hover:bg-gray-700"
						onclick="loadContent(this)"> <i class="fa-solid fa-users-gear"></i>
						Moderators
					</a>

<a href="./admins" onclick="loadContent(this)" class="block p-2 rounded hover:bg-gray-700"> <i
								class="fa-solid fa-user-check">
								</i>Admins
								</a>
				<!-- 	<div class="dropdown">
						<a href="#" class="block p-2 rounded hover:bg-gray-700 mb-2"
							onclick="toggleDropdown()"> <i class="fa-solid fa-users-gear"></i>
							Admins
						</a> -->
						
						
						<!-- <div id="adminDropdown" class="dropdown-content hidden">
							<a href="./admins" onclick="loadContent(this)"> <i
								class="fa-solid fa-user-check ml-4 mb-3"></i> Active Admin


							</a> <br> <a href="./#"> <i class="fa-solid fa-user-slash ml-3"></i> Inactive Admin
							
							</a>

						</div> -->
				

					<a href="/studentregistrationspringjpa/logout"
						class="block p-2 rounded hover:bg-gray-700"
						> <i class="fas fa-arrow-left"></i>
						Logout
					</a>
				</nav>
			</div>
		</aside>

		<!-- Main Content -->
		<div class="flex-1 p-8 ml-64 bg-gray-700">
			<iframe id="mainContent" src="admindashboard"
				style="width: 100%; height: 100vh; border: none;"></iframe>
		</div>
	</div>

	<script>
		function loadContent(link) {
			event.preventDefault();
			const iframe = document.getElementById('mainContent');
			iframe.src = link.getAttribute('href');
		}
		function toggleDropdown() {
		    var dropdown = document.getElementById("adminDropdown");
		    dropdown.classList.toggle("hidden");
		}

	</script>

</body>

</html>
