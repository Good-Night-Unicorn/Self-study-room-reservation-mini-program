
<template>
<view>
<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
	<view class="content">
		<view class="container" :style='{"minHeight":"100vh","width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}'>
			<swiper :style='{"width":"100%","background":"#fff","height":"440rpx","zIndex":"0"}' class="swiper" :indicator-dots='false' :autoplay='false' :circular='false' indicator-active-color='#000000' indicator-color='rgba(0, 0, 0, .3)' :duration='500' :interval='5000' :vertical='false'>
				<swiper-item :style='{"width":"100%","background":"#fff","height":"440rpx"}' v-for="(swiper,index) in swiperList" :key="index">
					<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"440rpx"}' mode="aspectFill" v-if="swiper.substring(0,4)=='http'" :src="swiper" @tap="imgView(swiper)"></image>
					<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"440rpx"}' mode="aspectFill" v-else :src="baseUrl+swiper" @tap="imgView(baseUrl+swiper)"></image>
				</swiper-item>
			</swiper>
			<view :style='{"alignContent":"flex-start","padding":"0","margin":"0 auto","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","position":"relative","height":"auto","zIndex":"9"}' class="detail-content">

				<view :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="detail-list-item title">
					<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}' class="lable">名称：</view>
					<view :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' class="text" >{{detail.mingcheng}}</view>
				</view>
				<view :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="detail-list-item title">
					<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}' class="lable">费用：</view>
					<view :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' class="text" >{{detail.feiyong}}</view>
				</view>

				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>编号：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.refno}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>类型：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.leixing}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>座位号：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.seatnum}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>预约日期：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.reservationdate}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>时间段：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.timeslot}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>账号：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.zhanghao}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>手机：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' style="text-decoration: underline" @tap="callClick(detail.shouji)" >{{detail.shouji}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>姓名：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.xingming}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>座位状态：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.reservationstate}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>是否签到：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.shifouqiandao}}</view>
				</view>




				<view v-if="userid" class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>审核状态：</view>
					<view v-if="detail.sfsh=='是'" class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}'>通过</view>
					<view v-if="detail.sfsh=='否'" class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}'>不通过</view>
					<view v-if="detail.sfsh=='待审核'" class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}'>待审核</view>
				</view>
				<view v-if="userid" class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>审核回复</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}'>{{detail.shhf}}</view>
				</view>




				<view class="bottom-content bg-white tabbar border shop" :style='{"padding":"0 24rpx","margin":"40rpx auto","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto","order":"20"}'>

					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' @tap="onPayTap" v-if="userid&&isAuth('zuoweiyuyue','支付') && detail.ispay!='已支付'"  class="cu-btn bg-brown round shadow-blur">支付</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' @tap="onPayTap" v-if="!userid&&isAuthFront('zuoweiyuyue','支付') && detail.ispay!='已支付'"  class="cu-btn bg-brown round shadow-blur">支付</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!isAuth('zuoweiyuyue','支付') && detail.ispay!='已支付'" class="cu-btn bg-brown round shadow-blur">未支付</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="detail.ispay=='已支付'"  class="cu-btn bg-brown round shadow-blur">已支付</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zuoweiyuyue','取消预约')" @tap="onAcrossTap('quxiaoyuyue','是','是','reservationstate','已取消',reservationstateCrossSelect[0])" class="cu-btn bg-brown round shadow-blur" >取消预约</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zuoweiyuyue','取消预约')" @tap="onAcrossTap('quxiaoyuyue','是','是','reservationstate','已取消',reservationstateCrossSelect[0])" class="cu-btn bg-brown round shadow-blur" >取消预约</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zuoweiyuyue','签到')" @tap="onAcrossTap('qiandaoxinxi','是','是','shifouqiandao','已签到',shifouqiandaoCrossSelect[0])" class="cu-btn bg-brown round shadow-blur" >签到</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zuoweiyuyue','签到')" @tap="onAcrossTap('qiandaoxinxi','是','是','shifouqiandao','已签到',shifouqiandaoCrossSelect[0])" class="cu-btn bg-brown round shadow-blur" >签到</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zuoweiyuyue','审核')" @tap="onSHTap">审核</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zuoweiyuyue','审核')" @tap="onSHTap">审核</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zuoweiyuyue','私聊')" @tap="chatClick">联系TA</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zuoweiyuyue','私聊')" @tap="chatClick">联系TA</button>
					
				</view>
			</view>
		</view>
		<!-- 确认完成弹窗 -->
		<uni-popup class="popup-content" ref="popup" type="center">
			<form class="popup-form" style="background: #fff;width: 300px;display: block;border-radius: 10px;padding: 20px;text-align: center;">
				<view class=" margin-top" style="border: 1px solid #666;line-height: 30px;border-radius: 5px;margin-bottom: 10px">
					<picker @change="sfshChange" :value="sfshIndex" :range="sfshOptions">
						<view class="uni-input">{{detail.sfsh?detail.sfsh:'选择审核结果'}}</view>
					</picker>
				</view>
				<view class="" style="border: 1px solid #666;border-radius: 5px;margin-bottom: 10px;text-align: left;padding: 10px">
					<!-- <view class="title">审核回复</view> -->
					<textarea style="width: 200px;" v-model="detail.shhf" placeholder="审核回复"></textarea>
					<!-- <input v-model="douyinId" placeholder="审核回复" name="input"></input> -->
				</view>
				<view class="btn-content">
					<button style="margin: 0 10px" @tap="onCloseWinTap" class="cu-btn bg-cyan">取消</button>
					<button style="margin: 0 10px" @tap="onFinishTap" class="cu-btn bg-red">确认</button>
				</view>
			</form>
		</uni-popup>
	</view>
