<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE> ${pageName} </TITLE>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/drawingboard.min.css">
    <link rel="stylesheet" href="css/imagin.css">
    <script type="text/javascript" src="js/drawing.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/drawingboard.js"></script>
    <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Open Sans", sans-serif}
    </style>
</HEAD>
<BODY class="w3-theme-l5">
    <!-- Navbar -->
    
     <div class="w3-bar w3-theme-d2 w3-left-align w3-large">
      <a href="#" class="w3-bar-item w3-button w3-padding-large w3-theme-d4"><i class="fa fa-home w3-margin-right"></i>Logo</a>
      <a href="#" class="w3-bar-item w3-button  w3-padding-large w3-hover-white" title="News"><i class="fa fa-globe"></i></a>
      <a href="getFriendsList.htm" class="w3-bar-item w3-button  w3-padding-large w3-hover-white" title="Friends"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button  w3-padding-large w3-hover-white" title="Messages"><i class="fa fa-envelope"></i></a>
      <div class="w3-dropdown-hover ">
        <button class="w3-button w3-padding-large" title="Notifications"><i class="fa fa-bell"></i><span class="w3-badge w3-right w3-small w3-green">3</span></button>     
        <div class="w3-dropdown-content w3-card-4 w3-bar-block" style="width:300px">
          <a href="#" class="w3-bar-item w3-button">One new friend request</a>
          <a href="#" class="w3-bar-item w3-button">John Doe posted on your wall</a>
          <a href="#" class="w3-bar-item w3-button">Jane likes your post</a>
        </div>
      </div>
      <a href="#" class="w3-bar-item w3-button  w3-right w3-padding-large w3-hover-white" title="My Account"><img ${userProfilePict} class="w3-circle" style="height:30px;width:30px" /></a>
     </div>
    
    
    <div class="w3-row-padding"  >
         <div class="w3-circle w3-col s2">
            <img ${visitedProfilePict} style="width:100%"/>
            <div>
                <b>User : ${userName}</b>
                <br/>
                <b>watch the profile of : ${profileName}</b>
                <div>
                    Dernière connection : ${profileConnection}
                </div>
            </div>  
         </div>
         <div class="w3-col s8 w3-center w3-grey">
             <h1>${pageName}</h1>
             <hr/>
             <jsp:include page="${pageName}.jsp"/>
         </div>    
    </div>
</BODY>
</HTML>