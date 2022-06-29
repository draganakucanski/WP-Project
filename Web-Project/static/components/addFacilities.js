Vue.component("addfacilities", {
	data: function () {
		   return {
			
            name: '',
            location: '',
            type: '',
            grade:'',
            status:'',
            logo: '',
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
                <td><input type="text" class="inputF" name="location" id="location" placeholder="Location" v-model="location"></td>
              </tr>
            
            <tr>
                <td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="type" id="type" v-model="type">
                          <option value="gym">Gym</option>
                          <option value="pool">Pool</option>
                          <option value="sports center">Sports Center</option>
                          <option value="dance studio">Dance Studio</option>
                        </select>
                </td>
            <tr>
           		<td><input type="number" name="grade" id="grade" placeholder="Grade" v-model="grade"></td>      
            </tr>
            
            <tr>
           		<td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="status" id="status" v-model="status">
                          <option value="gym">Open</option>
                          <option value="pool">Closed</option>
                          
                        </select>
                </td>     
            </tr>
            
             <tr>
           		<td><input type="image" name="logo" id="logo" placeholder="Logo" v-model="logo"></td>      
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
	methods : {	
         
      AddNewObject: function () {
        /* this.validInfo.username = 'OK';
         this.validInfo.emptyInput = 'OK';
         
         if (this.name == '') {
            this.validInfo.name = 'You must enter a name';
            return false;
         }
        
         if (this.location == '') { this.validInfo.emptyInput = 'You must enter a location'; return;}
       */
        axios
        	.post('/rest/facilities/addNewObject/',{"name":this.name, "location":this.location, "type": this.type, "grade": this.grade, "status": this.status, "logo": this.logo})
        	.then(response => {
				alert('Succesfully added!');
			})
			.catch(() => {alert('Error while adding new object.')});		
         	
        /* axios.post('rest/usernameExists', this.username).
            then(response => {
               let notUnique = response.data;
               if (notUnique) {
                  this.validInfo.username = 'Username already exists';
                  return;
               }
               else {
                  this.InputValid();   
               }
               

            }).catch(function (error) { alert('Error on server')});*/
      }
		
	},
});