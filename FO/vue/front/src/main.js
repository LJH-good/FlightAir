// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import vuetify from '@/plugins/vuetify' // path to vuetify export
import axios from 'axios'
import store from './store'
import VueSession from 'vue-session'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueSession)
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

// import VueSession from 'vue-session'
// var sessionOptions = {
//   persist: true
// }
// Vue.use(VueSession, sessionOptions)

Vue.prototype.$axios = axios
Vue.config.productionTip = false

/* eslint-disable */
new Vue({
    el: '#app',
    vuetify,
    router,
    store,
    components: { App },
    template: '<App/>',  
})