</mescroll-uni>
</view>
</template>

<script>
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				id: '',
				userid: '',
				detail: {},
				swiperList: [],
				commentList: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				user: {},
				sfshIndex: -1,
				sfshOptions: ['通过','不通过', '待审核'],
				reservationstateCrossSelect:'已取消,已预约'.split(','),
				shifouqiandaoCrossSelect:'已签到,待签到'.split(','),
				count: 0,
				timer: null,
				title:'',
			}
		},
		components: {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			// #ifdef APP-PLUS
			let page = getCurrentPages()
			this.href = this.baseUrl + 'front/h5/#/' + page[page.length - 1].route
			// #endif
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			this.id = options.id;
			if(options.userid) {
				this.userid = options.userid;
			}
			// 渲染数据
			this.init();
		},
		// #ifdef MP-WEIXIN
		onShareAppMessage(){
			var obj = {
				title: this.title,
				imageUrl: this.swiperList[0]?this.baseUrl + this.swiperList[0]: ''
			}
			return obj
		},
		// #endif
		onUnload() {
			if(this.timer) {
				clearInterval(this.timer);
			}
		},
		async onShow(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			let crossCleanType = uni.getStorageSync('crossCleanType')
			if(crossCleanType) {
				uni.removeStorageSync('crossCleanType')
				res = await this.$api.info('zuoweiyuyue', this.id);
				let reg=new RegExp('http://localhost:8080/ssm3g0pgi37/upload','g')//g代表全部
				this.detail = res.data;
				this.title = this.detail.mingcheng
			}
			let payType = uni.getStorageSync('payType')
			if(payType){
				uni.removeStorageSync('payType')
				this.mescroll.num = 1
				this.upCallback(this.mescroll)
				this.init();
			}
		},
		destroyed: function() {
			//window.clearInterval(this.inter);
		},
		methods: {
			imgView(url){
				let arr = []
				for(let x in this.swiperList){
					arr.push(this.swiperList[x].substr(0,4)=='http'?this.swiperList[x]:this.baseUrl + this.swiperList[x])
				}
				uni.previewImage({
					current: url,
					urls: arr
				})
			},
			// 拨打电话
			callClick(row){
				uni.makePhoneCall({
					phoneNumber: row
				})
			},
			// 支付
			onPayTap(){
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				if(this.detail.sfsh!='是') {
					this.$utils.msg("请审核通过后再操作");
					return
				}
				uni.setStorageSync('paytable','zuoweiyuyue');
				uni.setStorageSync('payObject',this.detail);
				this.$utils.jump('../pay-confirm/pay-confirm?type=1')
			},
			onDetailTap(item) {
				uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			// 跨表
			async onAcrossTap(tableName,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1){
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				if (this.detail.reservationstate=='已取消'){
					this.$utils.msg("该预约已取消，不能再操作")
					return;
				}
				if(crossOptAudit=='是'&&this.detail.sfsh!='是') {
					this.$utils.msg("请审核通过后再操作");
					return
				}
				if(crossOptPay=='是'&&this.detail.ispay!='已支付') {
					this.$utils.msg('请支付完成再操作')
					return
				}
				uni.setStorageSync('crossTable','zuoweiyuyue');
				uni.setStorageSync(`crossObj`, this.detail);
				uni.setStorageSync(`statusColumnName`, statusColumnName);
				uni.setStorageSync(`statusColumnValue`, statusColumnValue);
				uni.setStorageSync(`tips`, tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = uni.getStorageSync('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$utils.msg(tips);
							return
						}
					}
				}
				this.$utils.jump(`../${tableName}/add-or-update?cross=true`);
			},
			// 获取详情
			async init(type=1){
				if(this.timer) {
					clearInterval(this.timer);
				}
				let res = await this.$api.info('zuoweiyuyue', this.id);
				let reg=new RegExp('http://localhost:8080/ssm3g0pgi37/upload','g')//g代表全部
				this.detail = res.data;

				this.title = this.detail.mingcheng



				// 轮播图片
				this.swiperList = this.detail.tupian ? this.detail.tupian.split(",") : [];
				













				if(type==2){
					this.detail.discussnum++
					await this.$api.update('zuoweiyuyue',this.detail)
				}
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},

			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				mescroll.resetUpScroll()
			},

			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				mescroll.endSuccess(mescroll.size, this.hasNext);

			},



			onChatTap() {
				this.$utils.jump('../chat/chat')
			},
			// 下载
			download(url ){
				if(!url){
					return false
				}
				let _this = this;
				url=_this.$base.url +  url;
				uni.downloadFile({
					url: url,
					success: (res) => {
						if (res.statusCode === 200) {
							_this.$utils.msg('下载成功');
							// #ifdef H5
							 window.open(url);
							// #endif
							// #ifndef H5
							uni.saveFile({
								tempFilePath: res.tempFilePath, //临时路径
								success: function(obj) {
									uni.showToast({
										icon: 'success',
										mask: true,
										title: '下载成功' , 
										duration: 2000,
									});
									setTimeout(() => {
										console.log('obj.savedFilePath',obj.savedFilePath);
										var filePath = obj.savedFilePath;
										uni.openDocument({ //新开页面打开文档，支持格式：doc, xls, ppt, pdf, docx, xlsx, pptx。
											filePath: filePath,
											showMenu: true,
											success: function(res) {
												console.log('打开文档成功');
											}
										});
									}, 2000)
								}
							});
							// #endif
						}
					}
				});
			},
			//
			onCartTabTap() {
				this.$utils.tab('../shop-cart/shop-cart')
			},
			onSHTap() {
				if(this.detail.sfsh=='是'||this.detail.sfsh=='否') {
					this.$utils.msg('已审核完成');
					return;
				}
				this.$refs.popup.open()
			},
			// 完成审核
			async onFinishTap() {
				if(!this.detail.sfsh){
					this.$utils.msg('请选择审核状态');
					return
				}
				if(!this.detail.shhf){
					this.$utils.msg('请填写审核回复');
					return
				}
				if(this.detail.sfsh=="通过"){
					this.detail.sfsh = '是'
				}
				if(this.detail.sfsh=="不通过"){
					this.detail.sfsh = '否'
				}
				if(this.detail.sfsh=="待审核"){
					this.detail.sfsh = '待审核'
				}
				await this.$api.update('zuoweiyuyue', this.detail);
				this.$utils.msg('审核成功');
				this.$refs.popup.close();
			},
			// 关闭窗口
			onCloseWinTap() {
				this.$refs.popup.close()
			},
			sfshChange(e){
				this.sfshIndex = e.target.value
				this.detail.sfsh = this.sfshOptions[this.sfshIndex]
			},
		}
	}
</script>

<style lang="scss">
	page {
	  --animate-duration: 1s;
	  --animate-delay: 1s;
	  --animate-repeat: 1;
	}
	
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	
	.seat-list {
		display: flex;
		align-items: center;
		flex-wrap: wrap;
		background: #FFFFFF;
		margin: 20upx;
		border-radius: 20upx;
		padding: 20upx;
		font-size: 30upx;
		.seat-item {
			width: 33.33%;
			display: flex;
			align-items: center;
			flex-direction: column;
			margin-bottom: 20upx;
	
			.seat-icon {
				width: 50upx;
				height: 50upx;
				margin-bottom: 10upx;
			}
		}
	}
	
	audio {
		display: flex;
		flex-direction: column;
	}
	
	.audio .uni-audio-default {
		width: inherit !important;
	}
	

</style>
