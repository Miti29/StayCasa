<%if (session.getAttribute("Admin_email") == null) {response.sendRedirect("/signin"); } else {%> 
<jsp:include page="includes/header.jsp" />  
	
	<jsp:include page="includes/adminNav.jsp" />  
	
	<style>
    .card:hover {
      transform: scale(1.05);
      box-shadow: 0 10px 20px rgba(0, 0, 0, .12), 0 4px 8px rgba(0, 0, 0, .06);
      transition: 0.3s ease-in-out;
      cursor:pointer;
  }
    </style>
	    <!-- Page Content  -->
        <div id="content">

             <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span>Toggle Sidebar</span>
                    </button>
                   <div>
                    	<h3 class="text-info">ADMIN DASHBOARD</h3>
                    </div>
                   <div>
                    	<p>Welcome 
                    	<% if(session.getAttribute("Admin_gender").equals("male")){ %> 
                    		Mr.
                    	<%}else{%> 
                    		Miss.
                    	<%}%> 
                    	<span class="font-weight-bold text-info">${Admin_firstname} ${Admin_lastname}</span></p>
                    </div>
                </div>
            </nav>

<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap-extended.min.css">
<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/fonts/simple-line-icons/style.min.css">
<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/colors.min.css">


<div class="grey-bg container-fluid">
  <section id="minimal-statistics">
    <div class="row">
      <div class="col-12 mt-3 mb-1">
        <h4 class="text-uppercase">OVERVIEW</h4>
      </div>
    </div>

    <div class="table-responsive">
      <table class="content-table table" id="table-id">
          <thead>
              <tr>
              <th>Total Number of Users</th>
              <th>Total Number of Casa</th>
              <th>Total Number of Bookings</th>
              <th>Bookings cancelled by Admin</th>
              <th>Bookings cancelled by Users</th>
          </tr>
      </thead>
           <tbody>
              
                  <tr>
                      <td>${admin_user_count}</td>
                      <td>${admin_hotel_count}</td>
                      <td>${admin_booking_count}</td>
                      <td>${admin_bookingcancelbyadmin_count}</td>
                      <td>${admin_bookingcancelbyuser_count}</td>
                 </tr>
              
           </tbody>  
          </table> 
      </div>
  </div>      



    </div>
    </div>
</div>






















    <div class="row">
      <div class="col-xl-3 col-sm-6 col-12"> 
        <!-- <div class="card">
          <div class="card-content">
            <div class="card-body"> -->
              <div class="media d-flex">
                <!-- <div class="align-self-center">
                  <i class="fas fa-users primary fa-3x"></i>
                </div> -->
                <div class="media-body text-right">
                  <h3 class="success">${admin_user_count}</h3>
                  <span>Total Users</span>
                <!-- </div>
              </div>
            </div> -->
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-sm-6 col-12">
        <!-- <div class="card">
          <div class="card-content">
            <div class="card-body"> -->
              <div class="media d-flex">
                <!-- <div class="align-self-center">
                  <i class="fas fa-concierge-bell warning fa-3x"></i>
                </div> -->
                <div class="media-body text-right">
                  <h3 class="danger">${admin_hotel_count}</h3>
                  <span>Total Hotels</span>
                <!-- </div>
              </div>
            </div> -->
          </div>
        </div>
      </div>

      <div class="col-xl-3 col-sm-6 col-12">
        <!-- <div class="card">
          <div class="card-content">
            <div class="card-body"> -->
              <div class="media d-flex">
                <div class="media-body text-left">
                  <h3 class="success">${admin_booking_count}</h3>
                  <span>Total Booking</span>
                </div>
                <!-- <div class="align-self-center">
                  <i class="fas fa-calendar-check info fa-3x"></i>
                </div> -->
              <!-- </div>
            </div>
          </div> -->
        </div>
      </div>
  
     
    </div>


    <div class="row">
    

    
    
    <div class="col-xl-3 col-sm-6 col-12">
      <!-- <div class="card"> -->
        <!-- <div class="card-content"> -->
          <!-- <div class="card-body"> -->
            <div class="media d-flex">
              <div class="media-body text-left">
                <h3 class="warning">${admin_bookingcancelbyadmin_count}</h3>
                <span>Bookings Cancelled by Admin</span>
              </div>
              <!-- <div class="align-self-center">
                <i class="fal fa-calendar-check warning fa-3x"></i>
              </div> -->
            </div>
          <!-- </div> -->
        <!-- </div> -->
      <!-- </div> -->
    </div>

    <div class="col-xl-3 col-sm-6 col-12">
      <!-- <div class="card"> -->
        <!-- <div class="card-content"> -->
          <!-- <div class="card-body"> -->
            <div class="media d-flex">
              <div class="media-body text-left">
                <h3 class="danger">${admin_bookingcancelbyuser_count}</h3>
                <span>Bookings Cancelled by User</span>
              </div>
              <!-- <div class="align-self-center">
                <i class="fad fa-calendar-check danger fa-3x"></i>
              </div> -->
            <!-- </div>
          </div> -->
        <!-- </div> -->
      </div>
    </div>




    

  </div>


  </div>

  </div>
</section>
</div>  
 

   
  </div>
</div>
</div>
</div>
	
	

<jsp:include page="includes/footer.jsp" />  
<%}%>