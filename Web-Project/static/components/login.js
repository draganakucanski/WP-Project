Vue.component("login", {
    data: function () {
            return {
            message: "test",
            role: "",
            login: {
                username: "",
                password: "",
            }
          }
    },
    template: ` 
<div class="login">
    <form class="login">
        <table>
            <tr>
                <td>
                    <p class="loginP">Username: </p>
                    <input type="text" v-model = "login.username"></input>
                </td>
            </tr>
            <tr>
                <td>
                    <p class="loginP">Password: </p>
                    <input type="password" v-model = "login.password"></input>
                </td>
            </tr>
            
            <tr>
                <td colspan="2" style="text-align:center">
                    <input type="submit" value="Login" v-on:click = "loginConfirm"></input>
                </td>
            </tr>
        </table>
    </form>
</div>
`
    , 
    methods : {
        loginConfirm : function() {
           	event.preventDefault();
            axios.get("/rest/users/login/", {params: { username: this.login.username, password: this.login.password }})
            .then(response => {
                console.log(response);
                /*this.role = response.data.role;
                router.push(`/${this.role}`);*/
                localStorage.setItem('logedInUser', response.data);
                if(response.data.password != ""){
				router.push('/frontpage');
				} 
            }).catch(error => {
                console.log(error.response)
            });
            
            
        }
    },
    mounted () {
        /*this.id = this.$route.params.id;
        if (this.id != -1){
            axios
              .get('rest/products/' + this.id)
              .then(response => (this.product = response.data))
        }*/
    },
   
});