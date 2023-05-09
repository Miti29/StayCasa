<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Back</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarCollapse"
      aria-controls="navbarCollapse"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
  </div>
</nav>

<section class="container">
  <div class="container">
    <div class="row align-items-center my-5">
      <!-- For Demo Purpose -->
      <div class="col-md-12 col-lg-6 col-sm-12 mb-5">
        <img src="assets/picture/icons/thumbs-up.png" alt="" class="img-fluid mb-3 d-none d-md-block">
        <h1>Create an Account</h1>
        <p class="font-italic text-muted mb-0">
          Fill out the Information provided here to sign in
        </p>
        <div c:if="${reg_error != null}">
          <p class="text-danger">${reg_error}</p>
        </div>
      </div>

      <!-- Registeration Form -->
      <div class="col-md-12 col-lg-6 col-sm-12 mt-3">
        <form
          action="/registerForm"
          modelAttribute="registerForm"
          method="POST"
        >
          <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
          <input type="hidden" name="role" value="User" />
          <div class="row">
            <!-- First Name -->
            <div>FirstName</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
              </div>
              <input
                id="firstName"
                type="text"
                name="FirstName"
                
                class="form-control bg-white border-left-0 border-md"
                required
              />
            </div>

            <!-- Last Name -->
            <div>LastName</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
              </div>
              <input
                id="lastName"
                type="text"
                name="LastName"
                class="form-control bg-white border-left-0 border-md"
                required
              />
            </div>


            <!-- Email Address -->
            <div>Enter Email Address</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
   
              </div>
              
              <input
                id="email"
                type="email"
                name="email"
                 
                class="form-control bg-white border-left-0 border-md"
                required
              />
            </div>

            <!-- Phone Number -->
            <div>Phone Number</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">

              </div>
              
              <input
                id="phoneNumber"
                type="tel"
                name="contactno"
                pattern="[6789][0-9]{9}"
                
              
                class="form-control bg-white border-md border-left-0 pl-3"
                required
              />
            </div>

            <!-- Address -->
            <div>Address</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">

              </div>
              
              <textarea
                class="form-control"
                name="Address"
                id="floatingTextarea"
              ></textarea>
            </div>

            <!-- Gender -->
            <div>Gender</div>
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
              </div>
              
              <select
                id="job"
                name="gender"
                class="form-control custom-select bg-white border-left-0 border-md"
                required
              >
                <option value="">Choose your Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
              </select>
            </div>

            <!-- Password -->
            <div>Password</div>
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">

              </div>
              <input
                id="password"
                type="password"
                name="password"
                oninput="checkInputs();"
                
                class="form-control bg-white border-left-0 border-md"
                required
              />
            </div>

            <!-- Password Confirmation -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">

                  
                </span>
              </div>
              <div>Confirm Password</div>
              <input
                id="confirm_password"
                type="password"
                oninput="checkInputs();"
                name="ConfirmPassword"
               
                class="form-control bg-white border-left-0 border-md"
                required
              />
            </div>

            <!-- Submit Button -->
            <div class="form-group col-lg-12 mx-auto mb-0">
              <button
                type="submit"
                class="btn btn-primary btn-block py-2"
                name="adduser"
              >
                <span>Create your account</span>
              </button>
            </div>

            <!-- Already Registered -->
            <!-- <div class="text-center w-100">
              <p class="text-muted font-weight-bold">
                Already Registered?
                <a href="signin">Login</a>
              </p>
            </div> -->
          </div>
        </form>
      </div>
    </div>
  </div>
</section>
<script>
  var password = document.getElementById("password"),
    confirm_password = document.getElementById("confirm_password");

  function checkInputs() {
  		console.log(password.value.length);
		    if ( password.value.length < 5) {
		      confirm_password.setCustomValidity("Passwords must be greater than 5");
		    } else {
		    	if (password.value != confirm_password.value) {
		    		console.log(password.value.length);
				      confirm_password.setCustomValidity("Passwords dont match");
				    } else {
				      confirm_password.setCustomValidity("");
				    }
		    }	    
  }

  password.onchange = validatePassword;
  confirm_password.onkeyup = validatePassword;
</script>

<jsp:include page="includes/footer.jsp" />
