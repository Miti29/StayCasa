<%if (session.getAttribute("User_email") == null) {
response.sendRedirect("/signin"); } else {%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<jsp:include page="includes/userNav.jsp" />

<!-- Page Content  -->
<div id="content">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <button type="button" id="sidebarCollapse" class="btn btn-info">
        <i class="fas fa-align-left"></i> 
        <span>Toggle Sidebar</span>
       </button>
      <div>
        <h3 class="text-info">BOOK YOUR EVENTS</h3>
      </div>
      <div>
        <p>
          Welcome <% if(session.getAttribute("User_gender").equals("male")){ %>
          Mr. <%}else{%> Miss. <%}%>
          <span class="font-weight-bold text-info"
            >${User_firstname} ${User_lastname}</span
          >
        </p>
      </div>
    </div>
  </nav>

  <div>
    <section class="container">
      <div class="container">
        <div class="row align-items-center my-5">
          <!-- Booking Form -->
          <div class="col-md-12">
            <form
              action="/makeBookingForm"
              modelAttribute="makeBookingForm"
              method="POST"
            >
              <!-- <input
                type="hidden"
                name="${_csrf.parameterName}"
                value="${_csrf.token}"
              /> -->
              <input type="hidden" name="user_id" value="${User_id}" />
              <input
                type="hidden"
                name="current_date"
                id="current_date"
                value=""
              />
              <div class="row">
                <!-- First Name -->

                <div class="col-lg-5 col-md-5 col-sm-12 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input
                      type="email"
                      class="form-control"
                      id="email"
                      name="email"
                      aria-describedby="emailHelp"
                      value="${User_email}"
                      readonly
                    />
                    <small id="emailHelp" class="form-text text-muted"
                      >Your email is safe with us.</small
                    >
                  </div>
                </div>

                <div class="col-lg-5 col-md-5 col-sm-12 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputPassword1">Phone Number</label>
                    <input
                      type="tel"
                      class="form-control"
                      id="contactno"
                      name="contactno"
                      placeholder="phoneNumber"
                      value="${User_phone}"
                      readonly
                    />
                  </div>
                </div>

                <div class="col-lg-5 col-md-5 col-sm-5 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputPassword1">Select Date</label>
                    <input
                      type="date"
                      id="event_date"
                      name="event_date"
                      class="form-control"
                      onchange="validatedate();"
                      required
                    />
                  </div>
                </div>

                <div class="col-lg-5 col-md-5 col-sm-5 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputPassword1"
                      >Select Starting Time</label
                    >
                    <input
                      type="time"
                      id="start_at"
                      name="start_at"
                      class="form-control"
                      required
                    />
                  </div>
                </div>

                <div class="col-lg-5 col-md-5 col-sm-12 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputPassword1">Select Hotel</label>
                    <select
                      id="hotel_book"
                      name="hotel_id"
                      class="form-control"
                      required
                    >
                      <option value="">Choose the Hotel</option>
                      <c:forEach var="hotelData" items="${hotel_for_booking}">
                        <option id="${hotelData.id}" value="${hotelData.id}">
                          ${hotelData.hotelName} - Per Hour
                          Dollar.${hotelData.price}
                        </option>
                      </c:forEach>
                    </select>
                  </div>
                </div>

                <div class="col-lg-5 col-md-5 col-sm-5 mb-2 ml-3">
                  <div class="form-group">
                    <label for="exampleInputPassword1"
                      >Total Number of Guests / People</label
                    >
                    <input
                      type="number"
                      id="no_of_guest"
                      name="no_of_guest"
                      class="form-control"
                      pattern="[0-9]{3}"
                      required
                    />
                  </div>
                </div>

                <!-- Submit Button -->
                <div class="form-group col-lg-12 mx-auto mb-0">
                  <button
                    type="submit"
                    class="btn btn-info py-2"
                    name="adduser"
                  >
                    <span class="font-weight-bold btn-block"
                      >Book your Event</span
                    >
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
  var today = new Date();
  var dd = String(today.getDate()).padStart(2, "0");
  var mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
  var yyyy = today.getFullYear();

  today = dd + "/" + mm + "/" + yyyy;
  document.getElementById("current_date").value = today;

  $("#hotel_book").change(testMessage);
  $("#no_of_guest").on("input", textMessage4);

  var hotel_amt = 0,
    no_of_guest = 1,
    no_of_hours = 1;

  function testMessage() {
    var id = $(this).children(":selected").attr("id");
    console.log(id);
    var hotel_name = "";
    $.ajax({
      type: "GET",
      url: "${pageContext.request.contextPath}/hotelbookfind/" + id,
      data: "input=" + $("#ip1").val() + "&output=" + $("#op1").val(),
      success: function (hotel) {
        hotel_amt = hotel.price;
        hotel_name = hotel.hotelName;
        $("#hotel_price").text(hotel_amt);
      },
    });

    var e_date = $("#event_date").val();
    $.ajax({
      type: "GET",
      url: "${pageContext.request.contextPath}/hotelbookingfind/" + id,
      data: "input=" + $("#ip15").val() + "&output=" + $("#op15").val(),
      success: function (book) {
        console.log(e_date);

        for (let i = 0; i < book.length; i++) {
          console.log(book[i].event_date);
          if (e_date == book[i].event_date) {
            swal(
              "Alert!",
              "The Hotel " +
                hotel_name +
                " is already booked at the date " +
                e_date +
                " Please choose other hotel !",
              "error"
            );
            $("#hotel_book").val("");
          }
        }
      },
    });
  }

  function textMessage4() {
    no_of_guest = $("#no_of_guest").val();
    $("#guest").text(" X " + no_of_guest);
  }

  function validatedate() {
    today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //As January is 0.
    var yy = today.getFullYear();

    var e = document.getElementById("event_date");

    var dateformat = e.value.split("-");
    var cin = dateformat[2];
    var cinmonth = dateformat[1];
    var cinyear = dateformat[0];
    if (yy == cinyear && mm == cinmonth && dd <= cin) {
      return true;
    } else if (yy < cinyear) {
      return true;
    } else if (mm < cinmonth && yy <= cinyear) {
      return true;
    } else {
      alert("Please select valid appointment date from today");
      e.value = "";
    }
  }
</script>
<jsp:include page="includes/footer.jsp" />

<%}%>
