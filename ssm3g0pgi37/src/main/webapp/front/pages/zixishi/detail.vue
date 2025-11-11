
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
				<view :style='{"padding":"0","margin":"0px 0 0 0","borderColor":"#ccc","display":"flex","justifyContent":"flex-end","borderRadius":"0","flexWrap":"wrap","borderWidth":"0 0 0px 0","background":"#d3e5ff","width":"100%","position":"relative","borderStyle":"solid","height":"80rpx","zIndex":"999"}' class="detail-list-item price">
					<view :style='{"margin":"12rpx 20rpx 0 0","display":"flex"}' v-if="storeupFlag==1" @click="shoucang">
						<text class="icon iconfont icon-shoucang12" :style='{"padding":" 0 10rpx","margin":"0 0px 0 0","color":"#ffc441","borderRadius":"8rpx","background":"#303230","lineHeight":"56rpx","fontSize":"32rpx","order":"2","height":"56rpx"}'></text>
						<text :style='{"margin":"0 10rpx 0 0","lineHeight":"56rpx","fontSize":"30rpx","color":"#666"}'>已收藏</text>
					</view>
					<view :style='{"margin":"12rpx 20rpx 0 0","display":"flex"}' v-if="storeupFlag==0" @click="shoucang">
						<text class="icon iconfont icon-shoucang10" :style='{"padding":" 0 10rpx","margin":"0 0px 0 0","color":"#fff","borderRadius":"8rpx","background":"#303230","lineHeight":"56rpx","fontSize":"32rpx","order":"2","height":"56rpx"}'></text>
						<text :style='{"margin":"0 10rpx 0 0","lineHeight":"56rpx","fontSize":"30rpx","color":"#666"}'>收藏</text>
					</view>
				</view>

				<view :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="detail-list-item title">
					<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}' class="lable">编号：</view>
					<view :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' class="text" >{{detail.refno}}</view>
				</view>
				<view :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="detail-list-item title">
					<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}' class="lable">名称：</view>
					<view :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' class="text" >{{detail.mingcheng}}</view>
				</view>
				<view :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="detail-list-item title">
					<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}' class="lable">费用：</view>
					<view :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' class="text" >{{detail.feiyong}}</view>
				</view>

				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>类型：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.leixing}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>列数：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.columns}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>行数：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.rows}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>总数：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.totals}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>开放时间：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.opentime}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>评论数：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.discussnum}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>评分：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.totalscore}}</view>
				</view>
				<view class="detail-list-item" :style='{"padding":"0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>收藏数：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"rgb(0, 0, 0)"}' >{{detail.storeupnum}}</view>
				</view>





				<view class="detail-list-item rich" :style='{"padding":"0 0 0 20rpx","boxShadow":"inset 0px 0px 112rpx 0px #174ADD30","margin":"10rpx","borderColor":"#ccc","borderRadius":"20rpx","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"28rpx"}'>自习室介绍：</view>
					<view class="rich-text" :style='{"padding":"20rpx 24rpx 24rpx 0","margin":"0","lineHeight":"48rpx"}'>
						<rich-text :nodes="detail.zixishijieshao"></rich-text>
					</view>
				</view>


				<view class="time-content" :style='{"width":"100%","padding":"0 24rpx","margin":"40rpx 0 24rpx 0","height":"auto","order":"10"}'>
					<view class="comoment-header" :style='{"border":"2rpx solid #174add","padding":"0 0 0 20rpx","margin":"0 0 40rpx 0","borderRadius":"60rpx","background":"#fff","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
						<view :style='{"padding":"0 24rpx","lineHeight":"80rpx","fontSize":"32rpx","color":"#333","flex":"1"}'>评论</view>
						<view :style='{"padding":"0 30rpx","borderRadius":"60rpx","textAlign":"center","background":"#174add","display":"flex"}' @click="onCommentTap" class="btn-comment-content" style="display: flex;align-items: center;">
							<view :style='{"margin":"0 8rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#fff","display":"none","order":"2"}' class="cuIcon-add"></view>
							<view :style='{"margin":"0 10rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#fff"}'>添加评论</view>
						</view>
					</view>
				
					<view :style='{"padding":"24rpx","margin":"24rpx 0 0 0","borderRadius":"0px","background":"url(http://codegen.caihongy.cn/20241112/cfd7ccbe5256444b99cdf045151d65fb.jpg) no-repeat center top / 100% 100%","width":"100%","position":"relative","height":"auto"}' v-for="(item,index) in commentList" v-bind:key="index" class="cu-item comment-item">
						<view :style='{"position":"absolute","right":"20rpx","top":"20rpx","background":"none"}' v-if="item.istop">
							<span class="icon iconfont icon-jiantou24" :style='{"color":"#666","fontSize":"40rpx"}'></span>
						</view>
						<view :style='{"width":"100%","alignItems":"center","display":"flex","height":"auto"}'>
							<image :style='{"width":"76rpx","margin":"0 8rpx 0 0","borderRadius":"100%","display":"block","height":"76rpx"}' v-if="item.avatarurl" mode="aspectFill" :src="baseUrl+item.avatarurl"></image>
							<view :style='{"color":"#333","lineHeight":"60rpx","fontSize":"32rpx"}' class="text-grey">{{item.nickname}}</view>
						</view>
						<view :style='{"margin":"8rpx 0","lineHeight":"1.5","fontSize":"30rpx","color":"#333"}' class="text-gray text-content text-df">
							<rich-text :nodes="item.content"></rich-text>
						</view>
						<view :style='{"margin":"8rpx 0","lineHeight":"1.5","fontSize":"30rpx","color":"#333"}' class="text-gray text-content text-df">
							<uni-rate disabled :value="item.score" disabled-color="#ffca3e" />
						</view>
						<view :style='{"width":"100%","margin":"16rpx 0 0 0","alignItems":"center","justifyContent":"flex-end","display":"flex","height":"60rpx"}'>
							<view v-if="!comzanChange(item)&&!comcaiChange(item)" :style='{"alignItems":"center","margin":"0 20rpx 0 0","display":"flex"}' @click="comzanClick(item)">
								<span class="icon iconfont icon-zan07" :style='{"fontSize":"28rpx"}'></span>
								<span :style='{"fontSize":"28rpx","display":"none"}'>赞</span>
								<span :style='{"fontSize":"28rpx"}'>({{item.thumbsupnum}})</span>
							</view>
							<view v-if="comzanChange(item)" :style='{"margin":"0 20rpx 0 0","background":"none"}' @click="comzanClick(item)">
								<span class="icon iconfont icon-zan11" :style='{"color":"#ffc441","fontSize":"28rpx"}'></span>
								<span :style='{"color":"#ffc441","fontSize":"28rpx","display":"none"}'>已赞</span>
								<span :style='{"color":"#ffc441","fontSize":"28rpx"}'>({{item.thumbsupnum}})</span>
							</view>
							<view v-if="!comzanChange(item)&&!comcaiChange(item)" :style='{"alignItems":"center","display":"flex"}' @click="comcaiClick(item)">
								<span class="icon iconfont icon-cai15" :style='{"fontSize":"28rpx"}'></span>
								<span :style='{"fontSize":"28rpx","display":"none"}'>踩</span>
								<span :style='{"fontSize":"28rpx"}'>({{item.crazilynum}})</span>
							</view>
							<view v-if="comcaiChange(item)" :style='{"background":"none"}' @click="comcaiClick(item)">
								<span class="icon iconfont icon-cai16" :style='{"color":"#ffc441","fontSize":"28rpx"}'></span>
								<span :style='{"color":"#ffc441","fontSize":"28rpx","display":"none"}'>已踩</span>
								<span :style='{"color":"#ffc441","fontSize":"28rpx"}'>({{item.crazilynum}})</span>
							</view>
						</view>
						<view :style='{"width":"100%","lineHeight":"48rpx","fontSize":"28rpx","color":"#999","textAlign":"left"}' class="margin-top-sm text-gray text-df">{{item.addtime}}</view>
						<view v-if="item.reply" :style='{"margin":"8rpx 0","lineHeight":"1.5","fontSize":"30rpx","color":"#333"}' class="text-gray text-content text-df">
							回复:<rich-text :nodes="item.reply"></rich-text>
						</view>
						<view style="display: flex;display: flex;justify-content: flex-end;padding: 6rpx 0;" v-if="user&&user.id==item.userid">
							<view style="color: #999;font-size: 16rpx;" @click="delClick(item.id)">删除</view>
						</view>
					</view>
				</view>

				<view class="bottom-content bg-white tabbar border shop" :style='{"padding":"0 24rpx","margin":"40rpx auto","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto","order":"20"}'>

					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zixishi','跨表预状态-座位预约')" @tap="yuyueClick('zuoweiyuyue')" class="cu-btn bg-brown round shadow-blur" >座位预约</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zixishi','跨表预状态-座位预约')" @tap="yuyueClick('zuoweiyuyue')" class="cu-btn bg-brown round shadow-blur" >座位预约</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="userid&&isAuth('zixishi','私聊')" @tap="chatClick">联系TA</button>
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#174add","borderRadius":"8rpx","background":"#d3e5ff","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-if="!userid&&isAuthFront('zixishi','私聊')" @tap="chatClick">联系TA</button>
					
					<!-- #ifdef MP-WEIXIN -->
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#fff","borderRadius":"8rpx","background":"#22c0f5","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' open-type="share">分享</button>
					<!-- #endif -->
					<!-- #ifdef APP-PLUS -->
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#fff","borderRadius":"8rpx","background":"#22c0f5","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' @click="uniShare">分享</button>
					<!-- #endif -->
					<!-- #ifdef H5 -->
					<button :style='{"border":"0","padding":"0 20rpx","boxShadow":"none","margin":"0 10rpx 10rpx 0","color":"#fff","borderRadius":"8rpx","background":"#22c0f5","width":"auto","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' @click="shareClick">分享</button>
					<!-- #endif -->
				</view>
			</view>
		</view>
	</view>
