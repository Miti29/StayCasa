<jsp:include page="includes/header.jsp" />

<jsp:include page="includes/homeNav.jsp" />
<style>
  .carousel-item {
    height: 100vh;
    min-height: 300px;
    background: no-repeat center center scroll;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  }
</style>

<header style="margin-top: -40px">
  <div
    id="carouselExampleIndicators"
    class="carousel slide"
    data-ride="carousel"
  >
    <ol class="carousel-indicators">
      <li
        data-target="#carouselExampleIndicators"
        data-slide-to="0"
        class="active"
      ></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <!-- Slide One - Set the background image for this slide in the line below -->
      <div
        class="carousel-item active"
        style="background-image: url('../assets/images/hotels/img4.jpeg')"
      >
        <div class="carousel-caption d-none d-md-block">
         
        </div>
      </div>
      <!-- Slide Two - Set the background image for this slide in the line below -->
      <div
        class="carousel-item"
        style="background-image: url('../assets/images/hotels/img5.jpeg')"
      >
        <div class="carousel-caption d-none d-md-block">
          
        </div>
      </div>
      <!-- Slide Three - Set the background image for this slide in the line below -->
      <div
        class="carousel-item"
        style="background-image: url('../assets/images/hotels/img6.jpeg')"
      >
        <div class="carousel-caption d-none d-md-block">
         
        </div>
      </div>
    </div>
    <a
      class="carousel-control-prev"
      href="#carouselExampleIndicators"
      role="button"
      data-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a
      class="carousel-control-next"
      href="#carouselExampleIndicators"
      role="button"
      data-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</header>

<!-- footer-section -->
<section class="footer-section">
  <footer class="footer">
    <p class="footer_title">Contact us</p>
    <div class="footer_social">
      <a href="https://www.linkedin.com/in/miti-shah-6b51ba1a0/" class="footer_icon"
        ><i class="fab fa-facebook-f"></i
      ></a>
      <a href="https://www.instagram.com/miti_shah___/" class="footer_icon"
        ><i class="fab fa-instagram"></i
      ></a>
      <a href="https://www.linkedin.com/in/miti-shah-6b51ba1a0/" class="footer_icon"
        ><i class="fab fa-twitter"></i
      ></a>
    </div>
    <p>&#169; 2023 copyright</p>
  </footer>
</section>
<jsp:include page="includes/footer.jsp" />
