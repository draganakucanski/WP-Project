const Mainpage = { template: '<mainpage></mainpage>' }
const Registration = { template: '<registration></registration>' }
const Product = { template: '<edit-user></edit-user>' }
const Products = { template: '<products></products>' }
const Facilities = {template: '<facilities></facilities>'}
const Login = {template: '<login></login>'}
const Frontpage= {template: '<frontpage></frontpage>'}
const Editpage = {template: '<editpage></editpage>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
	  	{ path: '/', component: Mainpage },
		{path:'/login',component: Login},
		{path:'/registration',component: Registration},
		{path: '/facilities',component: Facilities},
		{path: '/frontPage',component: Frontpage},
		{path: '/editPage',component: Editpage},
	  ]
});

var app = new Vue({
	router,
	el: '#vue-app'
});