</mescroll-uni>
	<!-- #ifdef H5 -->
	<view class="shareBG" v-if="shareShow" @click="shareShow = false"></view>
	<view class="shareBox" :class="shareShow?'shareBoxActive':''">
		<view class="shareView" @click="shareToMicroblog">
			<img src="@/static/share/weibo.png" style="width: 80%;" alt="">
			<view class="shareText">微博</view>
		</view>
		<view class="shareView" @click="shareToQQRom">
			<img src="@/static/share/qqkongjian.png" style="width: 80%;" alt="">
			<view class="shareText">QQ空间</view>
		</view>
	</view>
	<!-- #endif -->
	<uni-popup class="popup-content" ref="riqipopup" type="bottom" :maskClick="true">
		<view class="yuyueForm">
			<view class="yuyueItem">
				<view class="yuyueLabel">预约日期：</view>
				<view class="uni-list-cell-db">
					<picker mode="date" :value="yuyueForm.reservationdate" :start="nowDate"
						@change="yuyuedateChange">
						<view class="uni-input">{{yuyueForm.reservationdate?yuyueForm.reservationdate:'请选择'}}</view>
					</picker>
				</view>
			</view>
			<view class="yuyueItem">
				<view class="yuyueLabel">开始时间：</view>
				<view class="uni-list-cell-db">
					<picker mode=selector :value="yuyueForm.starttime" :range="timelist" range-key="name"
						@change="yuyuetimeChange">
						<view class="uni-input">{{yuyueForm.starttime?yuyueForm.starttime:'请选择'}}</view>
					</picker>
				</view>
			</view>
			<view class="yuyueItem">
				<view class="yuyueLabel">结束时间：</view>
				<view class="uni-list-cell-db">
					<picker mode=selector :value="yuyueForm.endtime" @change="yuyuetimeChange2" :range="timelist2" range-key="name">
						<view class="uni-input">{{yuyueForm.endtime?yuyueForm.endtime:'请选择'}}</view>
					</picker>
				</view>
			</view>
			<view class="yuyueBtn">
				<button @click="yuyueClose" class="cancel">关闭</button>
				<button @click="xuanzuoClick" class="confirm">下一步</button>
			</view>
		</view>
	</uni-popup>
	<uni-popup class="popup-content" ref="zuoweipopup" type="bottom" :maskClick="true">
		<view class="yuyueForm zuoweiForm" v-if="detail.totals">
			<view :class="(index%2!=0)&&(detail.rows!=Number(index) + 1)?'hangshu':''"
				v-for="(item,index) in detail.rows" :key="index" style="display: flex;width: 100%;">
				<view @click="xuanzhongClick(Number(indexs) + 1)" v-for="(items,indexs) in detail.totals"
					style="padding: 15px 0;text-align: center;font-size: 14px;"
					:style="{'width':`calc(100% / ${detail.columns})`}"
					v-if="changeZuoweiPaixu(index,indexs)">
					<img v-if="!yixuanChange(Number(indexs) + 1)" src="../../static/restaurant-detail/select.png"
						style="width: 30px;height: 30px" alt="">
					<img v-else-if="yixuanChange(Number(indexs) + 1)&&yuyueForm.seatnum!=(Number(indexs) + 1)"
						src="../../static/restaurant-detail/unselect.png" style="width: 30px;height: 30px" alt="">
					<img v-else src="../../static/restaurant-detail/now.png" style="width: 30px;height: 30px" alt="">
					<view>{{Number(indexs) + 1}}号</view>
				</view>
			</view>
			<view class="yuyueBtn">
				<button @click="upClick()" class="cancel">上一步</button>
				<button @click="submitClick()" class="confirm">下一步</button>
			</view>
		</view>
	</uni-popup>
