Vue.component("addfacilities", {
	data: function () {
		   return {
			freeManagers: null,
            name: '',
            location: '',
            type: 'opt',
            logo: '',
            imageView: '',
            longi: '',
            lat: '',
            number: '',
            street: '',
            city: '',
            zip: '',
            workingHours: '',
            username: '',
            password: '',
            confirmPassword: '',
            firstname: '',
            lastname: '',
            gender: 'opt',
            dateOfBirth: '',
            validInfo: {
               name: 'OK',
               username: 'OK',
               emptyInput: 'OK'
            },
							
			}
	},
	template: `
    <div class ="registration">
         <form name="registration" id="registration" method="post">

          <table style="padding-bottom: 5px;" class="reg">
            <tr class="reg" >
              <th class="reg" style="padding: 0%;">
                Add new facility
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
            <td><input type="text" class="inputF" placeholder="Working hours" v-model="workingHours" id="wHours"></td>
            </tr>
            <tr>
                <td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="type" id="type" v-model="type">
                          <option value="opt" disabled selected hidden>Choose a type</option>
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
            	<td v-if="freeManagers.length != 0 "><select v-model="username"><option v-for="m in freeManagers">{{m.username}} </option></select></td>
                <td v-else><label style="color: red;">There is no free manager, register a new one.</label></td>
            </tr>
            <div v-if="freeManagers.length === 0 ">
            <tr class="reg">
            <th class="reg" style="padding: 0%; font-size:18px">
              Register new manager
              </th>
            </tr>
            <tr>
                <td><input type="text" class="inputF" name="username" id="username" placeholder="Username" v-model="username" autofocus></td>
            </tr>
            <tr>
                <td><input type="text" class="inputF" name="firstName" id="firstName" placeholder="First name" v-model="firstname"></td>
            </tr>
            <tr>
                <td><input type="text" class="inputF" name="lastName" id="lastName" placeholder="Last name" v-model="lastname"></td>
            </tr>
            <tr>
                <td>
                        <select  class="addNew" name="gender" id="gender" v-model="gender">
                            <option value="opt" selected disabled hidden>Gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td><input type="date" class="inputF" name="dateOfBirth" id="dateOfBirth" v-model="dateOfBirth" placeholder="Date of Birth"></td>
                </tr>
            <tr>
                <td><input type="password" class="inputF" name="password" id="password" v-model="password" placeholder="Password"></td>
            </tr>
            <tr>
                <td><input type="password" id="confirmPassword" style="margin-bottom: 20px;" class="inputF" v-model="confirmPassword" name="confirmPassword" placeholder="Confirm password"></td>
            </tr>
            <tr >
                  <td><input class="heroButtonReg" type="button" id="add" v-on:click="AddNewFacilityAndManager" value="Add facility and manager"/></td>
            </tr>
            </div>
            <tr v-if="freeManagers.length != 0">
                  <td><input class="heroButtonReg" type="button" id="addObjectButton" v-on:click="AddNewObject" value="Add new facility"/></td>
            </tr>
            
          </table>
        </form>
         <div class="err"  v-if="freeManagers.length === 0 && validInfo.name!='OK'"><br>
            <label style="color: red;">{{validInfo.name}}</label>
         </div>
        <div class="err" v-if="validInfo.emptyInput != 'OK'"> <br>
               <label style="color: red;">{{validInfo.emptyInput}}</label>
        </div>
       <div class="err" v-if="validInfo.username!='OK'"><br>
    <label style="color: red;">{{validInfo.username}}</label>
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
		 InputValidManager: function () {
            // poziv za pravljenje sa menag
            axios.post('rest/addNewObjectWithManager', { "name": this.name, "username": this.username, "type" : this.type,"street" : this.street,"number": this.number, "city":this.city,"zip":this.zip,"longi":this.longi, "lat":this.lat , "workingHours": this.workingHours,"logo" : this.logo,"imageFile": this.imageFile, "password" : this.password,"firstname" : this.firstname,"lastname" : this.lastname,"gender": this.gender,"dateOfBirth": this.dateOfBirth })
              .then(response => {
                 alert('Successfully added!');
                 router.push('/facilities');
                })
              .catch(() => {alert('Error while adding.')});   
         
        },	
        InputValid: function(){
            axios.post('rest/addNewObject', { "name": this.name, "username": this.username, "type" : this.type,"street" : this.street,"number": this.number, "city":this.city,"zip":this.zip,"longi":this.longi, "lat":this.lat,"workingHours":this.workingHours ,"logo" : this.logo,"imageFile": this.imageFile})
              .then(response => {
                 alert('Successfully added!');
                 router.push('/facilities');
                })
              .catch(() => {alert('Error while adding.')});
        },
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
           this.validInfo.username = 'OK';
            this.validInfo.emptyInput = 'OK';

            axios.post('rest/facilities/nameExists', this.name).
            then(response => {
               let notUnique = response.data;
               if (notUnique) {
                  this.validInfo.name = 'Name already exists';
                  return;
               }
               else {
                  this.InputValid();   
               }
               
 
            }).catch(function (error) { alert('Error on server')});
          
          
         
    }, 
    AddNewFacilityAndManager: function () {
        this.validInfo.username = 'OK';
        this.validInfo.emptyInput = 'OK';
        
        if (this.username == '') {
           this.validInfo.username = 'You must enter username';
           return false;
        }
       
        if (this.firstname == '') { this.validInfo.emptyInput = 'You must enter firstname'; return;}
      
        if (this.lastname == '') {this.validInfo.emptyInput = 'You must enter lastname'; return;}
        
        if (this.gender == '') {this.validInfo.emptyInput = 'You must choose gender'; return;}
        
        if (this.dateOfBirth == '') {this.validInfo.emptyInput = 'You must enter date of birth'; return;}
        
        if (this.password == '') {this.validInfo.emptyInput = 'You must enter password'; return;}
        
        if (this.password != this.confirmPassword) {this.validInfo.emptyInput = 'Passwords are not the same'; return;}

        if (this.name == '') { this.validInfo.emptyInput = 'You must enter name'; return;}
       
        if (this.longi == '') {this.validInfo.emptyInput = 'You must enter longitude'; return;}
          
        if (this.lat == '') {this.validInfo.emptyInput = 'You must enter latitude'; return;}
          
        if (this.number == '') {this.validInfo.emptyInput = 'You must enter number'; return;}
          
        if (this.street == '') {this.validInfo.emptyInput = 'You must enter street'; return;}

        if (this.city == '') {this.validInfo.emptyInput = 'You must enter city'; return;}

        if (this.zip == '') {this.validInfo.emptyInput = 'You must enter zip'; return;}

        if (this.type == '') {this.validInfo.emptyInput = 'You must choose a type'; return;}

        if (this.imageView == '') {this.validInfo.emptyInput = 'You must add a logo'; return;}
           this.validInfo.username = 'OK';
            this.validInfo.emptyInput = 'OK';

        
        axios.post('rest/usernameExists', this.username).
           then(response => {
              let notUnique = response.data;
              if (notUnique) {
                 this.validInfo.username = 'Username already exists';
                 return;
              }
              else {
                 this.InputValidManager();   
              }
              

           }).catch(function (error) { alert('Error on server')});
     }
		
	},
});