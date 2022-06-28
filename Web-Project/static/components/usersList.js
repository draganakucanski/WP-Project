Vue.component("userslist", {
	data: function () {
		    return {
		      usersList: null,
		      searchParam:{
				 username: '',
             	 firstName: '',
             	 lastName: '',
             	 gender: '',
             	 dateOfBirth: '',
             	 role: ''
			
			}
			  
              
			}
		     
		 },
	template: ` 
<div>

	 <section class="header">
	 	<nav>
          <div class="nav-links">
          
                <ul>
                    
                    <li><a @click="redirectOnFrontPage">Front Page</a>
                    <li><a @click="redirectOnFacilities">Our Facilities</a>
                    <li><a @click="redirectOnMain">Log Out</a></li>
                </ul>
            </div>
        </nav>
	
     
    <div>
	<table class="customTable">
		<tr bgcolor="lightgrey">
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Date of birth</th>
			<th>Role</th>
		</tr>
		
		<tr v-for="u in this.usersList">
			<td>{{u.username}}</td>
		
			<td>{{u.firstName}}</td>
			<td>{{u.lastName}}</td>
			<td>{{u.gender}}</td>
			<td>{{u.dateOfBirth}}</td>
			<td>{{u.role}}</td>
		
		</tr>
	</table>
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
		redirectOnFrontPage: function(){
		
			router.push('/frontpage');	
	},
	},
	   
	   
		//kada god zelim koristiti podatke ulogovanog korisnika
		//kopiram ovu mounted funkciju
		//i definisem gore u data: logedInUser: null
	 	mounted () {
			axios.get('/rest/users/getAllUsers/', { params: {
					 
				} })
					.then(response => (this.usersList = response.data))
						console.log(this.usersList);
	}
				
})