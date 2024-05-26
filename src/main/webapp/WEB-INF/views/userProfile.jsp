<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Home</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="flex flex-col min-h-screen bg-gray-200">

    <%@ include file="header.jsp" %>

    <!-- Profile Card -->
    <div class="container mx-auto mt-8 px-4 flex justify-center">
        <div class="bg-white shadow-lg rounded-lg overflow-hidden max-w-md w-full">
            <!-- Profile Form -->
            <form class="px-6 py-6" id="profileForm" enctype="multipart/form-data">
                <!-- Editable Profile Picture -->
                <div class="flex justify-center mb-6">
                    <label for="photo" class="cursor-pointer flex flex-col items-center">
                        <img src="data:image/jpeg;base64,${photoBase64}" alt="Profile Picture" class="rounded-full h-32 w-32 object-cover border-4 border-white shadow-md" id="profileImage">
                        <span class="block mt-2 text-sm text-gray-500">Click to change photo</span>
                        <input type="file" id="photo" name="photo" class="hidden" accept="image/*" onchange="handlePhotoChange(event)">
                    </label>
                </div>
                
                <!-- Form Fields -->
                <div class="space-y-4">
                    <!-- Name -->
                    <div class="flex items-center">
                        <label for="name" class="w-24 text-gray-700">Name:</label>
                        <input type="text" id="name" name="name" value="${user.name }" class="w-full py-2 px-3 border rounded-lg" readonly/>
                    </div>
                    
                    <!-- Email -->
                    <div class="flex items-center">
                        <label for="email" class="w-24 text-gray-700">Email:</label>
                        <input type="email" id="email" name="email" value="${user.email}" class="w-full py-2 px-3 border rounded-lg" readonly/>
                    </div>
       
                  
                </div>
                
               <!--  <!-- Save Button -->
               <!--  <div class="text-center mt-4">
                    <button type="submit" class="bg-violet-950 hover:bg-violet-700 text-white font-semibold py-2 px-4 rounded-lg">
                        Save Changes
                    </button>
                </div> -->
            </form>
        </div>
    </div>

    <!-- JavaScript -->
    <script>
        // Function to handle photo change
        function handlePhotoChange(event) {
            const file = event.target.files[0];
            const reader = new FileReader();
            
            reader.onload = function(e) {
                document.getElementById('profileImage').src = e.target.result;
            };
            
            if (file) {
                reader.readAsDataURL(file);
            }
        }
    </script>

</body>

</html>
    