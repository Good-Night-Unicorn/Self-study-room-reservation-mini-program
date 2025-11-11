<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"0","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#1ccb96","alignItems":"center","borderRadius":"20rpx","borderWidth":"0px 0","background":"#f5f5f5","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<xia-editor :style='{"minHeight":"300rpx","padding":"10rpx 0","margin":"0px","color":"#666","flex":"1","background":"rgba(255, 255, 255, 0)","width":"100%","fontSize":"30rpx","height":"auto"}' v-model="content" @editorChange="contentChange" placeholder="回复"></xia-editor>
			</view>
			<view :style='{"width":"100%","margin":"40rpx 0 0","justifyContent":"center","display":"flex","height":"auto"}'>
				<button :style='{"border":"0","padding":"0px","margin":"0 20rpx","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#174add","width":"100%","lineHeight":"88rpx","fontSize":"32rpx","height":"88rpx"}' @tap="onReplyTap" class="bg-red margin-tb-sm">提交回复</button>
			</view>
		</form>
	</view>
</view>
</template>

<script>
	import xiaEditor from '@/components/xia-editor/xia-editor'
	export default {
		data() {
			return {
				pid: '',
				content: '',
				username: '',
				avatarurl: '',
				user: {},
			}
		},
		components: {
			xiaEditor
		},
		async onLoad(options) {
			this.pid = options.pid;
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			if(table == `yonghu`){
				this.username = this.user.zhanghao
			}
		},
		methods: {
			contentChange(e) {
				this.content = e
			},
			async onReplyTap() {
				if(this.content==''){
					this.$utils.msg('内容不能为空');
					return false
				}
				this.avatarurl = uni.getStorageSync('headportrait')?uni.getStorageSync('headportrait'):'';
				
				await this.$api.save('forum',{
					parentid: this.pid,
					content: this.content,
					avatarurl: this.avatarurl,
					username: this.username,
					toptime: this.$utils.getCurDateTime()
				});
				this.$utils.msgBack('回复成功');
				uni.setStorageSync('forumComType', true);
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
