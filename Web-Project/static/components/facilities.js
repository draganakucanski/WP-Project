Vue.component("facilities", {
	data: function () {
		    return {
		      facilities: null
		      
		    }
	},
	template: ` 
<div>
	List of facilities:
	<table border="2">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		
		<th>Status</th>
	</tr>
		
	<tr v-for="f in facilities">
		<td>{{f.name}}</td>
		<td v-if="f.works">Open</td>
		<td v-else>Closed</td>
	</tr>
</table>
	
</div>		  
`
,
mounted () {
        axios
          .get('/rest/facilities/getJustFacilities/')
          .then(response => (this.facilities = response.data))
    },
   });