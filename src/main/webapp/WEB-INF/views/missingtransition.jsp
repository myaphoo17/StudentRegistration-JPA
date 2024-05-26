<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
	<!-- Voucher Section -->
				<form:form  id="multistepsform" action="wrogtransition" method="post" modelAttribute="studentinform" enctype="multipart/form-data">
                <div id="voucher" class="voucher bg-white p-6 rounded-lg shadow-lg max-w-lg mx-auto">
                    <!-- Voucher Header -->
                    <div class="flex justify-between items-center">
                        <div class="text-xl font-bold text-gray-800">Payment Voucher</div>
                        <div class="w-16 h-16">
                            <!-- Replace with your logo image -->
                            <!-- <img src="your-logo-url.png" alt="ACE Inspiration" class="w-full h-full object-cover"> -->
                        </div>
                    </div>

                    <!-- Voucher Details -->
                    <div class="mt-4 text-gray-700">
                    	<p><input type="hidden" name="studentid" value="${stuid}"></p>
                        <p><strong>Class:</strong> ${coursedata.name }</p>
                        <p><strong>Amount:</strong> ${coursedata.courseFees }</p>
                        <p><strong>Account Name:</strong> Aung Kyaw Nyein</p>
                        <p><strong>KPay Phone Number:</strong> 09260092902</p>
                    </div>

                    <!-- Payment Confirmation -->
                    <div class="mt-4">
                        <h5 class="text-lg mb-2">Send Transaction Screenshot:</h5>
                        <input type="file" name="payphoto" required class="p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500" accept="image/*">
                    </div>

                    <!-- Voucher Footer -->
                    <div class="flex justify-between mt-4">
                        <button type="button" name="previous" class="w-24 py-2 bg-gray-400 text-white rounded hover:bg-gray-500 transition">Previous</button>
                        <button type="submit" name="submit" class="w-24 py-2 bg-blue-950 text-white rounded hover:bg-blue-800 transition">Submit</button>
                    </div>

                    <!-- Download Button -->
                    <div class="mt-4">
                        <button id="downloadVoucher" class="py-2 px-4 bg-blue-950 text-white rounded hover:bg-blue-800 transition">Download Voucher</button>
                    </div>
                </div>
                </form:form>
</body>
</html>