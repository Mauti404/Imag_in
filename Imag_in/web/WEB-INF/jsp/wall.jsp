<h2> Ecrire un message :</h2>
<div>
    <form class="changeImg" id="changeImg" method="POST" action="sendMessage.htm" enctype="multipart/form-data">
       <div id="custom-board" class="message"></div>
       <input id ="hidden_data_canvas" name="imageMassage" type="hidden"/>
       <input type="submit">
    </form>    
</div>
<h2> Liste messages :</h2>
<div>
   ${messagesList}
</div>