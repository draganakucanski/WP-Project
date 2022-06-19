Vue.component("facilities", {
	data: function () {
		    return {
		      facilities: null,
		      searchParam: {
				name: "",
				type: "",
				location: "",
				grade: 0
			},
		    }
	},
	template: ` 
<div class="facilities">
	<h2 class="list">List of facilities:</h2>
	<table class="customTable">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Type</th>
		<th>Grade</th>
		<th>Location</th>
		<th>Status</th>
	</tr>
		
	<tr v-for="f in facilities">
		<td>{{f.name}}</td>
		<td>{{f.type}}</td>
		<td>{{f.averageGrade}}</td>
		<td>{{f.location.address.street}}, {{f.location.address.number}},{{f.location.address.city}}, {{f.location.address.zipCode}} </td>
		<td v-if="f.works">Open</td>
		<td v-else>Closed</td>
	</tr>
</table>
<div class="search">
		  <h2 class="search">Search</h2>
			<table>
			  <tr><td><input type="text" placeholder="Facility name" v-model="searchParam.name"></td></tr>
			  <tr><td><input type="text" placeholder="City" v-model="searchParam.location"></td></tr>
			  <tr><td><input type="number" placeholder="Average grade" min=0 max=10 v-model="searchParam.grade"></td></tr>
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
          .then(response => (this.facilities = response.data))
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
	
			}
		}
   });