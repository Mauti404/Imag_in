<h2> Liste des amis :</h2>
<div>
   
   <c:forEach var="friend" items="${friendsList}">
        <div class="item" >
            <div class="work_item">
                <div class="work_img" style="object-fit: cover; height: 250px; background-color: #e7e7e7;">
                    <img src="data:image/jpeg;base64,${work.base64}" alt="" style="object-fit: cover" height="200" />
                    <a class="zoom" href="data:image/jpeg;base64,${work.base64}" rel="prettyPhoto[portfolio1]" ></a>
                </div>
                <div class="work_description">
                    <div class="work_descr_cont">
                        <form method = "POST" action="Detail.htm" >
                            <div align="center">
                                <input type="hidden" value="${work.id}" name="workid"/>
                                <input type="submit" class="btn " value="${work.name}"/>
                            </div>
                        </form>   
                        <span><c:out value="${work.date}"/></span>
                    </div>
                </div>
            </div>
        </div><!-- //work1 -->
    </c:forEach>
</div>
<h2> Liste de tout les utilisateurs </h2>
<div>
    ${allUsers}
</div>