Vue.component("mainpage", {
	data: function () {
		    return {
		      
		    }
	},
	template: ` 
<div>
	 <section class="header">
	 	<nav>
          <div class="nav-links">
                <ul>
                    <li><a href="index.html">Home</a></li>
                    <li><a href="">About us</a></li>
                    <li><a href="">Contact</a></li>
                    <li><a @click="redirectOnFacilities">Our Facilities</a></li>
                </ul>
            </div>
        </nav>

        <div class="text-box">
            <h1>GymPass</h1>
            <p>Train at best gyms, with only one membership.<br> Join us today!</p>
            <div>
            <fieldset style="border: 0px;"> <button class="hero-btn" type="button" v-on:click="redirectOnLogin">Log in</button></fieldset>
            <fieldset style="border: 0px;"><button class="hero-btn" type="button" v-on:click="redirectOnRegistration">Sign up</button></fieldset>
            </div>
        </div>
		<script >
		
		</script>
    </section>
</div>		  
`
	,
	methods : {
		redirectOnLogin : function(){
			router.push('/login');	
		
			  					},
		redirectOnRegistration: function(){
			router.push('/registration');
		},
		redirectOnFacilities: function(){
			router.push('/facilities');
		}
				},
	 	
				
})