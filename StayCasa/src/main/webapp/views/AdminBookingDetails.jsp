<%if (session.getAttribute("Admin_email") == null) {response.sendRedirect("/signin"); } else {%> 

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <jsp:include page="includes/header.jsp" />  
        
        <jsp:include page="includes/adminNav.jsp" />  
        
        
            <!-- Page Content  -->
            <div id="content">
    
                  <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
    
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>Toggle Sidebar</span>
                        </button>
                        <div>
                            <h3 class="text-info">ADMIN BOOKINGDETAILS</h3>
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

<br /><br/>
        
          
                  <div class="table-responsive">
                      <table class="content-table table" id="table-id">
                          <thead>
                              <tr>
                              <th>Username</th>
                              <th>UserEmail</th>
                              <th>Casa_Name</th>
                              <th>Start_Date</th>
                              <th>Start_time</th>
                              <th>Guests</th>
                              <th>Status</th>
                              <th>Action</th>
                          </tr>
                      </thead>
                           <tbody>
                               <c:forEach var="allbooking" items="${admin_booking}" >
                                  <tr>
                                      <td>${allbooking.user.firstName} ${allbooking.user.lastName}</td>
                                      <td>${allbooking.user.email}</td>
                                      <td>${allbooking.hotel.hotelName}</td>
                                      <td>${allbooking.event_date}</td>
                                      
                                      <td>${allbooking.start_at}</td>
                                      <td>${allbooking.no_of_guest}</td>
                                      <td class="d-flex">
                                        <c:if test = "${allbooking.accept_status==0    }">
                                            <span class="text-muted">Pending</span>
                                        </c:if>
                                        <c:if test = "${allbooking.accept_status==1    }">
                                            <span class="text-muted">Booking Accepted</span>
                                        </c:if>
                                        <c:if test = "${allbooking.accept_status==2    }">
                                            <span class="text-muted">Cancelled by you</span>
                                        </c:if>
                                        <c:if test = "${allbooking.accept_status==3    }">
                                            <span class="text-muted">Cancelled by user</span>
                                        </c:if>
                                      </td>
                                      <td>
                                        <c:if test = "${allbooking.accept_status==0    }">
                                           <div class="d-flex">

                                            <form action="/bookacceptbyadmin" method="POST">
                                                <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
                                                <input type="hidden" value="${allbooking.id}" name="booking_id">
                                                <!-- <button type="submit" class="btn btn-success  ml-3">Accept</button> -->
                                                <button type="submit" class="btn btn-success ml-3">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                        <path d="M12.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 0 1 .708-.708L5.5 10.793l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                                                    </svg>
                                                </button>
                                                
                                                
                                            </form>
                                            <form action="/bookcancelbyadmin" method="POST">
                                                <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
                                                <input type="hidden" value="${allbooking.id}" name="booking_id">
                                                <!-- <button type="submit" class="btn btn-danger ml-3" onclick='return cancelBooking()'>Cancel</button> -->
                                                <button type="submit" class="btn btn-danger ml-3" onclick='return cancelBooking()'>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                                        <path fill-rule="evenodd" d="M8 7.293l3.646-3.647a.5.5 0 01.708.708L8.707 8l3.647 3.646a.5.5 0 01-.708.708L8 8.707l-3.646 3.647a.5.5 0 01-.708-.708L7.293 8 3.646 4.354a.5.5 0 01.708-.708L8 7.293z"/>
                                                    </svg>
                                                </button>
                                                
                                            </form>
                                           </div>
                                        </c:if>
                                    </td>
                                  </tr>
                              </c:forEach>
                           </tbody>  
                          </table> 
                      </div>
                  </div>      
          
    
        
                    </div>
                    </div>
                </div>
                
    
        
                
        
    
    
        <jsp:include page="includes/footer.jsp" />  
        <%}%>
        