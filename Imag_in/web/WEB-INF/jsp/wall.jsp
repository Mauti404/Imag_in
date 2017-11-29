<h2> Ecrire un message :</h2>
<div>
    <form class="writeMessage" id="writeMessage" method="POST" action="sendMessage.htm" enctype="multipart/form-data">
       <div id="writeMessageBoard" class="message"></div>
       <input id ="hidden_data_canvas" name="hidden_data" type="hidden"/>
       <input type="submit">
    </form>    
</div>
<h2> Liste messages :</h2>
<div>
   ${messagesList}
</div>