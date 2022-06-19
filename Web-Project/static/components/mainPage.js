Vue.component("mainpage", {
	data: function () {
		    return {
		      
		    }
	},
	template: ` 
<div>
	 <section class="header">
        

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
			axios.get("rest/user/sign/")
		}
				},
	 	
				
})