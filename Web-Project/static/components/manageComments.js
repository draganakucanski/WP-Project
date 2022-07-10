Vue.component("manageComments", {
	data: function () {
		    return {
		      logedInUser: null,
			  comments: null,
		    }
	},
	template: ` 
<div class="registration">
    <div>
		<h2 class="list">Comments</h2>
		<table class="customTable">
		<tr bgcolor="lightgrey">
			<th>Customer</th>
			<th>Facility name</th>
			<th>Content</th>
			<th>Grade</th>
			<th>Manage</th>
		</tr>
		<tr v-for="c in comments">
			<td>{{c.customer}}</td>
			<td>{{c.facilityName}}</td>
            <td>{{c.content}}</td>
			<td>{{c.grade}}</td>
			<td v-if="c.approved">Approved</td>
			<td v-else><button class="addNew" style="width:80px; margin:0px" v-on:click="Approve(c)">Approve</button></td>
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
	.get('/rest/comments/getComments')
	.then(response => (this.comments = response.data))
	
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
		redirectOnAddingContent: function(){
			router.push('/addContent');
		},
		redirectOnFacilities: function(){
			router.push('/facilities');
		},
		Approve: function(c){
			axios
				.post('/rest/comments/approve', c)
				.then(response => {
					alert("Comment approved!");
				})
		},
		
	},
				
})