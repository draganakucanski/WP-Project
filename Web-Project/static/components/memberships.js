Vue.component("memberships", {
	data: function () {
		    return {
			  logedInUser: null,
		      memberships: null,
			  type: 'opt',
		      searchParam: {
				name: "",
				type: "",
				location: "",
				grade: 0
			},
			buttonClicked: 'false',
			promoCode: '',
			filterWorks: ''
		    }
	},
	template: ` 
<div class="memberships">
	<h2 class="list">List of memberships:</h2>   
	<table class="customTable">
	<tr bgcolor="lightgrey">
		<th>Id</th>
		<th>Date1</th>
		<th>Date2</th>
		<th>Type</th>
		<th>price</th>
		<th>customer</th>
		<th>active</th>
	</tr>
		
	<tr v-for="m in memberships">
		<td>{{m.id}}</td>
		<td>
		{{m.payDate}}
		</td>
		<td>{{m.validTime}}</td>
		<td>{{m.type}}</td>
		<td>{{m.price}}</td>
		<td>{{m.customer}}</td>
		<td v-if="m.active">Active</td>
		<td v-else>Unactive</td>
	</tr>
</table>
<div v-if="buttonClicked=='false'">
<h2 class="chM"> Choose membership</h2>
<form name="membership" id="membership" method="post">

<table style="padding-bottom: 5px;" class="reg">
	<tr>
		<td><label style="color: rgb(0, 40, 101); text-align:center; font-size:18px ">Select subscrption type</label><br><br></td>
	</tr>
  <tr>
	  <td>
			  <select class="memb" name="type" id="type" v-model="type">
				<option value="opt" selected disabled hidden >Type</option>
				<option value="monthly">Monthly</option>
				<option value="yearly">Yearly</option>
			  </select>
			  <br>
			  <br>
	  </td>
  </tr>
  <tr  v-if="type!='opt'"> 
	<td v-if="type=='monthly'"><br>
	<label style="color: rgb(242, 190, 141); font-size:18px">With this membership you have:</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 16 monthly trainings</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 1 daily visits with  GymPass app</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 2500RSD</label><br><br>
	</td>
	<td v-if="type=='yearly'"><br>
	<label style="color: rgb(242, 190, 141); font-size:18px">With this membership you have:</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 30 monthly trainings</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 3 daily visits with  GymPass app</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 26000RSD</label><br><br>
	</td>
  </tr>
  <tr v-if="type=='monthly' || type=='yearly'">
		<td><input class="memberships" type="button" id="registrationButton" v-on:click="buttonClicked='true'" value="Create Membership"/></td>
  </tr>
  
</table>
</form>  
</div>  
<div v-if="buttonClicked=='true'">
<form name="membershipCreate" id="membershipCreate" method="post">

<table style="padding-bottom: 5px;" class="reg">
	<tr>
		<td><label style="color: rgb(0, 40, 101); text-align:center; font-size:18px ">Choosen membership</label></td>
	</tr>
  <tr  v-if="type!='opt'"> 
	<td v-if="type=='monthly'"><br>
	<label style="color: rgb(242, 190, 141); font-size:18px">M O N T H L Y</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 16 monthly trainings</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 1 daily visit with  GymPass app</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 2500RSD</label><br><br>
	</td>
	<td v-if="type=='yearly'"><br>
	<label style="color: rgb(242, 190, 141); font-size:18px">Y E A R L Y</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 30 monthly trainings</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 3 daily visits with  GymPass app</label><br>
	<label style="color: rgb(242, 190, 141); font-size:18px"> - 26000RSD</label><br><br>
	</td>
  </tr>
  <tr>
		<td><label style="color: rgb(0, 40, 101); text-align:center; font-size:18px ">Enter promo code:</label> <input type="text" v-model="promoCode"> <br><br></td>
  </tr>
  <tr v-if="type=='monthly' || type=='yearly'">
		<td><input class="memberships" type="button" id="registrationButton" v-on:click="CreateMembership" value="Confirm"/></td>
  </tr>
  
</table>
</form>  
</div>     
</div>	
`
,

	
	mounted () {
        axios
          .get('/rest/memberships/getMemberships')
          .then(response => (this.memberships = response.data))
          
          axios
			.get('/rest/users/getLogedUser/')
			.then(response => {
				if (response.data == '404'){
					console.log('No loged in user.');
				}
				else {
					this.logedInUser = response.data;
					console.log('LOGED IN USER:' + this.logedInUser.username);
				}
    },
    )},
    
	methods: {
			
		CreateMembership: function () {
			axios.post('rest/memberships/createMembership', this.type)
              .then(response => {
                 alert('Successfully created!');

                })
              .catch(() => {alert('Error while creating.')});
		}
			
		}
   });