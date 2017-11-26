<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE> Imag'In </TITLE>
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
    <script src="js/drawingboard.min.js"></script>
    <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Open Sans", sans-serif}
    </style>
</HEAD>
<BODY class="w3-theme-l5">   
    <div>
        <b>User :</b>
    </div>
    <div>
        ${userName}
    </div>
    <div>
        <form accept-charset="UTF-8" role="form" method="POST" ACTION="sendMessage.htm">
            <fieldset>
                <input placeholder="message" name="message" type="text">
                <input type="submit">
            </fieldset>
        </form>
        <form accept-charset="UTF-8" role="form" method="POST" ACTION="removeMessage.htm">
            <fieldset>
                <input placeholder="message" name="message" type="text">
                <input type="submit">
            </fieldset>
        </form>
        <form accept-charset="UTF-8" role="form" method="POST" ACTION="addFriend.htm">
            <fieldset>
                <input placeholder="friend" name="friend" type="text">
                <input type="submit">
            </fieldset>
        </form>
        <form accept-charset="UTF-8" role="form" method="POST" ACTION="removeFriend.htm">
            <fieldset>
                <input placeholder="friend" name="friend" type="text">
                <input type="submit">
            </fieldset>
        </form>
    </div>
    <div>
        Dernière connection : ${userConnection}
    </div>
    <div id="custom-board" class="dessin"></div>
    <div>
        <b>Messages :</b>
    </div>
    <div>
        ${messages}
    </div>
    <div>
        <b>Amis :</b>
    </div>
    <div>   
        ${amis}
    </div>
    <div>
        <img class="" id="" src="data:${Extension};base64,${ProfilPic}" alt="">
    </div>
    <div> Ajouter une image de profil </div>
    <form method = "POST" action="addProfilPict.htm" enctype="multipart/form-data" >
        <input type="file" id="profil_pic" name="profil_pic">
        <input type="submit">
    </form>
    
</BODY>
</HTML>