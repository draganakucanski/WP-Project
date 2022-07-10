Vue.component("facilityView", {
	data: function () {
		    return {
			  logedInUser: null,
		      facility: null,
			  trainings:null,
			  comments:null,
		    }
	},
	template: ` 
<div class="registration">
	<h2 class="list">Facility details:</h2>
	<div class="card">
      <div>
              <div class="facilityDiv" style="height:200px;">
                  <div class="row">
                      <div class="leftColumnFacility">
                          <img :src="photoPath(facility)" class="FacilityLogoCSS"> 
                      </div>
                      <div class="middleColumn">
                          <table>
                              <tr><td><h4>{{facility.name}}</h4></td></tr>
                              <tr><td>Facility type: {{facility.type}}</td></tr>
                              <tr><td>Location: {{facility.location.address.street}}, {{facility.location.address.number}}, {{facility.location.address.city}}, {{facility.location.address.zipCode}}</td></tr>
                              <tr><td v-if="facility.works">Status: Open</td>
							  <td v-else>Status: Closed</td></tr>
                          </table>
						  <div>
							<p>Grade: {{facility.averageGrade}}&#9733;</p>
						  </div>
                      </div>
                   </div>
        </div>
      </div>
    </div>
	<h2 class="list">Training schedule</h2>
			  <table  class="customTable">
			  <tr bgcolor="lightgrey">
				  <th>Name</th>
				  <th>Duration</th>
				  <th>Trainer</th>
				  <th>Description</th>
				  <th>Picture</th>
				  <th>Type</th>
			  </tr>
			  <tr v-for="tr in trainings">
				  <td>{{tr.name}}</td>
				  <td>{{tr.duration}} hours</td>
				  <td>{{tr.trainer}}</td>
				  <td>{{tr.description}} </td>
				  <td><img :src="photoPathPic(tr)"></td>
				  <td>{{tr.type}}</td>
			  </tr>
			  </table>
		
	<div v-if="logedInUser != null">
		<div class="leftcolumn" v-if="logedInUser.role=='admin' || logedInUser.role=='manager'">
			<div id="comments" class="card">
				<h2> Comments and grades</h2>
				
				<div id="commentsList">
							  <div class="commentDiv" v-for="c in comments">
								  <div class="row">
									  <div class="leftColumnComment">
										<label>{{c.grade}}</label>
									  </div>
									  <div class="middleColumn">
										<table style="max-width:400px; word-wrap:break-word;">
										  <tr><td>{{c.content}}</td></tr>
										</table>
									  </div>
									  <div class="authorRight">
										 {{c.customer}}
									  </div>
								  </div>
							  </div>
				  
				  
				</div>
			  </div>
			</div>
		</div>


		<div class="leftcolumn" v-if="logedInUser==null || logedInUser.role=='customer' || logedInUser.role=='trainer'">
			<div id="comments" class="card">
				<h2> Comments and grades</h2>
				
				<div id="commentsList" v-for="c in comments">
							  <div class="commentDiv" v-if="c.approved">
								  <div class="row">
									  <div class="leftColumnComment">
										<label>{{c.grade}}</label>
									  </div>
									  <div class="middleColumn">
										<table style="max-width:400px; word-wrap:break-word;">
										  <tr><td>{{c.content}}</td></tr>
										</table>
									  </div>
									  <div class="authorRight">
										 {{c.customer}}
									  </div>
								  </div>
							  </div>
				  
				  
				</div>
			  </div>
			</div>
</div>	
`
,

	
	mounted () {
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
			};})
				var path = window.location.href;
				var facilityName = path.split('/facilityView/')[1];
				var name = facilityName.replace('%20', ' '); 
				axios.get('rest/facilities/getFacilityByName', {
				  params: {
					name: name
				  }
				})
				  .then(response => {
					this.facility = response.data;
				  });
				  axios
				.get('rest/trainings/getFTrainings', {
					params: {
					  name: name
					}})
				.then(response => (this.trainings = response.data))
				axios
				.get('rest/comments/getFComments', {
					params: {
					  name: name
					}})
				.then(response => (this.comments = response.data))
    },
    
	methods: {
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
		}
   });