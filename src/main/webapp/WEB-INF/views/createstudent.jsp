<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.io.File"%>
<%@ page import="java.io.InputStream"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Multi-Step Form</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style type="text/css">
    .error{
    margin-left:0px;
    margin-bottom:20px;
    color:red;
    }
    </style>
</head>

<body class="flex items-center justify-center min-h-screen bg-slate-900">
    <!-- Multi-step form container -->
    <div class="bg-white shadow-lg rounded-lg p-6 max-w-lg w-full mx-4">
        <!-- Custom branding section -->
        <div class="text-center mb-6">
            <div class="text-3xl text-blue-950 mb-2">ACE Inspiration</div>
            <p class="text-gray-600">Join us on your learning journey!</p>
        </div>

        <!-- Multi-step form -->
		<form:form  id="multistepsform" action="addstudent" method="post" modelAttribute="studentinform" enctype="multipart/form-data">
            <!-- Fieldsets -->
            <fieldset class="form-step mt-3">
                <h2 class="text-xl mb-4">Create Your Account</h2>
                <h3 class="text-lg mb-2"></h3>
                <div class="error">${emailerror}</div>
                <input type="hidden" name="course_id" value="${courseid}">
                <input type="text" name="name" placeholder="Enter Your Name" value="${studentdata.name}" required class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <input type="email" name="email" placeholder="Enter Your Email" value="${studentdata.email}" required class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <input type="password" name="password"  placeholder="Password" value="${studentdata.password}" class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <button type="button" name="next" class="w-full py-2 bg-blue-950 text-white rounded hover:bg-blue-800 transition">Next</button>
            </fieldset>
            
            <fieldset class="form-step hidden">
                <h2 class="text-xl font-semibold mb-4">Personal Details</h2>
                <h5 class="text-sm text-gray-600">Your information is kept confidential.</h5>
                <input type="text" name="address"  placeholder="Enter Your Address" value="${studentdata.address}"class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <input type="text" name="phno"  placeholder="Enter Your Phone Number" value="${studentdata.phno}" class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <textarea name="education"  placeholder="Enter Your Education" class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">${studentdata.education}</textarea>
                <input type="file" name="photo" required class="mb-4 p-2 border border-gray-300 rounded w-full" accept="image/*">
                <label class="mr-4">Date Of Birth</label>
                <input type="date" name="dob" value="${studentdata.dob}" class="mb-4 p-2 border border-gray-300 rounded w-full focus:outline-none focus:ring focus:ring-blue-500">
                <div class="mb-4">
                    <label class="mr-4">Gender:</label>
                    <div class="inline-block">
                        <input type="radio" name="gender" id="male" value="Male" 
                        ${empty studentdata.gender ? '' : (studentdata.gender eq 'Male' ? 'checked' : '')}
                        class="mr-2"><label for="male" class="mr-4">Male</label>
                        <input type="radio" name="gender" id="female" value="Female"
                        ${empty studentdata.gender ? '' : (studentdata.gender eq 'Female' ? 'checked' : '')}> 
                        <label for="female">Female</label>
                    </div>
                </div>
                <div class="flex justify-between">
                    <button type="button" name="previous" class="w-24 py-2 bg-gray-400 rounded hover:bg-gray-500 transition">Previous</button>
                    <button type="button" name="next" class="w-24 py-2 bg-blue-950 text-white rounded hover:bg-blue-800 transition">Next</button>
                </div>
            </fieldset>
            
            <fieldset class="form-step hidden">
                <!-- Voucher Section -->
                <div id="voucher" class="voucher bg-white p-6 rounded-lg shadow-lg max-w-lg mx-auto">
                    <!-- Voucher Header -->
                    <div class="flex justify-between items-center">
                        <div class="text-xl font-bold text-gray-800">Payment Voucher</div>
                        <div class="w-16 h-16">
                            <!-- Replace with your logo image -->
                            <!-- <img src="your-logo-url.png" alt="ACE Inspiration" class="w-full h-full object-cover"> -->
                        </div>
                    </div>

                   <div class="mt-4 text-gray-700 flex flex-col items-start">
    <p><strong>Class :      </strong> ${coursedata.name }</p>
    <p><strong>Amount :</strong> ${coursedata.courseFees }0 MMK</p>
    <p><strong>Account Name :</strong> Aung Kyaw Nyein</p>
    <p><strong>KBZPay Account :</strong> 09260092902</p>
