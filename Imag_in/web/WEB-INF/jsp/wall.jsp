<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE> Imag'In </TITLE>
</HEAD>
<BODY>
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
    </div>
    <div>
        Dernière connection : ${userConnection}
    </div>
    <div>
        <b>Messages :</b>
    </div>
    <div>
        ${messages}
    </div>
    
</BODY>
</HTML>