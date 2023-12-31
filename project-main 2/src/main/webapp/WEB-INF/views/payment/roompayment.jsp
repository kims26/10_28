<%@ page language='java' contentType='text/html;charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib prefix='c'    uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt'  uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='fn'  uri='http://java.sun.com/jsp/jstl/functions'%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>드로잉썸(Drawing SSum)</title>

    <!-- Favicon -->
    <link rel="icon" href="../../img/core-img/favicon.jpg">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="../../css/style.css">
    

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.16.22/dist/css/uikit.min.css" />
     <script src="https://cdn.jsdelivr.net/npm/uikit@3.16.22/dist/js/uikit.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/uikit@3.16.22/dist/js/uikit-icons.min.js"></script>
    
</head>

<body>
 
  <!-- 클릭 시 이미지 -->
<div class="preloader d-flex align-items-center justify-content-center">
  <div class="preloader-content">
      <h3>함께 그리는 데이트, Drawing SSum</h3>
      <div id="cooking">
          <div id="area">
              <div id="pancake">
                  <img class="./img/core-img/루피.jpg">
              </div>
          </div>
      </div>
  </div>
</div>


<!-- ##### Header Area Start ##### -->
<header class="header-area">

  <!-- Top Header Area/ sns 버튼 -->
  <div class="top-header-area bg-img bg-overlay" style="background-image: url(../../img/bg-img/header.jpg);">
      <div class="container h-100">
          <div class="row h-100 align-items-center justify-content-between">
              <div class="col-12 col-sm-6">
                  <!-- Top Social Info -->
                  <div class="top-social-info">
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Pinterest"><i
                              class="fa fa-pinterest" aria-hidden="true"></i></a>
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Facebook"><i
                              class="fa fa-facebook" aria-hidden="true"></i></a>
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i
                              class="fa fa-twitter" aria-hidden="true"></i></a>
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Dribbble"><i
                              class="fa fa-dribbble" aria-hidden="true"></i></a>
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Behance"><i
                              class="fa fa-behance" aria-hidden="true"></i></a>
                      <a href="#" data-toggle="tooltip" data-placement="bottom" title="Linkedin"><i
                              class="fa fa-linkedin" aria-hidden="true"></i></a>
                  </div>
              </div>
              <div class="col-12 col-sm-6 col-lg-5 col-xl-4">
                  <!-- 검색 -->
                  <div class="top-search-area">
                      <form action="#" method="post">
                          <input type="search" name="top-search" id="topSearch" placeholder="Search">
                          <button type="submit" class="btn"><i class="fa fa-search"></i></button>
                      </form>
                  </div>
              </div>
          </div>
      </div>
  </div>

  <!-- 로고 사진 -->
  <div class="logo-area">
      <a href="/main"><img src="../../img/core-img/DrawingSSum.png" alt="" style="width: 500px; height: 130px"></a>
  </div>

  <!-- Navbar Area -->
  <div class="bueno-main-menu" id="sticker">
      <div class="classy-nav-container breakpoint-off">
          <div class="container">
              <!-- Menu -->
              <nav class="classy-navbar justify-content-between" id="buenoNav">

                  <!-- 좌측 Toggler 버튼 -->
                  <div id="toggler" style="right: -1200px"><img src="../../img/core-img/toggler.png" alt=""></div>

                  <!-- Navbar Toggler -->
                  <div class="classy-navbar-toggler">
                      <span class="navbarToggler"><span></span><span></span><span></span></span>
                  </div>

                  <!-- Menu -->
                  <div class="classy-menu">

                      <div class="classycloseIcon">
                          <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                      </div>

                      <!-- 메뉴 -->
                      <div class="classynav">
                          <ul>
                              <li><a href="/main">Home</a></li>

                              <li><a href="#">코스 그리기</a></li>

                              <li><a href="#">국내</a>
                              </li>

                              <li><a href="#">해외</a>
                              </li>
                              <li><a href="../../single-post.html">피드</a></li>
                              <li><a href="#">고객센터</a>
                          </ul>

                          <!-- 로그인/회원가입 -->
                          <c:if test="${empty sessionScope.user}">
                              <!-- 로그인/회원가입 -->
                              <div class="login-area">
                                  <a href="/member/login">Login / Register</a>
                              </div>
                          </c:if>

                          <c:if test="${not empty sessionScope.user}">
                              <div class="login-area">
                                  <a href="/member/logout">Logout</a>
                              </div>
                          </c:if>
                      </div>
                      <!-- Nav End -->

                  </div>
              </nav>
          </div>
      </div>
  </div>
</header>
<!-- ##### Header Area End ##### -->

<!-- toggle 클릭시 마이페이지/관리자 창 -->
<div class="treading-post-area" id="treadingPost">
  <div class="close-icon">
      <i class="fa fa-times"></i>
  </div>

  <h4>My Page</h4>
  <hr>

  <br>
  <a href="#">내 피드</a>
  <br>
  <br>
  <br>
  <a href="#">내 예약</a><br>
  <br>
  <br>
  <a href="#">내 쿠폰</a><br>
  <br>
  <br>
  <a href="#">내 정보</a>

