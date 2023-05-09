<jsp:include page="includes/header.jsp" />
<nav
  class="navbar navbar-expand-md navbar-dark bg-dark"
  style="margin-bottom: 20px"
>
<div class="container-fluid">
  <a class="navbar-brand" href="/"> Back</a>
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

<section class="mt-5">
  <div class="container">
    <div class="row align-items-center my-5">
      <!-- For Demo Purpose -->


      <!-- Registeration Form -->
      <div class="col-lg-6 col-md-12 col-sm-12 mt-5">
        <div class="login-form">
          <form
            action="/login-validation"
            modelAttribute="loginForm"
            method="POST"
          >
            <h2 class="text-center mb-4">Sign in</h2>
            <div class="form-group mt-3">
              <div class="input-group">
                <div class="input-group-prepend">
                </div>
                <input
                  type="text"
                  class="form-control"
                  name="email"
                  placeholder="Email"
                  required="required"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <div class="input-group-prepend">
                </div>
                <input
                  type="password"
                  class="form-control"
                  name="password"
                  placeholder="Password"
                  required="required"
                />
              </div>
            </div>

            <div class="form-group">
              <button
                type="submit"
                name="user_login"
                class="btn btn-primary login-btn btn-block"
              >
                Sign in
              </button>
            </div>
          </form>

          <!-- <div class="text-center w-100">
            <p class="text-muted font-weight-bold">
              Forgot Password
              <a href="/forgot_password" class="text-primary ml-2"
                >Click here!</a
              >
            </p>
          </div> -->

          <!-- not Already Registered -->
          <div class="text-center w-100">
            <p class="text-muted font-weight-bold">
              Don't have an account?
              <a href="signup" class="text-primary ml-2">Sign up here!</a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<jsp:include page="includes/footer.jsp" />
