const Mainpage = { template: '<mainpage></mainpage>' }
const Registration = { template: '<registration></registration>' }
const Product = { template: '<edit-user></edit-user>' }
const Products = { template: '<products></products>' }
const Facilities = {template: '<facilities></facilities>'}
const Login = {template: '<login></login>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	  	{ path: '/', component: Mainpage },
		{path:'/login',component: Login},
		{path:'/facilities',component: Facilities}
	  ]
});

var app = new Vue({
	router,
	el: '#vue-app'
});