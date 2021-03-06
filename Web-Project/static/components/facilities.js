Vue.component("facilities", {
	data: function () {
		    return {
			  logedInUser: null,
		      facilities: null,
		      //managers: null,
		      searchParam: {
				name: "",
				type: "",
				location: "",
				grade: 0
			},
			sortType: "Opened",
			filterType: '',
			filterWorks: ''
		    }
	},
	template: ` 
<div class="registration">
	<h2 class="list">List of facilities:</h2>
	<div>
			   <label class="sort">Sort by:</label>
			   <select name="sort" v-on:change="sort" v-model="sortType">
				<option value="Opened">Opened first</option>
				<option value="Closed">Closed first</option>
				<option value="GradeAsc">Average grade ascending</option>
				<option value="GradeDisc">Average grade discending</option>
				<option value="NameAsc">Name A-Z</option>
				<option value="NameDisc">Name Z-A</option>
				<option value="LocationAsc">Location A-Z</option>
				<option value="LocationDisc">Location Z-A</option>
			  </select>
			  <label class="filterT">Facility type:</label>
			   <select name="filter" v-on:change="FilterType" v-model="filterType">
			    <option value="all">All types</option>
				<option value="GYM">Gyms</option>
				<option value="POOL">Pools</option>
				<option value="DANCESTUDIO">Dance studios</option>
				<option value="SPORTSCENTER">Sports centers</option>
			  </select>
			  <label class="filterW">Opened/Closed:</label>
			   <select name="filter" v-on:change="FilterWorks" v-model="filterWorks">
			    <option value="both">Both</option>
				<option value="true">Opened</option>
				<option value="false">Closed</option>
			  </select>
			  <div v-if="logedInUser != null">
			  <button v-if="logedInUser.role === 'admin'" class="search" type="button" style="float:right;" v-on:click="redirectOnAddingFacilities">Add a facility</button>
			  </div>
			<br>
			<br>
		  </div>
	<table class="customTable">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Logo</th>
		<th>Type</th>
		<th>Grade</th>
		<th>Location</th>
		<th>Status</th>
		<th v-if="logedInUser != null && logedInUser.role=='admin'"></th>
		
	</tr>
		
	<tr v-for="f in facilities" v-on:click="facilityView(f)">
		<td>{{f.name}}</td>
		<td>
		<img :src="photoPath(f)""> 
		</td>
		<td>{{f.type}}</td>
		<td>{{f.averageGrade}}</td>
		<td>{{f.location.address.street}}, {{f.location.address.number}},{{f.location.address.city}}, {{f.location.address.zipCode}} </td>
		<td v-if="f.works">Open</td>
		<td v-else>Closed</td>
		<td v-if="logedInUser != null && logedInUser.role=='admin'"><button class="addNew" style="width:80px; margin:0px;" v-on:click="Delete(f)">Delete</button></td>
		</tr>
</table>
<div class="search">
		  <h2 class="search">Search</h2>
			<table>
			  <tr><td><input type="text" placeholder="Facility name" v-model="searchParam.name"></td></tr>
			  <tr><td><input type="text" placeholder="City" v-model="searchParam.location"></td></tr>
			  <tr><td><input type="number" placeholder="Average grade" min=0 max=5 v-model="searchParam.grade"></td></tr>
			  <tr><td><select name="Facility type" v-model="searchParam.type">
				<option selected value="Types">Types</option>
				<option value="GYM">Gym</option>
				<option value="POOL">Pool</option>
				<option value="SPORTSCENTER">Sports center</option>
				<option value="DANCESTUDIO">Dance studio</option>
			  </select></td></tr>
			  <tr><td><button class="search" v-on:click="FacilitySearch">Search</button></td></tr>
			</table>
		</div>	     
</div>	
`
,

	
	mounted () {
        axios
          .get('/rest/facilities/getJustFacilities/')
          .then(response => (this.facilities = response.data.sort((b, a) => (a.works > b.works) ? 1 : ((b.works > a.works) ? -1 : 0))))
          
         axios
			.get('/rest/users/getLogedUser/')
			.then(response => {
				if (response.data == '404'){
					this.logedInUser = null;
					console.log('No loged in user.');
				}
				else {
					this.logedInUser = response.data;
					console.log('LOGED IN USER:' + this.logedInUser.username);
				};
		 
		 	
    })
	/*
    	 axios
		 	.get('/rest/users/getManagers/')
		 	.then(response =>{
		 		this.managers = response.data;
		 		
		 		})
				*/
    },
    
	methods: {
			FacilitySearch: function () {
				axios.get('rest/facilities/getFacilitiesSearch', { params: {
					 name: this.searchParam.name,
					 type: this.searchParam.type,
					 location: this.searchParam.location,
					 grade: this.searchParam.grade
				} })
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
						
					});	
	
			},
			redirectOnAddingFacilities: function(){
				
				if(this.logedInUser.role === "admin"){
					console.log("Managers:");
		 				//console.log(this.managers);
					router.push('/addfacilities')
				}
			},
			facilityView: function (f) {
				window.location.href = "#/facilityView/"+f.name;
			},
			sort: function () {
			if (this.sortType == 'Opened') {
				this.facilities.sort((b, a) => (a.works > b.works) ? 1 : ((b.works > a.works) ? -1 : 0));
			} else if (this.sortType == 'Closed') {
				this.facilities.sort((a, b) => (a.works > b.works) ? 1 : ((b.works > a.works) ? -1 : 0));
			} else if (this.sortType == 'GradeDisc') {
				this.facilities.sort((b, a) => (a.averageGrade > b.averageGrade) ? 1 : ((b.averageGrade > a.averageGrade) ? -1 : 0));
			} else if (this.sortType == 'GradeAsc') {
				this.facilities.sort((a, b) => (a.averageGrade > b.averageGrade) ? 1 : ((b.averageGrade > a.averageGrade) ? -1 : 0));
			} else if (this.sortType == 'NameAsc') {
				this.facilities.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'NameDisc') {
				this.facilities.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'LocationDisc') {
				this.facilities.sort((b, a) => (a.location.address.city > b.location.address.city) ? 1 : ((b.location.address.city > a.location.address.city) ? -1 : 0));
			} else if (this.sortType == 'LocationAsc') {
				this.facilities.sort((a, b) => (a.location.address.city > b.location.address.city) ? 1 : ((b.location.address.city > a.location.address.city) ? -1 : 0));
			}
		},
		FilterType: function () {
		if(this.filterType=='GYM'){
				axios.get('rest/facilities/getGyms')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='POOL'){
				axios.get('rest/facilities/getPools')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='DANCESTUDIO'){
				axios.get('rest/facilities/getDanceS')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='SPORTSCENTER'){
				axios.get('rest/facilities/getSportsC')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterType=='all'){
				axios.get('rest/facilities/getJustFacilities/')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
	
			},
		photoPath: function (f) {
			if (f.logo === 'None') {
				return 'img/logo.png';
			} else {
				return f.logo;	
			}
		},
		Delete: function(f){
			axios
				.post('/rest/facilities/delete', f)
				.then(response => {
					if (response.data == true){
						alert('Sports facility is deleted');
					}
				})
		},
			FilterWorks: function () {
			if(this.filterWorks=='true'){
				axios.get('rest/facilities/getOpened')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		 else if(this.filterWorks=='false'){
				axios.get('rest/facilities/getClosed')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
		else if(this.filterWorks=='both'){
				axios.get('rest/facilities/getJustFacilities/')
					.then(response => (this.facilities = response.data)).catch(function (error) {
						alert('Error on server');
					});	
				}
	
			}
		}
   });