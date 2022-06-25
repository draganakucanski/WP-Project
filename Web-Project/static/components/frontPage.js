Vue.component("frontpage", {
	data: function () {
		    return {
		      logedInUser: null
		    }
	},
	template: ` 
<div>
	 <section class="header">
	 	<nav>
          <div class="nav-links">
                <ul>
                    <li><a >SOMETHING1</a></li>
                    <li><a >SOMETHING2</a></li>
                    <li><a @click="redirectOnFacilities">Our Facilities</a>
                    <li><a @click="redirectOnMain">Log Out</a></li>
                </ul>
            </div>
        </nav>

        <div class="text-box">
            <h1>GymPass</h1>
            
  
        </div>
		<script >
		
		</script>
    </section>
</div>		  
`
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
		
		redirectOnFacilities: function(){
			router.push('/facilities');
		}
	},
		//kada god zelim koristiti podatke ulogovanog korisnika
		//kopiram ovu mounted funkciju
		//i definisem gore u data: logedInUser: null
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
	}
				
})