</div>
                   
                    <!-- Payment Confirmation -->
                    <div class="mt-4">
                        <h5 class="text-sm mb-2">Send Transaction Screenshot:</h5>
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

                <!-- JavaScript Section -->
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.0/jspdf.umd.min.js"></script>
              <script>
    document.getElementById('downloadVoucher').addEventListener('click', function() {
        // Create a PDF instance
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();
        
        // Get the voucher content
        const voucherElement = document.getElementById('voucher');
        
        // Add the voucher content to the PDF
        doc.text('Payment Voucher', 10, 10);
        
        // Add the content of the voucher to the PDF
        const details = voucherElement.querySelectorAll('p');
        let y = 20;
        details.forEach(detail => {
            doc.text(detail.textContent, 10, y);
            y += 10; // Adjust the line height
        });

        // Add the image to the PDF
        const payphotoInput = document.querySelector('input[name="payphoto"]');
        const payphotoFile = payphotoInput.files[0];
        if (payphotoFile) {
            const img = new Image();
            img.onload = function() {
                const imgWidth = 100; // Adjust the image width as needed
                const imgHeight = img.height * imgWidth / img.width;
                doc.addImage(img, 'JPEG', 10, y + 10, imgWidth, imgHeight);
                doc.save('invoice.pdf');
            };
            img.src = URL.createObjectURL(payphotoFile);
        } else {
            doc.save('invoice.pdf');
        }
    });
</script>
              
            </fieldset>
            
            <!-- Circular step indicators -->
            <div class="progress-container mt-6">
                <div class="flex justify-center items-center space-x-4">
                    <!-- Step indicators -->
                    <div class="step-indicator w-4 h-4 flex items-center justify-center rounded-full bg-gray-500 cursor-pointer" data-step="1"></div>
                    <div class="step-indicator w-4 h-4 flex items-center justify-center rounded-full bg-gray-500 cursor-pointer" data-step="2"></div>
                    <div class="step-indicator w-4 h-4 flex items-center justify-center rounded-full bg-gray-500 cursor-pointer" data-step="3"></div>
                </div>
            </div>
        </form:form>
    </div>

    <!-- JavaScript for multi-step form -->
    <script>
        const form = document.getElementById("multistepsform");
        const fieldsets = form.querySelectorAll(".form-step");
        const stepIndicators = document.querySelectorAll('.step-indicator');
        let currentStep = 0;

        // Function to show the next fieldset
        function showNext() {
            fieldsets[currentStep].classList.add("hidden");
            currentStep++;
            fieldsets[currentStep].classList.remove("hidden");
            updateActiveStep();
        }

        // Function to show the previous fieldset
        function showPrevious() {
            fieldsets[currentStep].classList.add("hidden");
            currentStep--;
            fieldsets[currentStep].classList.remove("hidden");
            updateActiveStep();
        }

        // Function to update the active step indicator
        function updateActiveStep() {
            stepIndicators.forEach((indicator, index) => {
                if (index === currentStep) {
                    // Active step indicator
                    indicator.classList.remove('bg-gray-500');
                    indicator.classList.add('bg-blue-950');
                } else {
                    // Inactive step indicator
                    indicator.classList.remove('bg-blue-950');
                    indicator.classList.add('bg-gray-500');
                }
            });
        }

        // Add event listeners for the buttons
        form.addEventListener("click", (e) => {
            const target = e.target;
            if (target.name === "next") {
                showNext();
            } else if (target.name === "previous") {
                showPrevious();
            }
        });

        // Initial setup for the step indicators
        updateActiveStep();
    </script>
</body>

</html>
