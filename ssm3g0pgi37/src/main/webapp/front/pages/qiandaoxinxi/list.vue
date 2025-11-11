<template>
<!-- category 1 -->
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<view class="content">
			<view :style='{"minHeight":"100vh","width":"100%","padding":"0 20rpx 160rpx","position":"relative","background":"#fff","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"calc(100% - 90rpx)","padding":"20rpx 0","background":"none","display":"flex","height":"auto"}'>
					<view :style='{"margin":"0 12rpx","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"#174add","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #333" :style='{"border":"2rpx solid #174add","padding":"12rpx 20rpx 12rpx 80rpx","color":"#333","borderRadius":"60rpx","background":"#ffffff","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.refno" type="text" placeholder="编号" ></input>
					</view>
					<button :style='{"border":"0","padding":"0px","margin":"0","color":"#fff","borderRadius":"60rpx","background":"#174add","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"padding":"0px","margin":"40rpx auto","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"center"}'>
					<view @click="sortClick('addtime')" :style='{"border":"0","padding":"0 20rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"40rpx","background":"#fff","display":"flex"}'>
						<text :style='{"color":"inherit","lineHeight":"60rpx","fontSize":"inherit"}'>按日期</text>
						<text v-if="listSort!='addtime'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"60rpx","fontSize":"inherit","color":"inherit"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='asc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"60rpx","fontSize":"inherit","color":"inherit"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='desc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"60rpx","fontSize":"inherit","color":"inherit"}'></text>
					</view>
				</view>
				<view :style='{"alignContent":"flex-start","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto"}'>
					<!-- 样式2 -->
					<view class="list" :style='{"width":"100%","padding":"0","margin":"40rpx 0 0","height":"auto"}'>
						<view @tap="onDetailTap(product)" class="listm flex flex-between" :style='{"padding":"20rpx","margin":"0 0 20rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#f0f8ff","display":"flex","width":"100%","height":"auto"}' v-for="(product,index) in list" :key="index">
							<image :style='{"border":"4rpx solid #174add","width":"160rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-if="preHttp(product.tupian)" :src="product.tupian.split(',')[0]"></image>
							<image :style='{"border":"4rpx solid #174add","width":"160rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-else :src="product.tupian?baseUrl+product.tupian.split(',')[0]:''"></image>

							<view class="listmr" :style='{"padding":"0","margin":"0","color":"#888","flexWrap":"wrap","display":"flex","width":"calc(100% - 200rpx)","fontSize":"28rpx","height":"auto"}'>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis"}'>{{product.mingcheng}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{product.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{product.zhanghao}}</text>
								</view>
							</view>
							<!-- #ifdef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","margin":"20rpx 0 0 0","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"border":"2rpx solid #174add","padding":"10rpx","background":"#d3e5ff","display":"flex"}' v-if="(userid && isAuth('qiandaoxinxi','修改')) || (!userid && isAuthFront('qiandaoxinxi','修改'))" @tap.stop.proevent="onUpdate" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#174add","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#174add","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"border":"2rpx solid #9a9a9a","padding":"10rpx","background":"#ecebeb","display":"flex"}' v-if="(userid && isAuth('qiandaoxinxi','删除')) || (!userid && isAuthFront('qiandaoxinxi','删除'))" @tap.stop.proevent="onDelete" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
							<!-- #ifndef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","margin":"20rpx 0 0 0","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"border":"2rpx solid #174add","padding":"10rpx","background":"#d3e5ff","display":"flex"}' v-if="(userid && isAuth('qiandaoxinxi','修改')) || (!userid && isAuthFront('qiandaoxinxi','修改'))" @tap.stop.proevent="onUpdateTap(product)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#174add","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#174add","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"border":"2rpx solid #9a9a9a","padding":"10rpx","background":"#ecebeb","display":"flex"}' v-if="(userid && isAuth('qiandaoxinxi','删除')) || (!userid && isAuthFront('qiandaoxinxi','删除'))" @tap.stop.proevent="onDeleteTap(product.id)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
						</view>
					</view>
			
			
			


			
			
			
			
				</view>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx #00000030","whiteSpace":"nowrap","color":"#fff","right":"10rpx","outline":"none","borderRadius":"100%","top":"20rpx","background":"#03b882","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' class="add-btn" @click="screenBoxShow=true">筛</button>
				<button :style='{"border":"0","color":"rgb(255, 255, 255)","bottom":"40rpx","right":"120rpx","outline":"none","borderRadius":"100%","background":"#EF7061","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' v-if="userid && isAuth('qiandaoxinxi','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","color":"rgb(255, 255, 255)","bottom":"40rpx","right":"120rpx","outline":"none","borderRadius":"100%","background":"#EF7061","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' v-if="!userid && isAuthFront('qiandaoxinxi','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<view :style='{"top":"0","left":"0","background":"rgba(0, 0, 0, .3)","width":"100%","position":"absolute","height":"100%","zIndex":"665"}' v-if="screenBoxShow" @click="screenBoxShow=false"></view>
				<view class="screenBox" :class="screenBoxShow?'screenBoxActive':''">
					<view :style='{"width":"100%","padding":"0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"auto","padding":"0 20rpx 0 20rpx","fontSize":"28rpx","whiteSpace":"nowrap"}'>座位号</view>
						<input :style='{"border":"0","padding":"0 20rpx 0 20rpx","margin":"0 16rpx 0 0","borderRadius":"8rpx","background":"#f3f5f9","flex":"1","width":"75%","fontSize":"24rpx","height":"64rpx"}' placeholder="请输入座位号" v-model="searchForm.seatnum">
					</view>
					<view :style='{"width":"100%","padding":"10rpx 0 10rpx 0"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"28rpx"}'>预约日期</view>
						<view :style='{"width":"100%","alignItems":"center","flexWrap":"wrap","justifyContent":"center","display":"flex"}'>
							<picker :style='{"whiteSpace":"nowrap","borderRadius":"8rpx","textAlign":"center","background":"#f3f5f9","width":"46%","lineHeight":"64rpx","fontSize":"24rpx","height":"64rpx"}' mode="date" :value="searchForm.reservationdatestart" @change="reservationdatestartChange">
								<view>{{searchForm.reservationdatestart?searchForm.reservationdatestart:"请选择预约日期开始时间"}}</view>
							</picker>
							<view :style='{"padding":"0 4rpx","fontSize":"20rpx"}'>-</view>
							<picker :style='{"whiteSpace":"nowrap","borderRadius":"8rpx","textAlign":"center","background":"#f3f5f9","width":"46%","lineHeight":"64rpx","fontSize":"24rpx","height":"64rpx"}' mode="date" :value="searchForm.reservationdateend" @change="reservationdateendChange">
								<view>{{searchForm.reservationdateend?searchForm.reservationdateend:"请选择预约日期结束时间"}}</view>
							</picker>
						</view>
					</view>
					<view :style='{"width":"100%","padding":"0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"auto","padding":"0 20rpx 0 20rpx","fontSize":"28rpx","whiteSpace":"nowrap"}'>时间段</view>
						<input :style='{"border":"0","padding":"0 20rpx 0 20rpx","margin":"0 16rpx 0 0","borderRadius":"8rpx","background":"#f3f5f9","flex":"1","width":"75%","fontSize":"24rpx","height":"64rpx"}' placeholder="请输入时间段" v-model="searchForm.timeslot">
					</view>
					<view :style='{"width":"100%","padding":"20rpx 0 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<div :style='{"color":"#888","borderRadius":"8rpx","textAlign":"center","background":"#ededed","width":"30%","lineHeight":"68rpx","height":"68rpx"}' @click="screenReset">重置</div>
						<div :style='{"color":"#fff","borderRadius":"8rpx","textAlign":"center","background":"#03b882","width":"30%","lineHeight":"68rpx","height":"68rpx"}' @click="search">搜索</div>
					</view>
				</view>
			</view>
		</view>
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				searchForm:{
					refno: '',
					seatnum: '',
					reservationdatestart: '',
					reservationdateend: '',
					timeslot: '',
				},
				CustomBar: '0',
				listSort: 'qiandaoshijian',
				listOrder: 'desc',
				screenBoxShow: false,
			};
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onShow() {
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		async onLoad(options) {
            if(options.userid) {
                this.userid=options.userid;
            } else {
                this.userid = "";
            }
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
		},
		components: {
		},
		methods: {
			reservationdatestartChange(e){
				this.searchForm.reservationdatestart = e.detail.value
				this.$forceUpdate()
			},
			reservationdateendChange(e){
				this.searchForm.reservationdateend = e.detail.value
				this.$forceUpdate()
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.searchForm.refno){
					params['refno'] = '%' + this.searchForm.refno + '%'
				}
				if(this.searchForm.seatnum){
					params['seatnum'] = '%' + this.searchForm.seatnum + '%'
				}
				if(this.searchForm.reservationdate){
					params['reservationdate'] = '%' + this.searchForm.reservationdate + '%'
				}
				if(this.searchForm.timeslot){
					params['timeslot'] = '%' + this.searchForm.timeslot + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`qiandaoxinxi`, params);
                } else {
                    res = await this.$api.list(`qiandaoxinxi`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('qiandaoxinxi', JSON.stringify([id]));
							_this.$utils.msg('删除成功');
							_this.hasNext = true
							// 重置分页参数页数为1
							_this.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.refno){
					searchForm['refno'] = '%' + this.searchForm.refno + '%'
				}
				if(this.searchForm.seatnum){
					searchForm['seatnum'] = '%' + this.searchForm.seatnum + '%'
				}
				if(this.searchForm.reservationdatestart){
					searchForm['reservationdatestart'] = this.searchForm.reservationdatestart
				}
				if(this.searchForm.reservationdateend){
					searchForm['reservationdateend'] = this.searchForm.reservationdateend
				}
				if(this.searchForm.timeslot){
					searchForm['timeslot'] = '%' + this.searchForm.timeslot + '%'
				}
                let res = {};
                if(this.userid) {
                    res = await this.$api.page(`qiandaoxinxi`, searchForm);
                } else {
                    res = await this.$api.list(`qiandaoxinxi`, searchForm);
                }
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			}
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.category-one .tab {
		cursor: pointer;
		padding: 0 20rpx 0 32rpx;
		color: #333;
		background: #fff;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		line-height: 80rpx;
	}
	
	.category-one .tab.active {
		cursor: pointer;
		padding: 0 20rpx 0 32rpx;
		color: #fff;
		background: #174add;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		line-height: 80rpx;
	}
	.screenBox {
		padding: 100rpx 0 20rpx 0;
		transform: translate3d(100%, 0, 0);
		z-index: 6666;
		top: 0;
		background: #fff;
		width: 80%;
		overflow-y: auto;
		position: absolute;
		right: 0;
		transition: transform .3s;
		height: 100%;
		.screenTab {
			border-radius: 8rpx;
			margin: 10rpx 0 10rpx 0;
			background: #f3f5f9;
			width: calc(100% / 3 - 24rpx);
			line-height: 64rpx;
			text-align: center;
		}
		.screenTabActive {
			border-radius: 8rpx;
			margin: 10rpx 0 10rpx 0;
			color: #fff;
			background: #03b882;
			width: calc(100% / 3 - 20rpx);
			line-height: 64rpx;
			text-align: center;
		}
	}
	.screenBoxActive {
		transform: translate3d(0, 0, 0);
	}
</style>
