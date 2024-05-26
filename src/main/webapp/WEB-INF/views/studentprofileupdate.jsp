<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
 <%@ include file="header.jsp" %>
 
 
    <!-- Profile Card -->
    <div class="container mx-auto mt-8 px-4 flex justify-center">
        <div class="bg-white shadow-lg rounded-lg overflow-hidden max-w-md w-full">
            <!-- Profile Form -->
             <form:form class="px-6 py-6" id="profileForm" method="post" action="stuprofileupdate" modelAttribute="studentinform" enctype="multipart/form-data">
                 <!-- Editable Profile Picture -->
                <div class="flex justify-center mb-6">
                    <label for="photo" class="cursor-pointer flex flex-col items-center">
                        <img src="data:image/jpeg;base64,${photoBase64}" alt="Profile Picture" class="rounded-full h-32 w-32 object-cover border-4 border-white shadow-md" id="profileImage">
                        <span class="block mt-2 text-sm text-gray-500">Click to change photo</span>
                        <input type="file" id="photo" name="photo"  class="hidden" accept="image/*" onchange="handlePhotoChange(event)">
                    </label>
                </div>
                <!-- Form Fields -->
                <div class="space-y-4">
                    <!-- Name -->
                    <div class="flex items-center">
                        <label for="name" class="w-24 text-gray-700">Name:</label>
                        <input type="hidden" name="id" value="${stuid}">
                        <input type="text" id="name"  name="name" value="${studata.name }" class="w-full py-2 px-3 border rounded-lg" />
                    </div>
                    <!-- DOB -->
                    <div class="flex items-center">
                        <label for="dob" class="w-24 text-gray-700">DOB:</label>
                        <input type="date" id="dob"  name="dob" value="${studata.dob }" class="w-full py-2 px-3 border rounded-lg" />
                    </div>
                    
                    <!-- Phone -->
                    <div class="flex items-center">
                        <label for="phone" class="w-24 text-gray-700">Phone:</label>
                        <input type="text" id="phone" name="phno"  value="${studata.phno }" class="w-full py-2 px-3 border rounded-lg" />
                    </div>
                    
                    <!-- Address -->
                    <div class="flex items-center">
                        <label for="address" class="w-24 text-gray-700">Address:</label>
                        <input type="text" id="address" name="address" value="${studata.address }" class="w-full py-2 px-3 border rounded-lg" />
                    </div>
                    
                    <!-- Gender -->
                    <div class="flex items-center">
                        <label class="w-24 text-gray-700">Gender:</label>
                        <div class="flex items-center space-x-4">
                            <label for="gender-male" class="flex items-center">
                                <input type="radio" id="gender-male" name="gender" value="Male"  class="mr-2" <c:if test="${studata.gender == 'Male'}">checked</c:if>/>
                                <span>Male</span>
                            </label>
                            <label for="gender-female" class="flex items-center">
                                <input type="radio" id="gender-female" name="gender"  value="Female" class="mr-2" <c:if test="${studata.gender == 'Female'}">checked</c:if> />
                                <span>Female</span>
                            </label>
                        </div>
                    </div>
                    <div class="flex items-center">
                        <label for="address" class="w-24 text-gray-700">Education:</label>
                        <input type="text" id="address" name="education" value="${studata.education }" class="w-full py-2 px-3 border rounded-lg" />
                    </div>
                <div class="text-center mt-4">
                    <a href="/studentregistrationspringjpa/updateprofile"><button  class="bg-violet-950 hover:bg-violet-700 text-white font-semibold py-2 px-4 rounded-lg">
                       Update Profile
                    </button></a>
                </div>
             </form:form>
       </div>
       
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
