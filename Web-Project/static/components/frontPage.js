Vue.component("frontpage", {
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
			router.push('/');	
		
			  					},
		redirectOnFacilities: function(){
			router.push('/facilities');
		}
				},
	 	
				
})