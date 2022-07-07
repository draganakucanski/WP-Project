Vue.component("managersFacility", {
	data: function () {
		    return {
		      logedInUser: null,
			  managersFacility: null,
			  facilityTrainers:null,
			  facilityCustomers:null,
			  trainings:null,
			  buttonClicked: 'false',
		      selectedTraining:null,
			  backup:null,
			  trainers: null,
			  imageView:'',
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
			<img :src="photoPath(managersFacility)"> 
			</td>
			<td>{{managersFacility.type}}</td>
			<td>{{managersFacility.averageGrade}}</td>
			<td>{{managersFacility.location.address.street}}, {{managersFacility.location.address.number}},{{managersFacility.location.address.city}}, {{managersFacility.location.address.zipCode}} </td>
			<td v-if="managersFacility.works">Open</td>
			<td v-else>Closed</td>
		</tr>
		</table>

		<h2 class="list">Facility content</h2>
			  <button class="addNew" style="float:right" v-on:click="redirectOnAddingContent">Add new content</button>
			  <table  class="customTable">
			  <tr bgcolor="lightgrey">
				  <th>Name</th>
				  <th>Duration</th>
				  <th>Trainer</th>
				  <th>Description</th>
				  <th>Picture</th>
				  <th>Type</th>
				  <th>Manage</th>
			  </tr>
			  <tr v-for="tr in trainings">
				  <td>{{tr.name}}</td>
				  <td>{{tr.duration}} hours</td>
				  <td>{{tr.trainer}}</td>
				  <td>{{tr.description}} </td>
				  <td><img :src="photoPathPic(tr)"></td>
				  <td>{{tr.type}}</td>
				  <td><button class="addNew" style="width:60px" v-on:click="Edit(tr)">Edit</button></td>
			  </tr>
			  </table>
			  <br>
			  <br>
		<div style="background-color: #FFF7E1; border: 2px solid #283966;
		border-radius: 10px;"  v-if="buttonClicked=='true'">
			<button class="addNew" style="width:80px; float:right;" v-on:click="Cancel">Cancel</button>
			<table width="100%">
				<th>Name</th>
				<td><input type="text" name = "name" v-model = "selectedTraining.name"></td> 
				<th>Duration in hours</th>
				<td><input type="text" name = "duration" v-model = "selectedTraining.duration"></td> 
				<th>Trainer</th>
				<td><select v-model="selectedTraining.trainer"><option v-for="t in trainers">{{t.username}} </option></select></td> 
				<th>Description </th>
				<td><input type="text" name = "description" v-model = "selectedTraining.description"></td> 
				<th>Picture</th>
				<td><img :src="photoPathPic(selectedTraining)" v-if="imageView==''">
				<img :src="imageView" style="width: 20px; height:20px;" v-else>
				</td>
				<td><input type="file" @change="fileChange"></td>
				<th>Type</th>
				<td><select name="Type" v-model="selectedTraining.type">
					<option value="group">Group</option>
					<option value="personal">Personal</option>
					<option value="gym">Gym</option>
			  </select></td>
			  <th><button class="addNew" style="width:80px" v-on:click="EditData">Confirm</button></th>
			</table>
			<br>
		</div>
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
	.get('rest/trainings/getMTrainings')
	.then(response => (this.trainings = response.data))

	axios
	.get('/rest/facilities/getFacilityTrainers')
	.then(response => (this.facilityTrainers = response.data))

	axios
	.get('/rest/facilities/getFacilityCustomers')
	.then(response => (this.facilityCustomers = response.data))

	axios
	.get('rest/users/getTrainers/')
	.then(response =>{
		this.trainers = response.data;})
	
	
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
		photoPath: function (f) {
			if (f.logo === 'None') {
				return 'img/logo.png';
			} else {
				return f.logo;	
			}
		},
		photoPathPic: function (t) {
			if (t.picture === 'None') {
				return 'img/logo.png';
			} else {
				return t.picture;	
			}
		},
		Cancel: function(){
			this.buttonClicked = 'false';
			this.selectedTraining = this.backup;
		},
		Edit: function(tr){
			this.buttonClicked = 'true';
			this.selectedTraining = tr;
			this.backup = this.selectedTraining;
		},
		fileChange: function (e) {
            const file = e.target.files[0];
            this.makeBase64Image(file);
        },
        makeBase64Image: function (file) {
            const reader= new FileReader();
            reader.onload = (e) =>{
                this.imageFile = e.target.result;
            }
			reader.readAsDataURL(file);
			this.selectedTraining.picture = URL.createObjectURL(file);
			
        },
		EditData: function(){
			axios
				.post('/rest/trainings/editData/', { "name": this.selectedTraining.name, "type" : this.selectedTraining.type, "duration" : this.selectedTraining.duration,"description" : this.selectedTraining.description,"trainer": this.selectedTraining.trainer,"logo" : this.selectedTraining.picture,"imageFile": this.imageFile })
				.then(response => {
					alert("Training edited!");
				})
			
			
	},
		
	},
				
})