</div>


    <div class="container" style="margin-left:800px" >
        <h2>Accommodation Example</h2>  
        <div id="myCarousel" class="carousel slide" data-ride="carousel"  style="width:50%;" >
          <!-- Indicators -->
          <ol class="carousel-indicators">
            
            
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
          </ol>
      
          <!-- Wrapper for slides -->
          <div class="carousel-inner" >
            <c:forEach var="photo" items="${payment_info.roomvo.room_photo_list}">
            <div class="item active">
              <img src="../images/${photo.room_photo_name}" style="width:200;">
            </c:forEach>
            <div class="item">
            </div>
            </div>
      
          <!-- Left and right controls -->
          <a class="left carousel-control" href="#myCarousel" data-slide="prev" >
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" ></span>
            <span class="sr-only" >Next</span>
          </a>
        </div>
      </div>
   
     
      <br>
      <br>
      <br>


      <div id="payment">
        <form method="POST" action="../payment.do">
            
        <div id="box">
         <div ><h4 style= "text-align : left ">숙소정보</h4> </div>
              
        
      
        <table>

        <tr>
            <td bgcolor = gainsboro><span>객실이름</span><span class="star" style="margin-right: 70px">*</span>
            </td>
            <td><input type="text" name="room_name" id="room_name" value="${payment_info.roomvo.room_name}" class="input" style="width:400px" > 
               
            </td>
        </tr>

        <tr>
            <td width="150px"><span>호텔이름</span><span class="star" style="margin-right: 70px">*</span>
            </td>
            <td><input type="text" name="acc_name" id="acc_name" value=" ${payment_info.accvo.acc_name}" class="input" style="width:400px"> 
               
            </td>

        </tr>
        <tr>
            <td><span>기간</span><span class="star" style="margin-right: 56px">*</span></td>
            <td>
                <input type="text" id="room_check_in" name="room_check_in" value="${payment_info.roomvo.room_check_in}" class="input" style="width:400px" >
                
            </td>
        </tr>
        <tr>
          <td width="150px"><span>인원</span><span class="star" style="margin-right: 70px"></span>
          </td>
          <td><input type="text" name="room_people" id="room_people" value=" ${payment_info.roomvo.room_people}" class="input" style="width:400px"> 
          </td>
      </tr>
      </table>
      
      </div>

      <br>
      <br>
      <br>

      <div id="customer_info">
        <h  class="h1">
          <span style="margin-left: 800px">고객정보</span></h>
          <hr class="hr2">
        <!-- <form method="POST" action="${pageContext.request.contextPath}/payment" > -->
          <table width="1500" style="margin-left:800px">
            <input type="hidden" name="room_idx" value="${ payment_info.roomvo.room_idx}">
                                    <input type="hidden" name="acc_idx" value="${payment_info.accvo.acc_idx}">
                                    <input type="hidden" name="mem_idx" value="${payment_info.memvo.mem_idx}">
            console.log(${payment_info.roomvo.room_idx});
            console.log(${payment_info.memvo.mem_name});


          <tr>
              <td width="150px"><span>고객성함</span><span class="star" style="margin-right: 70px">*</span>
              </td>
              <td><input type="text" name="mem_name" id="mem_name" class="input" value="${payment_info.memvo.mem_name}" style="width:400px"> 
            </td>
  
          </tr>
          <tr>
            <td width="150px"><span>전화번호</span><span class="star" style="margin-right: 70px">*</span>
            </td>
            <td><input type="text" name="mem_phone" id="mem_phone" class="input" value="${payment_info.memvo.mem_phone}" style="width:400px"> 
            </td>

        </tr>
          <tr>
              <td><span>이메일</span><span class="star" style="margin-right: 56px">*</span></td>
              <td>
                  <input type="text" id="mem_email" name="mem_email" value="${payment_info.memvo.mem_email}" class="input"style="width:400px" >
              </td>  
          </tr>

        <input type="submit"  class="btn btn_lg btn_primary" value="카카오페이로 결제하기">
        </table>
          </form>
        </div>
        </div>
    

        <br>
        <br>
        <br>
<!-- 
        <h  class="h1">
          <span style="margin-left: 800px">kakaoPay api 이용하기</span></h>
          <hr class="hr2">       
            <form method="post" action="/kakaoPay" style="margin-left:800px">
                <button>카카오페이로 결제하기</button>
            </form> -->

 
            <footer class="footer-area">
              <hr>
              <div class="container">
                  <div class="row">
                      <div class="col-12 col-sm-5">
                          <!-- Copywrite Text -->
                          <p class="copywrite-text"><a href="#"/>
                              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                              Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                              All rights reserved | This site is made<br> <i class="fa fa-heart-o"
                                                                             aria-hidden="true"></i> by <a
                                      href="/main" target="_blank">Drawing SSum</a>
                              <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                          </p>
                      </div>
                      <div class="col-12 col-sm-7">
                          <!-- Footer Nav -->
                          <div class="footer-nav">
                              <ul>
                                  <li><a href="/main">Home</a></li>
                                  <li><a href="#">코스 그리기</a></li>
                                  <li><a href="#">국내</a></li>
                                  <li><a href="#">해외</a></li>
                                  <li><a href="../../single-post.html">피드</a></li>
                                  <li><a href="#">고객센터</a>
                              </ul>
                          </div>
                      </div>
                  </div>
              </div>
          </footer>
          <!-- ##### Footer Area Start ##### -->
          
          <!-- ##### All Javascript Script ##### -->
          <!-- jQuery-2.2.4 js -->
          <script src="../../js/jquery/jquery-2.2.4.min.js"></script>
          <!-- Popper js -->
          <script src="../../js/bootstrap/popper.min.js"></script>
          <!-- Bootstrap js -->
          <script src="../../js/bootstrap/bootstrap.min.js"></script>
          <!-- All Plugins js -->
          <script src="../../js/plugins/plugins.js"></script>
          <!-- Active js -->
          <script src="../../js/active.js"></script>
          


</body>
</html>



