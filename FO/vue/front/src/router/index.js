import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Layout from '@/components/Layout'
import FindId from '@/components/FindId'
import FindPassword from '@/components/FindPassword'
import MyPage from '@/components/MyPage'
import MyPagePassword from '@/components/MyPagePassword'
import DeleteUser from '@/components/DeleteUser'
import ChangePassword from '@/components/ChangePassword'
import ScheduleSelect from '@/components/ScheduleSelect'
import CheckOut from '@/components/CheckOut'
import ReservationCheck from '@/components/ReservationCheck'
import ScheduleSelect2 from '@/components/ScheduleSelect2'
import CheckOut2 from '@/components/CheckOut2'
import MyReservation from '@/components/MyReservation'
import QnA from '@/components/QnA'
import MyQnA from '@/components/MyQnA'
import QnADetail from '@/components/QnADetail'
import DeleteUserPassword from '@/components/DeleteUserPassword'

/* eslint-disable */
Vue.use(Router)

export default new Router({
        routes: [{
            path: '/',
            component: Layout,
            children: [{
                    path: '/',
                    component: Home,
                    name: 'Home',

                },
                {
                    path: '/login',
                    component: Login,
                    name: 'Login'
                }, {
                    path: '/signup',
                    component: SignUp,
                    name: 'SignUp'
                },
                {
                    path: '/FindId',
                    component: FindId,
                    name: 'FindId'
                },
                {
                    path: '/findPassword',
                    component: FindPassword,
                    name: 'FindPassword'
                },
                {
                    path:'/mypage',
                    component:MyPage,
                    name:'MyPage'
                },
                {
                    path:'/mypagepassword',
                    component:MyPagePassword,
                    name:'MyPagePassword'
                },
                {
                    path:'/deleteuser',
                    component:DeleteUser,
                    name:'DeleteUser'
                },
                {
                    path:'/changepassword',
                    component:ChangePassword,
                    name:'ChangePassword'
                },
                {
                    path:'/scheduleselect',
                    component:ScheduleSelect,
                    name:'ScheduleSelect',
                    props:true
                },
                {
                    path:'checkout',
                    component: CheckOut,
                    name:'CheckOut'
                },
                {
                    path:'reservationcheck',
                    component: ReservationCheck,
                    name:'ReservationCheck'
                },
                {
                    path:'/scheduleselect2',
                    component: ScheduleSelect2,
                    name:'ScheduleSelect2'
                },
                {
                    path:'/checkout2',
                    component: CheckOut2,
                    name:'CheckOut2'
                },
                {
                    path:'/myreservation',
                    component: MyReservation,
                    name:'MyReservation'
                },
                {
                    path:'/qna',
                    component: QnA,
                    name:'QnA'
                },
                {
                    path:'/myqna',
                    component: MyQnA,
                    name:'MyQnA'
                },
                {
                    path:'/qnadetail',
                    component: QnADetail,
                    name:'QnADetail'
                },
                {
                    path:'/deleteuserpassword',
                    component: DeleteUserPassword,
                    name:'DeleteUserPassword'
                },
            ]
        }],
        mode: 'history'
    },

)
