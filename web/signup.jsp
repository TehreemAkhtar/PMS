<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="DTO.jDBCConnection"%>
<%@page import="DTO.User"%>
<jsp:include page="resources/head.jsp" />
<jsp:include page="resources/navbar.jsp" />
<jsp:include page="auth.jsp" />


<%
    String error = "";

    if (request.getParameter("uname") != null) {
        User user = new User();
        String password1, password2, email, phoneNo;
        email = request.getParameter("email");
        phoneNo = request.getParameter("phoneNO");
        if (User.isUserExistInDB(email, phoneNo)) {
            error = "User already exists";
        } else {
            password1 = request.getParameter("password1");
            password2 = request.getParameter("password2");
            if (password1.compareTo(password2)==0) {
                    user.setPassword(password1);
                    user.setName(request.getParameter("name"));
                    user.setPhone(request.getParameter("phoneNo"));
                    user.setAddress(request.getParameter("address"));
                    user.setEmail(request.getParameter("email"));
                    user.setAccountType(2);
                    User usr1=User.createUser(user);
                    if(usr1==null){
                        error="Error Occurred";
                    }
                    else{
                         HttpSession  httpSession = request.getSession();
                         httpSession.setAttribute("user", usr1);
                         response.sendRedirect("index.jsp");
                    }
            } else {
                error = "Those passwords did not match.Try again.";
                
            }

           
            
        }

        //PreparedStatement pst= conn.prepareStatement("insert into User()");
    }
%>


<section class="ftco-section contact-section">
    <div class="container mt-5">
        <div class="row block-9">

            <div class="col-md-1"></div>
            <div class="col-md-6 ftco-animate">
                <form action="#" class="contact-form">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Username" name="uname" required="">
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="E-Mail" name="email" required="">
                            </div>
                        </div></div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="Password" class="form-control" placeholder="Password" name="password1" minlength="5" required="" ng-model="activeItem.passwordString" >
                            </div>
                        </div></div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="Password" class="form-control" placeholder="Retype Password" name="password2" minlength="5" required="" ng-model="activeItem.passwordConfirm">
                            </div>
                            <p ng-show="(activeItem.passwordString && activeItem.passwordConfirm) && activeItem.passwordString
                                            !== activeItem.passwordConfirm"><%=error%></p>
                        </div></div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="tel" class="form-control" placeholder="Phone Number" name="phoneNo" required="">
                            </div>
                        </div></div>


                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Address" name="address" required="">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Signup" class="btn btn-primary py-3 px-5">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<jsp:include page="resources/footer.jsp" />
