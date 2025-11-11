import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import leixing from '@/views/modules/leixing/list'
	import news from '@/views/modules/news/list'
	import renlingxinxi from '@/views/modules/renlingxinxi/list'
	import zixishi from '@/views/modules/zixishi/list'
	import menu from '@/views/modules/menu/list'
	import storeup from '@/views/modules/storeup/list'
	import zuoweiyuyue from '@/views/modules/zuoweiyuyue/list'
	import shiwuzhaoling from '@/views/modules/shiwuzhaoling/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import discusszixishi from '@/views/modules/discusszixishi/list'
	import quxiaoyuyue from '@/views/modules/quxiaoyuyue/list'
	import yonghu from '@/views/modules/yonghu/list'
	import chat from '@/views/modules/chat/list'
	import forumreport from '@/views/modules/forumreport/list'
	import qiandaoxinxi from '@/views/modules/qiandaoxinxi/list'
	import config from '@/views/modules/config/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/leixing',
		name: '类型',
		component: leixing
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/renlingxinxi',
		name: '认领信息',
		component: renlingxinxi
	}
	,{
		path: '/zixishi',
		name: '自习室',
		component: zixishi
	}
	,{
		path: '/menu',
		name: '菜单列表',
		component: menu
	}
	,{
		path: '/storeup',
		name: '我的收藏',
		component: storeup
	}
	,{
		path: '/zuoweiyuyue',
		name: '座位预约',
		component: zuoweiyuyue
	}
	,{
		path: '/shiwuzhaoling',
		name: '失物招领',
		component: shiwuzhaoling
	}
	,{
		path: '/forum',
		name: '论坛管理',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/discusszixishi',
		name: '自习室评论',
		component: discusszixishi
	}
	,{
		path: '/quxiaoyuyue',
		name: '取消预约',
		component: quxiaoyuyue
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/chat',
		name: '在线咨询',
		component: chat
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/qiandaoxinxi',
		name: '签到信息',
		component: qiandaoxinxi
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
