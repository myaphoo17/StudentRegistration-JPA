<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<style>
.popup {
  width: 400px;
  background: #F8F9F9;
  position: fixed;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  padding: 30px;
  visibility: hidden;
  color: black;
  transition: transform 0.4s, top 0.4s;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.open-popup { 
  visibility: visible;
}

.popup img {
  width: 100px;
  border-radius: 50%;
  margin-bottom: 20px;
}

.popup h2 {
  font-size: 24px;
  font-weight: 500;
  margin: 10px 0;
}

.popup button {
  width: 100%;
  padding: 10px 10;
  background: #6fd649;
  color: #fff;
  border: 0;
  outline: none;
  font-size: 20px;
  border-radius: 4px;
  cursor: pointer;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
}
 /* Add a simple scale animation for when a card is hovered */
        .card:hover {
            transform: scale(1.05);
        }
</style>
</head>
<body class="bg-gray-100">
	<%@ include file="header.jsp" %>
	
    <div class="container mx-auto mt-10 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
       <c:forEach items="${registerStudents}" var="post">
        <div class="card border p-4 rounded-lg shadow-lg transition-transform duration-300 ease-in-out">
            <div class="flex items-center mb-4">
                <div class="student-photo w-12 h-12 rounded-full overflow-hidden mr-4">
                    <img src="data:image/jpeg;base64,${post.student.bindprofilephoto}" alt="Student Photo">
                </div>
                <div>
                    <h2 class="text-lg font-semibold">${post.student.name}</h2>
                    <p class="text-gray-500">Course: ${post.course.name}</p>
                </div>
            </div>

            <div class="student-photo w-30 h-30  overflow-hidden mr-4">
                <img src="data:image/jpeg;base64,${post.bindphoto}" alt="Student Photo">
            </div>
            <div>
                <form action="/studentregistrationspringjpa/replystudent" method="post">
                    <input type="hidden" name="email" value="${post.student.email}">
                    <input type="hidden" name="studentid" value="${post.student.id}">
                    <input type="hidden" name="courseid" value="${post.course.id}">
                    <div class="col-md-4">
					    <select class="form-select" aria-label="Education" required name="status">
					        <option value="">Select an option</option>
					        <option value="Accept">Accept</option>
					        <option value="Reject">Reject</option>
					    </select>
					</div>
                    <textarea id="message-3" name="description" required rows="2" class="w-full border rounded-lg p-2 mb-4" placeholder="Enter your message here..."></textarea>
                    <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">Send</button>
                    <div class="popup" id="popup">
		               <img src="https://www.shutterstock.com/image-vector/green-check-mark-icon-circle-260nw-576516469.jpg">
		               <p>Reply Successfully!</p>
		               <a href="/studentregistrationspringjpa/regstudents"><button type="button">Ok</button></a>
		            </div>
                </form>
              </div> 
        </div>
   
</c:forEach> </div>
<footer class="bg-gray-800 text-white py-6">
    <div class="container mx-auto text-center">
        &copy; 2024 All rights reserved by ACE Inspiration.
    </div>
</footer>
<script>
     const popup = document.getElementById("popup");
        //var result=0;
        var result = ${result};
        const openPopup = ()=>{
          popup.classList.add("open-popup");
        }
        if(result === 1 ){
          openPopup();
        }
    </script>
</body>

</html>
