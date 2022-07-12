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
<div >
	
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
        <h2 class="list">List of all users:</h2>
	<div>
			   <label class="sort">Sort by:</label>
			   <select name="sort" v-on:change="sort" v-model="sortType">
				
				<option value="NameAsc">First Name A-Z</option>
				<option value="NameDisc">First Name Z-A</option>
				<option value="UsernameAsc">Username A-Z</option>
				<option value="UsernameDisc">Username Z-A</option>
				<option value="LastNameAsc">Last Name A-Z</option>
				<option value="LastNameDisc">Last Name Z-A</option>
				<option value="PointsCollectedAsc">Points Collected Min to Max</option>
				<option value="PointsCollectedDisc">Points Collected Max to Min</option>
			  </select>
			  <label class="filterT">Role type:</label>
			   <select name="filter" v-on:change="FilterType" v-model="filterType">
			    <option value="all">All types</option>
				<option value="CUSTOMERS">Customers</option>
				<option value="MANAGERS">Managers</option>
				<option value="ADMINS">Admins</option>
				<option value="TRAINERS">Trainers</option>
			  </select>
			  
			  <label class="filterT">User type:</label>
			   <select name="filter" v-on:change="FilterTypeType" v-model="filterTypeType">
			    <option value="all">All types</option>
				<option value="GOLD">Gold</option>
				<option value="BRONZE">Bronze</option>
				<option value="SILVER">Silver</option>
				
			  </select>
			  <button class="addNew" v-on:click="addNew">Add new manager/trainer</button>
			<br>
			<br>
		  </div>
	 	
	
     
    <div>
	<table class="customTable">
		<tr bgcolor="lightgrey">
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Date of birth</th>
			<th>Role</th>
			<th>Points Collected</th>
			<th></th>
		</tr>
		
		<tr v-for="u in this.usersList">
			<td>{{u.username}}</td>
		
			<td>{{u.firstName}}</td>
			<td>{{u.lastName}}</td>
			<td>{{u.gender}}</td>
			<td>{{u.dateOfBirth}}</td>
			<td>{{u.role}}</td>
			<td>{{u.pointsCollected}}</td>
			<td><button class="addNew" style="width:80px; margin:0px;" v-on:click="Delete(u)">Delete</button></td>
		
		</tr>
	</table>
	</div>
	<div class="search">
		  <h2 class="search">Search</h2>
			<table>
			  <tr><td><input type="text" placeholder="First name" v-model="searchParam.firstName"></td></tr>
			  <tr><td><input type="text" placeholder="Last Name" v-model="searchParam.lastName"></td></tr>
			  <tr><td><input type="text" placeholder="Username" min=0 max=5 v-model="searchParam.username"></td></tr>	
			  <tr><td><button class="search" v-on:click="UserSearch">Search</button></td></tr>
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
		addNew: function(){
			router.push('/addManagerTrainer');
		},
		UserSearch: function () {
				axios.get('rest/users/getUsersSearch/', { params: {
					 firstName: this.searchParam.firstName,
					 lastName: this.searchParam.lastName,
					 username: this.searchParam.username
					 
				} })
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
	
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
		sort: function () {
			 if (this.sortType == 'LastNameDisc') {
				this.usersList.sort((b, a) => (a.lastName > b.lastName) ? 1 : ((b.lastName > a.lastName) ? -1 : 0));
			} else if (this.sortType == 'LastNameAsc') {
				this.usersList.sort((a, b) => (a.lastName > b.lastName) ? 1 : ((b.lastName > a.lastName) ? -1 : 0));
			} else if (this.sortType == 'NameAsc') {
				this.usersList.sort((a, b) => (a.firstName > b.firstName) ? 1 : ((b.firstName > a.firstName) ? -1 : 0));
			} else if (this.sortType == 'NameDisc') {
				this.usersList.sort((b, a) => (a.firstName > b.firstName) ? 1 : ((b.firstName > a.firstName) ? -1 : 0));
			} else if (this.sortType == 'UsernameAsc') {
				this.usersList.sort((a, b) => (a.username > b.username) ? 1 : ((b.username > a.username) ? -1 : 0));
			} else if (this.sortType == 'UsernameDisc') {
				this.usersList.sort((b, a) => (a.username > b.username) ? 1 : ((b.username > a.username) ? -1 : 0));
			} else if (this.sortType == 'PointsCollectedAsc') {
				this.usersList.sort((a, b) => (a.pointsCollected > b.pointsCollected) ? 1 : ((b.pointsCollected > a.pointsCollected) ? -1 : 0));
			} else if (this.sortType == 'PointsCollectedDisc') {
				this.usersList.sort((b, a) => (a.pointsCollected > b.pointsCollected) ? 1 : ((b.pointsCollected > a.pointsCollected) ? -1 : 0));
			} 
		},
		FilterType: function () {
		if(this.filterType=='CUSTOMERS'){
				axios.get('rest/users/getCustomers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='MANAGERS'){
				axios.get('rest/users/getManagers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='ADMINS'){
				axios.get('rest/users/getAdmins/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='TRAINERS'){
				axios.get('rest/users/getTrainers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='all'){
				axios.get('rest/users/getAllUsers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
	
			},
			FilterTypeType: function () {
		if(this.filterTypeType=='BRONZE'){
				axios.get('rest/users/getBronzeUsers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterTypeType=='SILVER'){
				axios.get('rest/users/getSilverUsers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterTypeType=='GOLD'){
				axios.get('rest/users/getGoldUsers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterTypeType=='all'){
				axios.get('rest/users/getAllTypesOfUsers/')
					.then(response => (this.usersList = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
	
			},
		redirectOnFrontPage: function(){
		
			router.push('/frontpage');	
	},
	Delete: function(u){
		axios
			.post('/rest/users/delete', u)
			.then(response => {
					alert('User is deleted');
				
			})
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