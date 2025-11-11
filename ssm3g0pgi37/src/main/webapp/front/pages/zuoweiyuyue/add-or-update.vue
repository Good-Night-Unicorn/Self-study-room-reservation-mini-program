<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"48rpx 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">编号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.refno" v-model="ruleForm.refno" placeholder="编号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">名称</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.mingcheng" v-model="ruleForm.mingcheng" placeholder="名称"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">类型</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.leixing" v-model="ruleForm.leixing" placeholder="类型"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">费用</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.feiyong" v-model.number="ruleForm.feiyong" placeholder="费用" type="number"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">座位号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.seatnum" v-model="ruleForm.seatnum" placeholder="座位号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">预约日期</view>
				<picker :start="reservationdatestarttime" :disabled="ro.reservationdate" :style='{"width":"100%","flex":"1","height":"auto"}' mode="date" :value="ruleForm.reservationdate" @change="reservationdateChange">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#174add"}' class="uni-input">{{ruleForm.reservationdate?ruleForm.reservationdate:"请选择预约日期"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">时间段</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.timeslot" v-model="ruleForm.timeslot" placeholder="时间段"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">账号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.zhanghao" v-model="ruleForm.zhanghao" placeholder="账号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">手机</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.shouji" v-model="ruleForm.shouji" placeholder="手机"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">姓名</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.xingming" v-model="ruleForm.xingming" placeholder="姓名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">座位状态</view>
				<picker :disabled="ro.reservationstate" :style='{"width":"100%","flex":"1","height":"auto"}' @change="reservationstateChange" :value="reservationstateIndex" :range="reservationstateOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#174add"}' class="uni-input">{{ruleForm.reservationstate?ruleForm.reservationstate:"请选择座位状态"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 12rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">是否签到</view>
				<picker :disabled="ro.shifouqiandao" :style='{"width":"100%","flex":"1","height":"auto"}' @change="shifouqiandaoChange" :value="shifouqiandaoIndex" :range="shifouqiandaoOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#174add"}' class="uni-input">{{ruleForm.shifouqiandao?ruleForm.shifouqiandao:"请选择是否签到"}}</view>
				</picker>
			</view>
                
			
			<view :style='{"width":"100%","margin":"40rpx 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 0 40rpx 0","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#174add","width":"100%","lineHeight":"96rpx","fontSize":"30rpx","height":"96rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				refno: '',
				mingcheng: '',
				leixing: '',
				tupian: '',
				feiyong: '',
				seatnum: '',
				reservationdate: '',
				timeslot: '',
				zhanghao: '',
				shouji: '',
				xingming: '',
					reservationstate: '已预定' ,
					shifouqiandao: '待签到' ,
				shhf: '',
				ispay: '',
				},
				reservationdatestarttime: '',
				reservationstateOptions: [],
				reservationstateIndex: 0,
				shifouqiandaoOptions: [],
				shifouqiandaoIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   refno : false,
				   mingcheng : false,
				   leixing : false,
				   tupian : false,
				   feiyong : false,
				   seatnum : false,
				   reservationdate : false,
				   timeslot : false,
				   zhanghao : false,
				   shouji : false,
				   xingming : false,
				   reservationstate : false,
				   shifouqiandao : false,
				   sfsh : false,
				   shhf : false,
				   ispay : false,
				},
				virtualPay: false,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			this.reservationdatestarttime = this.$utils.getCurDate()
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取
			this.ruleForm.zhanghao = this.user.zhanghao
			this.ro.zhanghao = true;
			this.ruleForm.shouji = this.user.shouji
			this.ro.shouji = true;
			this.ruleForm.xingming = this.user.xingming
			this.ro.xingming = true;

			this.ro.shifouqiandao = true;

			// 自定义下拉框值
			this.reservationstateOptions = "已取消,已预约".split(',')
			// 自定义下拉框值
			this.shifouqiandaoOptions = "已签到,待签到".split(',')

			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`zuoweiyuyue`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='refno'){
						this.ruleForm.refno = obj[o];
						this.ro.refno = true;
						continue;
					}
					if(o=='mingcheng'){
						this.ruleForm.mingcheng = obj[o];
						this.ro.mingcheng = true;
						continue;
					}
					if(o=='leixing'){
						this.ruleForm.leixing = obj[o];
						this.ro.leixing = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='feiyong'){
						this.ruleForm.feiyong = obj[o];
						this.ro.feiyong = true;
						continue;
					}
					if(o=='seatnum'){
						this.ruleForm.seatnum = obj[o];
						this.ro.seatnum = true;
						continue;
					}
					if(o=='reservationdate'){
						this.ruleForm.reservationdate = obj[o];
						this.ro.reservationdate = true;
						continue;
					}
					if(o=='timeslot'){
						this.ruleForm.timeslot = obj[o];
						this.ro.timeslot = true;
						continue;
					}
					if(o=='zhanghao'){
						this.ruleForm.zhanghao = obj[o];
						this.ro.zhanghao = true;
						continue;
					}
					if(o=='shouji'){
						this.ruleForm.shouji = obj[o];
						this.ro.shouji = true;
						continue;
					}
					if(o=='xingming'){
						this.ruleForm.xingming = obj[o];
						this.ro.xingming = true;
						continue;
					}
					if(o=='reservationstate'){
						this.ruleForm.reservationstate = obj[o];
						this.ro.reservationstate = true;
						continue;
					}
					if(o=='shifouqiandao'){
						this.ruleForm.shifouqiandao = obj[o];
						this.ro.shifouqiandao = true;
						continue;
					}
				}
			}
			this.styleChange()
			this.$forceUpdate()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap()
				}, 300)
			}
		},
		methods: {
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数

			reservationdateChange(e) {
				this.ruleForm.reservationdate = e.target.value;
				this.$forceUpdate();
			},


			// 下拉变化
			reservationstateChange(e) {
				this.reservationstateIndex = e.target.value
				this.ruleForm.reservationstate = this.reservationstateOptions[this.reservationstateIndex]
			},
			// 下拉变化
			shifouqiandaoChange(e) {
				this.shifouqiandaoIndex = e.target.value
				this.ruleForm.shifouqiandao = this.shifouqiandaoOptions[this.shifouqiandaoIndex]
			},

			tupianTap() {
				let _this = this;
				if(this.ro.tupian){
					return false
				}
				this.$api.upload(function(res) {
					_this.ruleForm.tupian = 'upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(()=>{
						_this.styleChange()
					})
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap() {
				let that = this
				if(!this.ruleForm.id) {
					this.ruleForm.ispay = '未支付'
				}
				//跨表计算判断
				var obj;
				if(this.ruleForm.feiyong&&(!this.$validate.isIntNumer(this.ruleForm.feiyong))){
					this.$utils.msg(`费用应输入整数`);
					return
				}
				if(this.ruleForm.shouji&&(!this.$validate.isMobile(this.ruleForm.shouji))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				if((!this.ruleForm.shifouqiandao)){
					this.$utils.msg(`是否签到不能为空`);
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}

							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
							   crossuserid=Number(uni.getStorageSync('appUserid'));
							   crossrefid=obj['id'];
							   crossoptnum=uni.getStorageSync('statusColumnName');
							   crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
				}
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid=crossuserid;
					this.ruleForm.crossrefid=crossrefid;
					let params = {
						page: 1,
						limit:10,
						crossuserid:crossuserid,
						crossrefid:crossrefid,
					}
					let res = await this.$api.list(`zuoweiyuyue`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`zuoweiyuyue`, this.ruleForm);
						}else{
							oet = await this.$api.add(`zuoweiyuyue`, this.ruleForm);
						}
						if(this.cross){
							uni.setStorageSync('crossCleanType',true);
						}
						this.$utils.msgBack('提交成功');
					}
				} else {
				//跨表计算
					let oet = {}
					if(this.ruleForm.id){
						await this.$api.update(`zuoweiyuyue`, this.ruleForm);
					}else{
						oet = await this.$api.add(`zuoweiyuyue`, this.ruleForm);
					}
					if(this.cross){
						uni.setStorageSync('crossCleanType',true);
					}
					this.$utils.msgBack('提交成功');
				}
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
