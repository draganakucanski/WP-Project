Vue.component("addManagerTrainer", {
	data: function () {
        return {
            username: '',
            password: '',
            confirmPassword: '',
            firstname: '',
            lastname: '',
            gender: 'opt',
            dateOfBirth: '',		
            type: 'opt',
            validInfo: {
               username: 'OK',
               emptyInput: 'OK'
            }
        }
	},
	template: ` 
<div class="addManagerTrainer">
    <h2 class="list"></h2>
    <form name="registration" id="registration" method="post">

    <table style="padding-bottom: 5px;" class="reg">
    <tr class="reg">
        <td class="reg"><div class="reg" style="text-align: center;">Register new manager/trainer.</div><br></td>
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
        <td>
                <select  class="addNew"  name="type" id="type" v-model="type">
                    <option value="opt" selected disabled hidden>Type</option>
                    <option value="manager">Manager</option>
                    <option value="trainer">Trainer</option>
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
    
    <tr>
            <td><input class="heroButtonReg" type="button" id="registrationButton" v-on:click="Registration" value="Create account"/></td>
    </tr>
    
    </table>
    </form>
    <div class="err" v-if="validInfo.username!='OK'"><br>
    <label style="color: red;">{{validInfo.username}}</label>
    </div>
    <div class="err" v-if="validInfo.emptyInput != 'OK'"> <br>
        <label style="color: red;">{{validInfo.emptyInput}}</label>
    </div>
</div>	
`
,

	
	mounted () {
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
        InputValid: function () {
            axios.post('rest/addManagerTrainer', { "username": this.username, "password" : this.password,"firstname" : this.firstname,"lastname" : this.lastname,"gender": this.gender,"dateOfBirth": this.dateOfBirth, "type": this.type })
                .then(response => {
                   alert('Successfully added!');
                   router.push('/userslist');
                  })
                .catch(() => {alert('Error while adding.')});
                
         
      },
		Registration: function () {
            this.validInfo.username = 'OK';
            this.validInfo.emptyInput = 'OK';
            
            if (this.username == '') {
               this.validInfo.username = 'You must enter username';
               return false;
            }
           
            if (this.firstname == '') { this.validInfo.emptyInput = 'You must enter firstname'; return;}
          
            if (this.lastname == '') {this.validInfo.emptyInput = 'You must enter lastname'; return;}
            
            if (this.gender == '') {this.validInfo.emptyInput = 'You must choose gender'; return;}

            if (this.type == 'opt') {this.validInfo.emptyInput = 'You must choose type'; return;}
            
            if (this.dateOfBirth == '') {this.validInfo.emptyInput = 'You must enter date of birth'; return;}
            
            if (this.password == '') {this.validInfo.emptyInput = 'You must enter password'; return;}
            
            if (this.password != this.confirmPassword) {this.validInfo.emptyInput = 'Passwords are not the same'; return;}
   
            
            axios.post('rest/usernameExists', this.username).
               then(response => {
                  let notUnique = response.data;
                  if (notUnique) {
                     this.validInfo.username = 'Username already exists';
                     return;
                  }
                  else {
                     this.InputValid();   
                  }
                  
   
               }).catch(function (error) { alert('Error on server')});
               
         }	
			
		}
   });