/*** Image Blob ****/

//Ajouter au librairies:
//commons-io-2.6.jar
//commons-fileupload-1.3.1.jar

//Ajouter au dispatcher-servlet:
 <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	<property name="maxUploadSize" value="100000000"/>
</bean>

///////////Dans l'entité///////////
/*On créé un tableau de byte[] pour l'image*/
	@Lob
    @Column
    private byte[] profilePic;

/*Si on ne veut pas que mettre des images on récupère l'extension du fichier*/
	@Column(name="extprofil")
	private String extprofil;
	
/*On fait une variable pour l'affichage qui n'est pas stockée dans la base de données*/
	@Transient
   private String base64Profil;
   
/*On fait les getter et setter */
   

/****Enregistrement*****/

/////////Dans la jsp du formulaire /////////

/*La balise du formulaire doit contenir enctype="Multipart/Form-data"*/
<form method = "POST" action="index.htm" enctype="multipart/form-data" >

/*exemple d'input*/
<input type="file" id="profil_pic" name="profil_pic">


/////////Dans controller pour enregister dans la base de données//////////

/*Dans les paramètres du controller ajouter: */
@RequestParam("profil_pic") MultipartFile file


/*dans le controller pour enregister*/
user.setProfilPic((byte[]) file.getBytes());
user.setExtProfil(file.getContentType());


/*Et voila c'est enregistré!*/

/******Affichage********/

///////Dans le controller qui gère l'affichage /////////

/*On récupère l'utilisateur et on lui set la base64*/
user.setBase64Profil(Base64.encodeBase64String(user.getProfilPic());

/*On fait ensuite passé à la vue l'image*/
mv.addObject("ProfilPic", user.getBase64Profil());
/*Si on a pas que des images on fait aussi passer l'extension*/
mv.addObject("Extension", user.getExtProfil());


////////Dans la jsp//////////

/*Si on a que des images on a une balise comme ça pour l'image*/
<img class="" id="" src="data:image/jpeg;base64,${ProfilPic}" alt="">

/*Si on a des extensions particulière on fait: */
<img class="" id="" src="data:${Extension};base64,${ProfilPic}" alt="">


/*Et voila c'est affiché!*/
