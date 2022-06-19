const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const Product = { template: '<edit-user></edit-user>' }
const Products = { template: '<products></products>' }
const Facilities = {template: '<facilities></facilities>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	  	{ path: '/', component: Users },
		{ path: '/login', component: Login },
		{ path: '/registration', component: Registration },
	    { path: '/users/:id', component: User},
	    {path: '/facitilies',component: Facilities}
	  ]
});

var app = new Vue({
	router,
	el: '#users'
});