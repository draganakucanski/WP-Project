Vue.component("addfacilities", {
	data: function () {
		   return {
			freeManagers: null,
            name: '',
            location: '',
            type: '',
            logo: '',
            imageView: '',
            longi: '',
            lat: '',
            number: '',
            street: '',
            city: '',
            zip: '',
           username:'',
            validInfo: {
               name: 'OK',
               emptyInput: 'OK'
            }
							
			}
	},
	template: `
    <div class ="registration">
         <form name="registration" id="registration" method="post">

          <table style="padding-bottom: 5px;" class="reg">
            <tr class="reg" >
              <th class="reg" style="padding: 0%;">
                <img width=15% height=60% src="../img/weights-1.png">
                GymPass
                </th>
            </tr>
            
            <tr>
              <td><input type="text" class="inputF" name="name" id="name" placeholder="Name" v-model="name" autofocus></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Street" v-model="street" id="street"></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Number" name="number" id="number" v-model="number"></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="City" v-model="city" name="city"  id="city"></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Zip code" v-model="zip" id="zip"></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Longitude" v-model="longi" name="longi" id="longi">	</td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Latitude" v-model="lat" name="lat"  id="lat">	</td>
            </tr>
            
            <tr>
                <td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="type" id="type" v-model="type">
                          <option value="" disabled selected hidden>Choose a type</option>
                          <option value="gym">Gym</option>
                          <option value="pool">Pool</option>
                          <option value="sportsCenter">Sports Center</option>
                          <option value="danceStudio">Dance Studio</option>
                        </select>
                </td>
            </tr>
            
             <tr>
             <input type="file" @change="promenaFajla"  style="width:300px"/>
			 <br>
			 <div v-if="imageView!=''" style="width: 100px; height:100px;">
				<img :src="imageView" style="width: 100px; height:100px;">
			 </div>
			 <br>    
            </tr>
            <tr>
            	<td ><select v-model="username"><option v-for="m in freeManagers">{{m.username}} </option></select></td>
            </tr>
            <tr>
                  <td><input class="heroButtonReg" type="button" id="addObjectButton" v-on:click="AddNewObject" value="Add new object"/></td>
            </tr>
            
          </table>
        </form>
         <div class="err" v-if="validInfo.name!='OK'"><br>
            <label style="color: red;">{{validInfo.name}}</label>
         </div>
        <div class="err" v-if="validInfo.emptyInput != 'OK'"> <br>
               <label style="color: red;">{{validInfo.emptyInput}}</label>
        </div>
        
    </div>
    `,
    mounted () {
       
    	 axios
		 	.get('/rest/users/getFreeManagers/')
		 	.then(response =>{
		 		this.freeManagers = response.data;
		 		console.log(this.freeManagers);
		 		})
    },
	methods : {	
		promenaFajla: function (e) {
            const file = e.target.files[0];
            this.makeBase64Image(file);
        },
        makeBase64Image: function (file) {
            const reader= new FileReader();
            reader.onload = (e) =>{
                this.imageFile = e.target.result;
            }
			reader.readAsDataURL(file);
			this.imageView = URL.createObjectURL(file);
			
        },
         
        AddNewObject: function () {
          if (this.name == '') { this.validInfo.emptyInput = 'You must enter name'; return;}
       
          if (this.longi == '') {this.validInfo.emptyInput = 'You must enter longitude'; return;}
          
          if (this.lat == '') {this.validInfo.emptyInput = 'You must enter latitude'; return;}
          
          if (this.number == '') {this.validInfo.emptyInput = 'You must enter number'; return;}
          
          if (this.street == '') {this.validInfo.emptyInput = 'You must enter street'; return;}

          if (this.city == '') {this.validInfo.emptyInput = 'You must enter city'; return;}

          if (this.zip == '') {this.validInfo.emptyInput = 'You must enter zip'; return;}

          if (this.type == '') {this.validInfo.emptyInput = 'You must choose a type'; return;}

          if (this.imageView == '') {this.validInfo.emptyInput = 'You must add a logo'; return;}

          axios.post('rest/addNewObject', { "name": this.name, "username": this.username, "type" : this.type,"street" : this.street,"number": this.number, "city":this.city,"zip":this.zip,"longi":this.longi, "lat":this.lat ,"logo" : this.logo,"imageFile": this.imageFile})
              .then(response => {
			 axios.post('/rest/users/setManagersFacility/' ,{"name":this.name, "username": this.username}) 
       		   .then(response => {
			
})
                 alert('Successfully adding!');

                })
              .catch(() => {alert('Error while adding.')});
          
         
    }
		
	},
});