Vue.component("registration", {
	data: function () {
		   return {
            username: '',
            password: '',
            confirmPassword: '',
            firstname: '',
            lastname: '',
            gender: '',
            dateOfBirth: '',		
            type: "CUSTOMER",
            validInfo: {
               username: 'OK',
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
            <tr class="reg">
                <td class="reg"><div class="reg" style="text-align: center;">Sign up to join our team.</div></td>
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
                <td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="gender" id="gender" v-model="gender">
                          <option value="opt">Gender</option>
                          <option value="male">MALE</option>
                          <option value="female">FEMALE</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td><input type="text" class="inputF" name="dateOfBirth" id="dateOfBirth" v-model="dateOfBirth" placeholder="Date of Birth"></td>
              </tr>
            <tr>
                <td><input type="password" class="inputF" name="password" id="password" v-model="password" placeholder="Password"></td>
            </tr>
            <tr>
                <td><input type="password" id="confirmPassword" style="margin-bottom: 20px;" class="inputF" v-model="confirmPassword" name="confirmPassword" placeholder="Confirm password"></td>
            </tr>
            
            <tr>
                  <td><input class="heroButtonReg" type="button" id="registrationButton" v-on:click="Registration" value="Sign Up"/></td>
            </tr>
            
          </table>
        </form>
         <div class="err" v-if="validInfo.username!='OK'"><br>
            <label style="color: red;">{{validInfo.username}}</label>
         </div>
        <div class="err" v-if="validInfo.emptyInput != 'OK'"> <br>
               <label style="color: red;">{{validInfo.emptyInput}}</label>
        </div>
        <form name="login">
            <p class="reg">Have an account? <a class="reg" href="index.html"><b>Log In</b></a></p>
        </form>
    </div>
    `,
	methods : {	
         InputValid: function () {
        	alert('U reg smo.');
            axios.post('rest/registration', { "username": this.username, "password" : this.password,"firstname" : this.firstname,"lastname" : this.lastname,"gender": this.gender,"dateOfBirth": this.dateOfBirth })
                .then(response => {
                   alert('Successfully registrated!');

                  })
                .catch(() => {alert('Error while registration.')});
                
         
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
		
	},
});