</view>
</template>

<script>
	// #ifdef APP-PLUS
	import UniShare from 'components/uni-share/js_sdk/uni-share.js';
	const uniShare = new UniShare();
	// #endif
	import uniRate from "@/components/uni-rate/uni-rate.vue";
	export default {
		// #ifdef APP-PLUS
		onBackPress({from}) {
			if(from=='backbutton'){
				this.$nextTick(function(){
					uniShare.hide()
				})
				return uniShare.isShow;
			}
		},
		// #endif
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
				storeupFlag: 0,
				count: 0,
				timer: null,
				title:'',
				// #ifdef APP-PLUS
				href: '',
				// #endif
				// #ifdef H5
				shareShow: false,
				shareUrl: location.href,
				// #endif
				riqiVisible: false,
				yuyueForm: {
					reservationdate: '',
					timeslot: '',
					seatnum: '',
					starttime: '',
					endtime: ''
				},
				xuanzuoVisible: false,
				pickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now();
					}
				},
				yixuanzuowei: '',
				timelist: [],
				timelist1: [],
				timelist2: [],
				nowDate: this.$utils.getCurDate(),
				yuyuetable: '',
			}
		},
		components: {
			uniRate,
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
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
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
			this.getStoreup();
			let cleanType = uni.getStorageSync('discusszixishiCleanType')
			if(cleanType){
				uni.removeStorageSync('discusszixishiCleanType')
				this.mescroll.num = 1
				this.upCallback(this.mescroll)
				this.init(2);
			}
			let crossCleanType = uni.getStorageSync('crossCleanType')
			if(crossCleanType) {
				uni.removeStorageSync('crossCleanType')
				res = await this.$api.info('zixishi', this.id);
				let reg=new RegExp('http://localhost:8080/ssm3g0pgi37/upload','g')//g代表全部
				if(res.data.zixishijieshao){
					res.data.zixishijieshao = res.data.zixishijieshao.replace(reg, this.$base.url + 'upload');
				}
				this.detail = res.data;
				this.title = this.detail.refno
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
			changeZuoweiPaixu(index,indexs){
				index = Number(index)
				indexs = Number(indexs)
				if(index==0&&indexs<this.detail.columns){
					return true
				}
				if(index>0){
					if(indexs<(this.detail.columns * (index + 1))){
						if(indexs >= this.detail.columns * index){
							return true
						}
					}
				}
				return false
			},
			yuyueClick(table) {
				this.yuyueForm = {
					reservationdate: '',
					timeslot: '',
					seatnum: '',
					starttime: '',
					endtime: ''
				}
				this.yuyuetable = table
				this.$refs.riqipopup.open()
			},
			async xuanzuoClick() {
				if (this.yuyueForm.reservationdate == '') {
					return false
				}
				if (this.yuyueForm.starttime == '') {
					return false
				}
				if (this.yuyueForm.endtime == '') {
					return false
				}
				await this.getYuyue()
				this.$refs.riqipopup.close()
				this.$refs.zuoweipopup.open()
			},
			async getYuyue() {
				let res = await this.$api.list(`${this.yuyuetable}`, {
					refno: this.detail.refno,
					reservationdatestart: this.yuyueForm.reservationdate,
					reservationdateend: this.yuyueForm.reservationdate,
				})
				let arr = []
				for (let x in res.data.list) {
					if(res.data.list[x].reservationstate != '已取消'){
						if(res.data.list[x].timeslot.split('-').length>1&&this.changeZuowei(Number(this.yuyueForm.starttime.split(':')[0]),Number(this.yuyueForm.endtime.split(':')[0]),Number(res.data.list[x].timeslot.split('-')[0].split(':')[0]),Number(res.data.list[x].timeslot.split('-')[1].split(':')[0]))) {
							arr.push(res.data.list[x].seatnum)
						}
					}
				}
				this.yixuanzuowei = arr
			},
			changeZuowei(startA, endA, startB, endB){
				if (endA <= startB || endB <= startA) {
					// 两个时间段没有重叠
					return false;
				}
				// 两个时间段有重叠
				return true;
			},
			upClick() {
				this.$refs.riqipopup.open()
				this.$refs.zuoweipopup.close()
			},
			yixuanChange(index) {
				for (let x in this.yixuanzuowei) {
					if (this.yixuanzuowei[x] == index) {
						return false
					}
				}
				return true
			},
			xuanzhongClick(index) {
				if (!this.yixuanChange(index)) {
					this.$utils.msg('该选项已被选择')
					return false
				}
				this.yuyueForm.seatnum = index
			},
			submitClick() {
				if(!this.yuyueForm.seatnum) {
					this.$utils.msg('未选中')
					return false
				}
				this.yuyueForm = Object.assign(this.detail, this.yuyueForm)
				this.yuyueForm.timeslot = this.yuyueForm.starttime + '-' + this.yuyueForm.endtime
				uni.setStorageSync('crossTable', 'zixishi');
				uni.setStorageSync(`crossObj`, this.yuyueForm);
				this.$utils.jump(`../${this.yuyuetable}/add-or-update?cross=true`)
				this.$refs.zuoweipopup.close()
			},
			yuyueClose(){
				this.$refs.riqipopup.close()
			},
			yuyuedateChange(e) {
				if(new Date(e.detail.value).getTime() == new Date(this.$utils.getCurDate()).getTime()){
					let endtime = this.detail.opentime.split('-')[1]
					let time = this.getNextHour()
					var a = time.getHours()
					let b = endtime.split(':')[0]
					let timelist = []
					for(let x = Number(a);x<b;x++){
						timelist.push({name: (x<10?('0' + x):x) + ':00'})
					}
					this.timelist = timelist
				}else{
					this.timelist = this.timelist1
				}
				this.yuyueForm.reservationdate = e.detail.value
			},
			getNextHour() {
				var now = new Date();
				var minutes = now.getMinutes(); // 获取当前的分钟数
				var seconds = now.getSeconds(); // 获取当前的秒数
			 
				// 将当前时间调整到下一个整点
				now.setMinutes(minutes + (60 - minutes % 60), 0); // 加上差值直到整点
				now.setSeconds(seconds); // 保留当前秒数
				now.setMilliseconds(0); // 清除毫秒数
				return now; // 返回调整后的时间
			},
			yuyuetimeChange(e) {
				this.yuyueForm.starttime = this.timelist[e.detail.value].name
				let endtime = this.detail.opentime.split('-')[1]
				let a = this.yuyueForm.starttime.split(':')[0]
				let b = endtime.split(':')[0]
				let c = Number(a) + 1
				let timelist = []
				for (let x = Number(c); x <= b; x++) {
					timelist.push({
						name: (x < 10 ? ('0' + x) : x) + ':00'
					})
				}
				if(this.yuyueForm.endtime) {
					if(this.yuyueForm.starttime.split(':')[0] >= this.yuyueForm.endtime.split(':')[0]) {
						this.yuyueForm.endtime = ''
					}
				}
				this.timelist2 = timelist
				this.$forceUpdate()
			},
			yuyuetimeChange2(e) {
				this.yuyueForm.endtime = this.timelist2[e.detail.value].name
			},
			// 拨打电话
			callClick(row){
				uni.makePhoneCall({
					phoneNumber: row
				})
			},
			// #ifdef H5
			// 分享微博
			shareToMicroblog() {
				//跳转地址
				window.open(
					"https://service.weibo.com/share/share.php?url=" +
					encodeURIComponent(this.shareUrl) +
					"&title=" + this.title
				);
			},
			// 分享qq空间
			shareToQQRom() {
				//跳转地址
				window.open(
					"https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" +
					encodeURIComponent(this.shareUrl) +
					"&title=" +
					this.title +
					"&summary=" +
					this.title
				);
			},
			shareClick(){
				this.shareShow = true
			},
			// #endif
			// #ifdef APP-PLUS
			uniShare() {
				uniShare.show({
					content: { //公共的分享参数配置  类型（type）、链接（herf）、标题（title）、summary（描述）、imageUrl（缩略图）
						type: 0,
						href: this.href,
						title: this.title,
						summary: this.title,
						imageUrl: this.swiperList[0]?this.baseUrl + this.swiperList[0]: ''
					},
					menus: [{
							"img": "/static/share/weixin.png",
							"text": "微信好友",
							"share": { //当前项的分享参数配置。可覆盖公共的配置如下：分享到微信小程序，配置了type=5
								"provider": "weixin",
								"scene": "WXSceneSession"
							}
						},
						{
							"img": "/static/share/pengyouquan.png",
							"text": "微信朋友圈",
							"share": {
								"provider": "weixin",
								"scene": "WXSceneTimeline"
							}
						},
						{
							"img": "/static/share/xiaochengxu.png",
							"text": "微信小程序",
							"share": {
								provider: "weixin",
								scene: "WXSceneSession",
								type: 5,
								miniProgram: {
									id: '123',
									path: '/pages/huodongxinxi/list',
									webUrl: '/#/pages/huodongxinxi/list',
									type: 0
								},
							}
						},
						{
							"img": "/static/share/weibo.png",
							"text": "微博",
							"share": {
								"provider": "sinaweibo"
							}
						},
						{
							"img": "/static/share/qq.png",
							"text": "QQ",
							"share": {
								"provider": "qq"
							}
						},
						{
							"img": "/static/share/fuzhi.png",
							"text": "复制",
							"share": "copyurl"
						},
						{
							"img": "/static/share/gengduo.png",
							"text": "更多",
							"share": "shareSystem"
						}
					],
					cancelText: "取消分享",
				}, e => { //callback
				})
			},
			// #endif
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
				uni.setStorageSync('paytable','zixishi');
				uni.setStorageSync('payObject',this.detail);
				this.$utils.jump('../pay-confirm/pay-confirm?type=1')
			},
			onDetailTap(item) {
				uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			// 收藏
			async getStoreup() {
				if(!this.user){
					return false
				}
				let params = {
					page: 1,
					limit: 1,
					refid : this.id,
					tablename : 'zixishi',
					userid: this.user.id,
					type: '1',
				}
				let res = await this.$api.list(`storeup`, params);
				this.storeupFlag = res.data.list.length;
			},
			async shoucang(){
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				let _this = this;
				let params = {
					page: 1,
					limit: 1,
					refid : _this.detail.id,
					tablename : 'zixishi',
					userid: _this.user.id,
					type: '1',
				}
				let res = await _this.$api.list(`storeup`, params);
				if (res.data.list.length == 1) {
					let storeupId = res.data.list[0].id;
					uni.showModal({
						title: '提示',
						content: '是否取消',
						success: async function(res) {
							if (res.confirm) {
								_this.detail.storeupnum--
								await _this.$api.update('zixishi',_this.detail)
								await _this.$api.del('storeup', JSON.stringify([storeupId]));
								_this.$utils.msg('取消成功');
								_this.getStoreup();
							}
						}
					});
					return;
				}
				uni.showModal({
					title: '提示',
					content: '是否收藏',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.add('storeup', {
								userid: _this.user.id,
								name: _this.detail.refno,
								inteltype: _this.detail.leixing,
								picture: _this.swiperList[0],
								refid: _this.detail.id,
								tablename: 'zixishi',
								type: '1'
							});
							_this.detail.storeupnum++
							await _this.$api.update('zixishi',_this.detail)
							_this.$utils.msg('收藏成功');
							_this.getStoreup();
						}
					}
				});
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
				uni.setStorageSync('crossTable','zixishi');
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
				let res = await this.$api.info('zixishi', this.id);
				let reg=new RegExp('http://localhost:8080/ssm3g0pgi37/upload','g')//g代表全部
				if(res.data.zixishijieshao){
					res.data.zixishijieshao = res.data.zixishijieshao.replace(reg, this.$base.url + 'upload').replace(/<img/g,'<img style="width: 100%;"');
				}
				this.detail = res.data;
				if (res.data.opentime&&res.data.opentime.split('-').length>1) {
					let starttime = res.data.opentime.split('-')[0]
					let endtime = res.data.opentime.split('-')[1]
					let a = starttime.split(':')[0]
					let b = endtime.split(':')[0]
					let timelist = []
					let timelist2 = []
					for(let x = Number(a);x<=b;x++){
						if(x==b) {
							timelist2.push({name: (x<10?('0' + x):x) + ':00'})
						}else {
							timelist.push({name: (x<10?('0' + x):x) + ':00'})
							timelist2.push({name: (x<10?('0' + x):x) + ':00'})
						}
						
					}
					this.timelist = timelist
					this.timelist1 = timelist
					this.timelist2 = timelist2
					this.$forceUpdate()
				}

				this.title = this.detail.refno



				// 轮播图片
				this.swiperList = this.detail.tupian ? this.detail.tupian.split(",") : [];
				







				//修改富文本的图片样式
				if(this.detail.zixishijieshao) {
					this.detail.zixishijieshao = this.detail.zixishijieshao.replace(/img src/gi,"img style=\"width:100%;\" src");
				}
				




				if(type==2){
					this.detail.discussnum++
					await this.$api.update('zixishi',this.detail)
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
				if(uni.getStorageSync("appUserid")){
					let res = await this.$api.list('discusszixishi', {
						page: mescroll.num,
						limit: 10,
						refid: Number(this.id),
						sort: 'istop',
						order: 'desc'
					});
					// 如果是第一页数据置空
					if (mescroll.num == 1) this.commentList = [];
					for(let x in res.data.list){
						if(res.data.list[x].content){
							res.data.list[x].content = res.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src")
						}
					}
					this.commentList = this.commentList.concat(res.data.list);
					if (res.data.list.length == 0) this.hasNext = false;
				}
				mescroll.endSuccess(mescroll.size, this.hasNext);

			},
			comzanChange(row){
				if(row.tuserids){
					let arr = String(row.tuserids).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							return true
						}
					}
				}
				return false
			},
			async comzanClick(row){
				if(!this.user){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.user.id
					}else {
						row.tuserids = this.user.id
					}
					await this.$api.update('discusszixishi',row)
					this.$utils.msg('点赞成功');
				}else {
					row.thumbsupnum--
					let arr = String(row.tuserids).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					await this.$api.update('discusszixishi',row)
					this.$utils.msg('取消成功');
				}
				this.$forceUpdate()
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = String(row.cuserids).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							return true
						}
					}
				}
				return false
			},
			async comcaiClick(row){
				if(!this.user){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.user.id
					}else {
						row.cuserids = this.user.id
					}
					await this.$api.update('discusszixishi',row)
					this.$utils.msg('点踩成功');
				}else {
					row.crazilynum--
					let arr = String(row.cuserids).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					await this.$api.update('discusszixishi',row)
					this.$utils.msg('取消成功');
				}
				this.$forceUpdate()
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
			// 添加评论
			async onCommentTap() {
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				let res = {}
				let obj = {
					page: 1,
					limit: 1,
					refid: this.id,
					userid: uni.getStorageSync('appUserid')
				}
				res = await this.$api.page('discusszixishi',obj)
				if(res.data.list.length){
					this.$utils.msg('每个人只能评论一次');
					return;
				}
				this.$utils.jump(`../discusszixishi/add-or-update?refid=${this.id}` )
			},
			delClick(id){
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否删除此评论？',
					async success(res) {
						if(res.confirm){
							await that.$api.del('discusszixishi',JSON.stringify([id]))
							that.$utils.msg('删除成功')
							that.detail.discussnum--
							await that.$api.update('zixishi',that.detail)
							setTimeout(()=>{
								that.upCallback(that.mescroll)
							},1500)
						}
					}
				})
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
	// #ifdef H5
	.shareBG{
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0,0,0,.3);
		z-index: 665;
	}
	.shareBox {
		width: 100%;
		position: fixed;
		height: 20%;
		right: 0;
		bottom: 0;
		z-index: 666;
		-webkit-transform: translate3d(0, 100%, 0);
		transform: translate3d(0, 100%, 0);
		transition: transform .3s;
		overflow-y: scroll;
		background: #fff;
		display: flex;
		align-items: center;
		justify-content: space-around;
	}
	.shareBoxActive {
		-webkit-transform: translate3d(0%, 0, 0);
		transform: translate3d(0%, 0, 0);
	}
	.shareView {
		width: 20%;
		display: flex;
		flex-direction: column;
		align-items: center;
		.shareText {
			font-size: 24rpx;
			padding: 10rpx 0 0 ;
		}
	}
	// #endif
	

	.yuyueForm {
		max-height: 50vh;
		overflow-y: scroll;
		width: 100%;
		background: #fff;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0 20px 20px;
		.yuyueItem {
			display: flex;
			align-items: center;
			margin: 20px 0;
			width: 100%;
			.yuyueLabel{
				font-size: 14px;
				color: #555;
			}
			.uni-list-cell-db{
				font-size: 14px;
				color: #333;
			}
		}
		.yuyueBtn {
			width: 100%;
			display: flex;
			justify-content: space-around;
			.cancel{
				font-size: 14px;
				background: #ffdcfb;
			}
			.confirm{
				font-size: 14px;
				background: #c1ffbc;
			}
		}
	}
	.zuoweiForm{
		max-height: 70vh;
		padding: 0 10px;
	}
</style>
