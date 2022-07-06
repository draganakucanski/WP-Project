Vue.component("addContent", {
	data: function () {
		   return {
			trainers: null,
            name: '',
            duration: '',
            type: 'opt',
            logo: '',
            imageView: '',
            description: '',
            username:'',
            validInfo: {
               name: 'OK',
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
            <td class="reg"><div class="reg" style="text-align: center;">Add new training.</div><br></td>
            </tr>
            <tr>
              <td><input type="text" class="inputF" name="name" id="name" placeholder="Name" v-model="name" autofocus></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Duration in hours" v-model="duration" id="duration"></td>
            </tr>
            <tr>
            <td><input type="text" class="inputF" placeholder="Description" name="description" id="description" v-model="description"></td>
            </tr>
            <tr>
                <td class="reg">
                        <select style="width: 236px; background-color: white; border-color: white; color:rgb(169, 169, 169)" name="type" id="type" v-model="type">
                          <option value="opt" disabled selected hidden>Choose a type</option>
                          <option value="group">Group</option>
                          <option value="personal">Personal</option>
                          <option value="gym">Gym</option>
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
            	<td>Select trainer: <select v-model="username"><option v-for="t in trainers">{{t.username}} </option></select></td>
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
		 	.get('rest/users/getTrainers/')
		 	.then(response =>{
		 		this.trainers = response.data;
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
        InputValid: function () {
            axios.post('rest/trainings/addContent', { "name": this.name, "type" : this.type, "duration" : this.duration,"description" : this.description,"trainer": this.username,"logo" : this.logo,"imageFile": this.imageFile })
                .then(response => {
                   alert('Successfully added!');
                   router.push('/managersFacility');
                  })
                .catch(() => {alert('Error while adding.')});
                
         
      },
         
        AddNewObject: function () {
          if (this.name == '') { this.validInfo.emptyInput = 'You must enter name'; return;}

          if (this.type == '') {this.validInfo.emptyInput = 'You must choose a type'; return;}

          if (this.imageView == '') {this.validInfo.emptyInput = 'You must add a logo'; return;}

          axios.post('rest/trainings/NameExists', this.name).
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
          
         
    }
		
	},
});