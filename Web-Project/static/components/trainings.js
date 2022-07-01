Vue.component("trainings", {
	data: function () {
		    return {
			  logedInUser: null,
              usersHistories:null,
		      searchParam: {
				name: "",
				priceStart: "",
				priceTo: "",
				dateFrom: "",
                dateTo: ""
			},
			sortType: "",
			filterType: '',
			filterTType: ''
		    }
	},
	template: ` 
<div class="trainings">
	<h2 class="list">History of trainings:</h2>
    <label class="sort">Sort by:</label>
			   <select name="sort" v-on:change="sort" v-model="sortType">
				<option v-if="logedInUser.role != 'manager'" value="NameAsc">Name A-Z</option>
				<option v-if="logedInUser.role != 'manager'" value="NameDisc">Name Z-A</option>
				<option value="DateAsc">Date Asc</option>
				<option value="DateDisc">Date Disc</option>
			  </select>
              <label v-if="logedInUser.role != 'manager'" class="filterT">Facility type:</label>
			   <select v-if="logedInUser.role != 'manager'" name="filter" v-on:change="FilterType" v-model="filterType">
			    <option value="all">All types</option>
				<option value="GYM">Gyms</option>
				<option value="POOL">Pools</option>
				<option value="DANCESTUDIO">Dance studios</option>
				<option value="SPORTSCENTER">Sports centers</option>
			  </select>
			  <label class="filterW">Training type:</label>
			   <select name="filter" v-on:change="FilterTType" v-model="filterTType">
			    <option value="all">All types</option>
				<option value="group">Group</option>
				<option value="personal">Personal</option>
                <option value="gym">Gym</option>
			  </select>
			<br>
			<br>
	<table v-if="logedInUser.role === 'customer'" class="customTable">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Sports Facility</th>
		<th>Date and time</th>
	</tr>
    
    <tr v-for="u in usersHistories">
        <td>{{u.training.name}}</td>
        <td>{{u.training.sportsFacility.name}}</td>
        <td>{{u.dateTime.date.day}}.{{u.dateTime.date.month}}.{{u.dateTime.date.year}} - {{u.dateTime.time.hour}}:{{u.dateTime.time.minute}}</td>
    </tr>
    </table>
    <table v-if="logedInUser.role === 'trainer'" class="customTable">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Sports Facility</th>
		<th>Date and time</th>
        <th>Type</th>
	</tr>
    
    <tr v-for="u in usersHistories">
        <td>{{u.training.name}}</td>
        <td>{{u.training.sportsFacility.name}}</td>
        <td>{{u.dateTime.date.day}}.{{u.dateTime.date.month}}.{{u.dateTime.date.year}} - {{u.dateTime.time.hour}}:{{u.dateTime.time.minute}}</td>
        <td>{{u.training.type}}</td>
    </tr>
    </table>
    <table v-if="logedInUser.role === 'manager'" class="customTable">
	<tr bgcolor="lightgrey">
		<th>Name</th>
		<th>Date and time</th>
	</tr>
    
    <tr v-for="u in usersHistories">
        <td>{{u.training.name}}</td>
        <td>{{u.dateTime.date.day}}.{{u.dateTime.date.month}}.{{u.dateTime.date.year}} - {{u.dateTime.time.hour}}:{{u.dateTime.time.minute}}</td>
    </tr>
    </table>
    <div class="search">
		  <h2 class="search">Search</h2>
			<table>
			  <tr v-if="logedInUser.role != 'manager'" ><td><input type="text" placeholder="Facility name" v-model="searchParam.name"></td></tr>
			  <tr><td><input type="text" placeholder="Price from" v-model="searchParam.priceStrat"></td></tr>
              <tr><td><input type="text" placeholder="Price to" v-model="searchParam.priceTo"></td></tr>
			  <tr><td><input type="date" placeholder="Date from" v-model="searchParam.dateFrom"></td></tr>
              <tr><td><input type="date" placeholder="Date to" v-model="searchParam.dateTo"></td></tr>
			  <tr><td><button class="search" v-on:click="TrainingSearch">Search</button></td></tr>
			</table>
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
            );
            axios.get('rest/trainings/getUsersHistories')
            .then(response => (this.usersHistories = response.data)).catch(function (error) {
                alert('Error on server');
            });	
          },
    
	methods: {
        TrainingSearch: function () {
            axios.get('rest/trainings/getTrainingSearch', { params: {
                 name: this.searchParam.name,
                 priceStart: this.searchParam.priceStart,
                 priceTo: this.searchParam.priceTo,
                 dateFrom: this.searchParam.dateFrom,
                 dateTo: this.searchParam.dateTo
            } })
                .then(response => (this.usersHistories = response.data)).catch(function (error) {
                    alert('Error on server');
                });	

        },
        sort: function () {
			if (this.sortType == 'NameAsc') {
				this.usersHistories.sort((a, b) => (a.training.name > b.training.name) ? 1 : ((b.training.name > a.training.name) ? -1 : 0));
			} else if (this.sortType == 'NameDisc') {
				this.usersHistories.sort((b, a) => (a.training.name > b.training.name) ? 1 : ((b.training.name > a.training.name) ? -1 : 0));
            }else if (this.sortType == 'DateAsc') {
                axios.get('rest/trainings/getDateAscHistories')
                .then(response => (this.usersHistories = response.data)).catch(function (error) {
                    alert('Error on server');
                });	
			}else if (this.sortType == 'DateDisc') {
                axios.get('rest/trainings/getDateDiscHistories')
                .then(response => (this.usersHistories = response.data)).catch(function (error) {
                    alert('Error on server');
                });	
			} 
		},
        FilterType: function () {
            if(this.filterType=='GYM'){
                    axios.get('rest/trainings/getGyms')
                        .then(response => (this.usersHistories = response.data)).catch(function (error) {
                            alert('Error on server');
                        });	
                    }
            else if(this.filterType=='POOL'){
                    axios.get('rest/trainings/getPools')
                        .then(response => (this.usersHistories = response.data)).catch(function (error) {
                            alert('Error on server');
                        });	
                    }
            else if(this.filterType=='DANCESTUDIO'){
                    axios.get('rest/trainings/getDanceS')
                        .then(response => (this.usersHistories = response.data)).catch(function (error) {
                            alert('Error on server');
                        });	
                    }
            else if(this.filterType=='SPORTSCENTER'){
                    axios.get('rest/trainings/getSportsC')
                        .then(response => (this.usersHistories = response.data)).catch(function (error) {
                            alert('Error on server');
                        });	
                    }
            else if(this.filterType=='all'){
                    axios.get('rest/trainings/getUsersHistories')
                        .then(response => (this.usersHistories = response.data)).catch(function (error) {
                            alert('Error on server');
                        });	
                    }
        
            },
            FilterTType: function () {
                if(this.filterTType=='gym'){
                        axios.get('rest/trainings/getGymsT')
                            .then(response => (this.usersHistories = response.data)).catch(function (error) {
                                alert('Error on server');
                            });	
                        }
                else if(this.filterTType=='personal'){
                        axios.get('rest/trainings/getPersonal')
                            .then(response => (this.usersHistories = response.data)).catch(function (error) {
                                alert('Error on server');
                            });	
                        }
                else if(this.filterTType=='group'){
                        axios.get('rest/trainings/getGroup')
                            .then(response => (this.usersHistories = response.data)).catch(function (error) {
                                alert('Error on server');
                            });	
                        }
                else if(this.filterTType=='all'){
                        axios.get('rest/trainings/getUsersHistories')
                            .then(response => (this.usersHistories = response.data)).catch(function (error) {
                                alert('Error on server');
                            });	
                        }
            
                    },
			
		}
   });