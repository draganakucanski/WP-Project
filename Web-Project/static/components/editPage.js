Vue.component("editpage", {
	data: function () {
		    return {
		      logedInUser: {
			  username: '',
           	  password: '',
              firstName: '',
              lastName: '',
              gender: '',
              dateOfBirth: ''
              
		},
		     
		    }
	},
	template: ` 
<div>

	 <section class="header">
	 	<nav>
          <div class="nav-links">
          
                <ul>
                    
                    
                    <li><a @click="redirectOnFacilities">Our Facilities</a>
                    <li><a @click="redirectOnMain">Log Out</a></li>
                </ul>
            </div>
        </nav>
	
     
    <div>
	<table width="100%">
		<th> First Name</th>
		<td><input type="text" name = "firstName" v-model = "logedInUser.firstName"></td> 
	<th> Last Name</th>
		<td><input type="text" name = "lastName" v-model = "logedInUser.lastName"></td> 
	<th>Date of birth</th>
		<td><input type="text" name = "dateOfBirth" v-model = "logedInUser.dateOfBirth"></td> 
	<th> Password </th>
		<td><input type="text" name = "password" v-model = "logedInUser.password"></td> 
	<th> Gender </th>
		<td><input type="text" name = "gender" v-model = "logedInUser.gender"></td>
	
	
	</table>  
	<fieldset style="border: 0px;"><button class="hero-btn" type="button" v-on:click="editDataAndRedirectOnFrontPage">Edit</button></fieldset> 
	</div>
    </section>
    
</div>		  
`
	,
	methods : {
		redirectOnMain : function(){
			axios.post('/rest/logOut')
			.then(response => {
				console.log("logout return:");
				console.log(response.data);
			})
			router.push('/');	
		},
		
		redirectOnFacilities: function(){
			router.push('/facilities');
		},
		editDataAndRedirectOnFrontPage : function(){
		
        
			axios
				.post('/rest/users/editData/', this.logedInUser )
				.then(response => {
					console.log("edit return: ");
					console.log(response.data);
					router.push('/frontpage');
				})
			
			console.log(this.logedInUser.firstName)
			
	},
	},
	   
	   
		//kada god zelim koristiti podatke ulogovanog korisnika
		//kopiram ovu mounted funkciju
		//i definisem gore u data: logedInUser: null
	 	mounted () {
			axios.get('/rest/users/getLogedUser/')
			.then(response => {
				if (response.data == '404'){
					console.log('No loged in user.');
				}
				else {
					this.logedInUser = response.data;
					console.log('LOGED IN USER:' + this.logedInUser.username);
				}
			})
	}
				
})