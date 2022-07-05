Vue.component("managersFacility", {
	data: function () {
		    return {
		      logedInUser: null,
			  managersFacility: null,
			  facilityTrainers:null,
			  facilityCustomers:null,
		     
		    }
	},
	template: ` 
<div class="trainings">
	<div v-if="managersFacility == null"> 
		<h2 class="list">You dont have a sports facility.</h2>
	</div>
    <div v-else>
		<h2 class="list">My facility</h2>
		<table class="customTable">
		<tr bgcolor="lightgrey">
			<th>Name</th>
			<th>Logo</th>
			<th>Type</th>
			<th>Grade</th>
			<th>Location</th>
			<th>Status</th>
		</tr>
		<tr>
			<td>{{managersFacility.name}}</td>
			<td>
			<img :src="photoPath(managersFacility)""> 
			</td>
			<td>{{managersFacility.type}}</td>
			<td>{{managersFacility.averageGrade}}</td>
			<td>{{managersFacility.location.address.street}}, {{managersFacility.location.address.number}},{{managersFacility.location.address.city}}, {{managersFacility.location.address.zipCode}} </td>
			<td v-if="managersFacility.works">Open</td>
			<td v-else>Closed</td>
		</tr>
		</table>

		<h2 class="list">Facility trainers</h2>
		<table  class="customTable">
		<tr bgcolor="lightgrey">
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of birth</th>
			<th>Gender</th>
		</tr>
		<tr v-for="t in facilityTrainers">
			<td>{{t.firstName}}</td>
			<td>{{t.lastName}}</td>
			<td>{{t.dateOfBirth}}</td>
			<td>{{t.gender}} </td>
		</tr>
		</table>

		<h2 class="list">Facility customers</h2>
		<table class="customTable">
		<tr bgcolor="lightgrey">
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of birth</th>
			<th>Gender</th>
		</tr>
		<tr v-for="c in facilityCustomers">
			<td>{{c.firstName}}</td>
			<td>{{c.lastName}}</td>
			<td>{{c.dateOfBirth}}</td>
			<td>{{c.gender}} </td>
		</tr>
		</table>

	</div>
    
</div>		  
`,
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
	axios
	.get('/rest/facilities/getManagersFacility')
	.then(response => (this.managersFacility = response.data))

	axios
	.get('/rest/facilities/getFacilityTrainers')
	.then(response => (this.facilityTrainers = response.data))

	axios
	.get('/rest/facilities/getFacilityCustomers')
	.then(response => (this.facilityCustomers = response.data))
	
	
}
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
		photoPath: function (f) {
			if (f.logo === 'None') {
				return 'img/logo.png';
			} else {
				return f.logo;	
			}
		},
		
	},
				
})