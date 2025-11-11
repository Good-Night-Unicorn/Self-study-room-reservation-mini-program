<template>
	<view>
		<view class="cu-chat" :style="{'padding-bottom': tabBarHeight + 'px'}">
			<view v-for="(item,index) in list" v-bind:key="index" v-if="item.type!=4">
				<view v-if="item.addtime" style="width: 100%;text-align: center;font-size: 10px;color: #666;">{{timeFormat(item.addtime)}}</view>
				<view v-if="item.ask" class="cu-item self">
					<view class="main">
						<view class="content bg-green shadow">
							<text v-if="item.type==1">{{item.ask}}</text>
							<image :src="baseUrl + item.ask" v-else-if="item.type==2" mode="aspectFill" style="width: 150px;" @click="imgView(item.ask)"></image>
							<video :src="baseUrl + item.ask" v-else-if="item.type==3" style="width: 150px;" controls></video>
						</view>
					</view>
					<img class="cu-avatar radius" :src="baseUrl+item.uimage" />
				</view>
				<view v-if="item.reply" class="cu-item">
					<img v-if="item.uimage" class="cu-avatar radius" :src="baseUrl+item.uimage" />
					<view v-else class="cu-avatar radius" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big143004.jpg);"></view>
					<view class="main">
						<view class="content shadow">
							<text v-if="item.type==1">{{item.reply}}</text>
							<image :src="baseUrl + item.reply" v-else-if="item.type==2" mode="aspectFill" style="width: 150px;" @click="imgView(item.reply)"></image>
							<video :src="baseUrl + item.reply" v-else-if="item.type==3" style="width: 150px;" controls></video>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view style="width: 100%;height: 45px;"></view>
		<view class="cu-bar foot input" :style="{'bottom':showType?((45 + tabBarHeight) + 'px'):(tabBarHeight + 'px')}">
			<image class="jiahao" src="/static/jiahao.png" mode="widthFix" @click="showClick"></image>
			<input v-model="ask" class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300"
				cursor-spacing="10"></input>
			<button @tap="onSendTap" class="cu-btn bg-green shadow">发送</button>
		</view>
		<view class="showView" v-if="showType" :style='{"bottom":tabBarHeight + "px"}'>
			<view class="showBox" @click="changeImg">
				选择图片
			</view>
			<view class="showBox" @click="changeVideo">
				选择视频
			</view>
		</view>
	</view>
</template>

<script>
	import timeMethod from '@/utils/timeMethod'
	export default {
		data() {
			return {
				list: [],
				ask: '',
				showType: false,
				tabBarHeight: 0,
				userid: 0,
				askType: 1 //1为智能回复 2为人工
			};
		},
		onLoad() {
		},
		onShow(){
			let _this = this;
			uni.getSystemInfo({
				success: function(info) {
					_this.tabBarHeight = info.windowBottom
					_this.$forceUpdate()
					
				}
			});
			this.init()
			this.intelligenceSave('您好，在线客服很高兴为您服务！')
			uni.connectSocket({
				url: 'ws' + this.$base.url.split('http')[1] + '/ws?user_id=' + this.userid + '&to_id=1'
			})
			uni.onSocketMessage((res)=> {
				this.init()
			})
		},
		onHide() {
			uni.closeSocket()
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		methods: {
			formatMessages(messages) {
				let lastTime = null;
				messages.forEach((message, index) => {
					const currentTime = new Date(message.addtime).getTime();
					if (lastTime !== null) {
						const timeDiff = (currentTime - lastTime) / 1000 / 60; // 转换为分钟
						if (timeDiff < 3) {
							message.addtime = ''; // 如果小于3分钟，不显示时间
						}
					}
					lastTime = currentTime;
				});
				return messages;
			},
			timeFormat(time) {
				const Time = timeMethod.getTime(time).split("T");
				//当前消息日期属于周
				const week = timeMethod.getDateToWeek(time);
				//当前日期0时
				const nti = timeMethod.setTimeZero(timeMethod.getNowTime());
				//消息日期当天0时
				const mnti = timeMethod.setTimeZero(timeMethod.getTime(time));
				//计算日期差值
				const diffDate = timeMethod.calculateTime(nti, mnti);
				//本周一日期0时 （后面+1是去除当天时间）
				const fwnti = timeMethod.setTimeZero(timeMethod.countDateStr(-timeMethod.getDateToWeek(timeMethod
					.getNowTime()).weekID + 1));
				//计算周日期差值
				const diffWeek = timeMethod.calculateTime(mnti, fwnti);
			
				if (diffDate === 0) { //消息发送日期减去当天日期如果等于0则是当天时间
					return Time[1].slice(0, 5);
				} else if (diffDate < 172800000) { //当前日期减去消息发送日期小于2天（172800000ms）则是昨天-  一天最大差值前天凌晨00:00:00到今天晚上23:59:59
					return "昨天 " + Time[1].slice(0, 5);
				} else if (diffWeek >= 0) { //消息日期减去本周一日期大于0则是本周
					return week.weekName;
				} else { //其他时间则是日期
					return Time[0].slice(5, 10);
				}
			},
			showClick() {
				this.showType = !this.showType
				this.scrollBottom()
			},
			changeImg(){
				let _this = this;
				this.$api.upload(function(res) {
					_this.save('upload/' + res.file,2)
				});
			},
			changeVideo(){
				let _this = this;
				this.$api.uploadMedia(function(res) {
					_this.save('upload/' + res.file,3)
				});
			},
			imgView(img){
				uni.previewImage({
					urls: [this.baseUrl + img]
				});
			},
			async onSendTap() {
				await this.save()
			},
			async intelligenceSave(ask) {
				await this.$api.save('chat', {
					reply: ask,
					userid: Number(this.userid),
					type: 1
				});
				
				this.init();
			},
			async save(asks = null,type=1) {
				await this.$api.add('chat', {
					ask: asks?asks:this.ask,
					uimage: uni.getStorageSync('headportrait'),
					uname: uni.getStorageSync('nickname'),
					type: type,
					userid: this.userid,
					isreply: 1
				});
				this.ask = '';
				uni.sendSocketMessage({
					data: asks?asks:this.ask
				})
				this.init();
			},
			async init() {
				this.userid = uni.getStorageSync("appUserid")
				let res = await this.$api.page('chat', {
					sort: 'addtime',
					order: 'asc',
					limit: 1000,
				});
				this.list = this.formatMessages(res.data.list);
				this.scrollBottom()
			},
			scrollBottom(){
				this.$nextTick(function() {
					setTimeout(() => {
						uni.pageScrollTo({
							scrollTop: 99999,
							duration: 0
						})
					}, 200) // 要加点延迟, 不然有可能不生效
				})
			},
		}
	}
</script>

<style lang="scss">
	page {
		padding-bottom: 100upx;
	}

	.jiahao {
		width: 50upx;
		margin: 0 10upx;
	}

	.showView {
		width: 100%;
		background: #fff;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		align-items: center;

		.showBox {
			width: 100%;
			line-height: 80upx;
			color: #fff;
			background: #007AFF;
			text-align: center;
			font-size: 28upx;
			margin: 0 5upx 10upx;
		}
	}
</style>
