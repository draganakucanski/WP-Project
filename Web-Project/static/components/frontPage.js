Vue.component("frontpage", {
	data: function () {
		    return {
		      logedInUser: null
		    }
	},
	template: ` 
<div>
	 <section class="header">
	 	<nav>
          <div class="nav-links">
                <ul>
                    <li><a >SOMETHING1</a></li>
                    <li><a @click="redirectOnEdit" >Edit profile info</a></li>
                    <li><a @click="redirectOnFacilities">Our Facilities</a></li>
                    <li><a @click="redirectOnMain">Log Out</a></li>
                </ul>
            </div>
        </nav>

     
    <div>
    <table class="customTable">
	<tr bgcolor="lightblue">
		<th>First Name</th>
		<th>Last Name</th>
		<th>Date of birth</th>
		<th>Gender</th>
		<th>Role</th>
		<th>Type</th>
	</tr>
		
	<tr >
		<td>{{logedInUser.firstName}}</td>
		
		<td>{{logedInUser.lastName}}</td>
		<td>{{logedInUser.dateOfBirth}}</td>
		<td>{{logedInUser.gender}} </td>
		<td >{{logedInUser.role}}</td>
		
		<td>{{logedInUser.type.userTypeName}}</td>
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
		redirectOnEdit : function(){
		router.push('/editpage');
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