<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACE Inspiration</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

</head>

<body class="flex flex-col min-h-screen bg-gray-100">

    <!-- Header Section -->
     <%@ include file="header.jsp" %>
     
    <!-- Main Content Section -->
    <main class="flex-grow mt-2 mb-3">
        <!-- Pricing Section -->
        <div id="fh5co-pricing-section" class="py-8">
            <div class="container mx-auto">

                <!-- Cards -->
                <!-- Cards -->
                <div class="grid grid-cols-3 gap-6">
                    <!-- Card 1: Address -->
                    <div class="card bg-white rounded-lg ml-3 shadow-md p-4">
                        <h6 class="text-lg font-bold mb-2 flex items-center">
                            <i class="fas fa-map-marker-alt mr-2"></i> Address
                        </h6>
                        <p>No.169, MTP Condo, 169 Insein Rd,<br>Hlaing Township, Yangon, Myanmar</p>
                    </div>
                    <!-- Card 2: Phone -->
                    <div class="card bg-white rounded-lg shadow-md p-4">
                        <h6 class="text-lg font-bold mb-2 flex items-center">
                            <i class="fas fa-phone mr-2"></i> Phone
                        </h6>
                        <p>95-1-652239, 95-1-652270, 95-1-2305108,<br>95-1-2305109, 95-1-2305110</p>
                    </div>
                    <!-- Card 3: Hours & Phone -->
                    <div class="card bg-white rounded-lg mr-3shadow-md p-4">
                        <h6 class="text-lg font-bold mb-2 flex items-center">
                            <i class="fas fa-clock mr-2"></i> Hours & Phone
                        </h6>
                        <p>Hours: 9 AM - 6 PM daily</p>
                        <p>Phone: 09 253 257 742</p>
                    </div>
                </div>

            </div>

            <!-- Maps Section -->
            <div id="fh5co-us-section">
                <div class="container-new mb-5 mt-7 ">
                    <div class="row">
                        <div class="col-md-8 heightLine_01 p-0 mx-auto">
                            <iframe
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3818.535928080833!2d96.1254676148184!3d16.845903638394023!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x30c194e9ed66de9d%3A0xaba8dc0947f34754!2sNo.%20169%2C%20MTP%20Condo%2C%20169%20Insein%20Rd%2C%20Yangon%2011051!5e0!3m2!1sen!2smm!4v1685031994953!5m2!1sen!2smm"
                                width="60%" height="300" style="border:0;" allowfullscreen="" loading="lazy"
                                class="mx-auto">
                            </iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
    </main>
    <!-- Footer Section -->
    <footer class="bg-gray-800 text-white py-6">
        <div class="container mx-auto text-center">
            &copy; 2024 All rights reserved by ACE Inspiration.
        </div>
    </footer>

</body>

</html>