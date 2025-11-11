<template>
	<view class="content">
		<view :style='{"alignContent":"flex-start","alignItems":"flex-start","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","height":"auto"}'>
			<view class="list-swiper-4" :style='{"width":"calc(100% - 48rpx)","margin":"20rpx auto","position":"relative","height":"360rpx","order":"-1"}' @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
				<view :style='{"top":"0%","borderRadius":"32rpx","left":"0%","background":"none","width":"100%","position":"absolute","height":"360rpx"}' class="item animate__animated" :class="prevNumList4 == index  ? 'animate__fadeOutRight' : (numList4 == index  ? 'animate__fadeInLeft' : '')" v-for="(swiper,index) in swiperList" :key="index" v-if="numList4 == index || prevNumList4 == index">
					<image :style='{"width":"100%","objectFit":"cover","borderRadius":"32rpx","display":"block","height":"360rpx"}' mode="aspectFill" :src="baseUrl+swiper.img" @tap="onSwiperTap(swiper)"></image>
					<view :style='{"padding":"8rpx 20rpx","margin":"-20rpx 0 0 0","transform":"translate3d(-50%, -50%, 0)","top":"50%","color":"#000","left":"50%","background":"rgba(255, 255, 255, 0.3)","display":"none","lineHeight":"1.5","fontSize":"40rpx","position":"absolute"}'>{{ swiper.title }}</view>
				</view>
				<view class="animate__navigation" :style='{"alignItems":"center","left":"0%","bottom":"20rpx","background":"rgba(0,0,0,0)","display":"flex","width":"100%","position":"absolute","justifyContent":"center","height":"40rpx"}'>
					<block v-for="(swiper,index) in swiperList" :key="index">
						<text class="navigation-item" v-if="numList4 == index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#23baf7","height":"16rpx"}'></text>
						<text class="navigation-item" v-if="numList4 != index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"}'></text>
					</block>
				</view>
			</view>
			<!-- menu -->
			<view v-if="true" class="menu_view">
				<block v-for="(item,index1) in menuList" v-bind:key="item.roleName">
					<block v-if="index1==0" v-bind:key="index" v-for=" (menu,index) in item.frontMenu">
						<block v-bind:key="sort" v-for=" (child,sort) in menu.child">
							<block v-bind:key="sort2" v-for=" (button,sort2) in child.buttons">
								<view class="menu-item" v-if="button=='查看' && child.tableName!='yifahuodingdan' && child.tableName!='yituikuandingdan' &&child.tableName!='yiquxiaodingdan' && child.tableName!='weizhifudingdan' && child.tableName!='yizhifudingdan' && child.tableName!='yiwanchengdingdan' " @tap="onPageTap2(child.tableName)">
									<view class="iconarr" :class="child.appFrontIcon" :style="{'color':menuColor[index]}"></view>
									
									<view class="text">{{child.menu.split("列表")[0]}}</view>
								</view>
							</block>
						</block>
					</block>
				</block>
			</view>
			<!-- 商品推荐 -->
			<view class="listBox recommend" :style='{"width":"100%","padding":"0 24rpx","margin":"40rpx auto","background":"none","order":"5"}'>
				<view class="title" :style='{"padding":"0 0 0 0px","margin":"0 0 40rpx","borderColor":"#23baf7","textAlign":"left","background":"none","borderWidth":"0 0 0 0px","width":"100%","position":"relative","borderStyle":"solid","fontWeight":"600"}'>
					<view :style='{"color":"#000","fontSize":"44rpx"}'>自习室推荐</view>
				</view>
				<!-- 样式4 -->
				<view class="list-box style4" :style='{"padding":"0px","margin":"0","color":"#fff","flexWrap":"wrap","display":"block","width":"100%","fontSize":"24rpx","height":"auto"}'>
					<view class="list-item" :style='{"padding":"0","margin":"0 0 20rpx","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"6"}'>
						<view v-if="zixishilist.length > 0" @tap="onDetailTap('zixishi',zixishilist[0].id)" class="box box1" :style='{"padding":"12rpx","margin":"0","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"48%","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"764rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[0].tupian.substring(0,4)=='http'" :src="zixishilist[0].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"764rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[0].tupian?baseUrl+zixishilist[0].tupian.split(',')[0]:''"></image>
							<view :style='{"alignContent":"center","padding":"20rpx 0 20rpx 20rpx","alignItems":"center","borderRadius":"0 0 20rpx 20rpx","left":"12rpx","flexWrap":"wrap","bottom":"12rpx","background":"rgba(0,0,0,.3)","display":"flex","width":"calc(100% - 24rpx)","position":"absolute","height":"auto"}'>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">编号:{{zixishilist[0].refno}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">名称:{{zixishilist[0].mingcheng}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">费用:{{zixishilist[0].feiyong}}</view>
								<view :style='{"width":"100%","padding":"0px"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[0].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[0].storeupnum}}</text>
								</view>
							</view>
						</view>
						<view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
							<view v-if="zixishilist.length > 1" @tap="onDetailTap('zixishi',zixishilist[1].id)" class="box box2" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[1].tupian.substring(0,4)=='http'" :src="zixishilist[1].tupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[1].tupian?baseUrl+zixishilist[1].tupian.split(',')[0]:''"></image>
								<view :style='{"padding":"10rpx 0 10rpx 10rpx","borderRadius":"0 0 16rpx 16rpx","left":"12rpx","bottom":"12rpx","background":"rgba(0,0,0,.3)","width":"calc(100% - 24rpx)","position":"absolute"}'>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">编号:{{zixishilist[1].refno}}</view>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">名称:{{zixishilist[1].mingcheng}}</view>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">费用:{{zixishilist[1].feiyong}}</view>
							
									<view :style='{"width":"100%","padding":"0px"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{zixishilist[1].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{zixishilist[1].storeupnum}}</text>
									</view>
								</view>
							</view>
							<view v-if="zixishilist.length > 2" @tap="onDetailTap('zixishi',zixishilist[2].id)" class="box box3" :style='{"padding":"12rpx","margin":"0","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[2].tupian.substring(0,4)=='http'" :src="zixishilist[2].tupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[2].tupian?baseUrl+zixishilist[2].tupian.split(',')[0]:''"></image>
								<view :style='{"padding":"10rpx 0 10rpx 10rpx","borderRadius":"0 0 16rpx 16rpx","left":"12rpx","bottom":"12rpx","background":"rgba(0,0,0,.3)","width":"calc(100% - 24rpx)","position":"absolute"}'>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">编号:{{zixishilist[2].refno}}</view>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">名称:{{zixishilist[2].mingcheng}}</view>
									<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">费用:{{zixishilist[2].feiyong}}</view>

									<view :style='{"width":"100%","padding":"0px"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{zixishilist[2].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{zixishilist[2].storeupnum}}</text>
									</view>
								</view>
							</view>
						</view>
					</view>
					<view class="list-item" :style='{"padding":"0","margin":"0","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"3"}'>
						<view v-if="zixishilist.length > 3" @tap="onDetailTap('zixishi',zixishilist[3].id)" class="box box4" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"48%","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[3].tupian.substring(0,4)=='http'" :src="zixishilist[3].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[3].tupian?baseUrl+zixishilist[3].tupian.split(',')[0]:''"></image>
							<view :style='{"padding":"10rpx 0 10rpx 10rpx","borderRadius":"0 0 16rpx 16rpx","left":"12rpx","bottom":"12rpx","background":"rgba(0,0,0,.3)","width":"calc(100% - 24rpx)","position":"absolute"}'>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">编号:{{zixishilist[3].refno}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">名称:{{zixishilist[3].mingcheng}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">费用:{{zixishilist[3].feiyong}}</view>
								<view :style='{"width":"100%","padding":"0 0 0px"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[3].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[3].storeupnum}}</text>
								</view>
							</view>
						</view>
						<view v-if="zixishilist.length > 4" @tap="onDetailTap('zixishi',zixishilist[4].id)" class="box box5" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"48%","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[4].tupian.substring(0,4)=='http'" :src="zixishilist[4].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[4].tupian?baseUrl+zixishilist[4].tupian.split(',')[0]:''"></image>
							<view :style='{"padding":"10rpx 0 10rpx 10rpx","borderRadius":"0 0 16rpx 16rpx","left":"12rpx","bottom":"12rpx","background":"rgba(0,0,0,.3)","width":"calc(100% - 24rpx)","position":"absolute"}'>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">编号:{{zixishilist[4].refno}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">名称:{{zixishilist[4].mingcheng}}</view>
								<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">费用:{{zixishilist[4].feiyong}}</view>
								<view :style='{"width":"100%","padding":"0 0 0px"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[4].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{zixishilist[4].storeupnum}}</text>
								</view>
							</view>
						</view>
					</view>
					<view v-if="zixishilist.length > 5" @tap="onDetailTap('zixishi',zixishilist[5].id)" class="box box6" :style='{"padding":"16rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(90deg, rgba(41,91,224,1) 0%, rgba(145,183,241,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
						<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-if="zixishilist[5].tupian.substring(0,4)=='http'" :src="zixishilist[5].tupian"></image>
						<image :style='{"width":"100%","objectFit":"cover","borderRadius":"16rpx","display":"block","height":"360rpx"}' class="list-item-image" mode="aspectFill" v-else :src="zixishilist[5].tupian?baseUrl+zixishilist[5].tupian.split(',')[0]:''"></image>
						<view :style='{"alignContent":"center","padding":"20rpx 0 0 20rpx","alignItems":"center","borderRadius":"16rpx 0 0 16rpx","left":"16rpx","flexWrap":"wrap","bottom":"16rpx","background":"rgba(0,0,0,.3)","display":"flex","width":"calc(55% - 32rpx)","position":"absolute","height":"calc(100% - 32rpx)"}'>
							<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">编号:{{zixishilist[5].refno}}</view>
							<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">名称:{{zixishilist[5].mingcheng}}</view>
							<view :style='{"padding":"0px","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.2","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">费用:{{zixishilist[5].feiyong}}</view>
						
							<view :style='{"width":"100%","padding":"0 0 0px"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{zixishilist[5].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"padding":"0 10rpx 0 0","display":"inline-block"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{zixishilist[5].storeupnum}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 商品推荐 -->
			
			<!-- 商品列表 -->
			<view class="listBox list" :style='{"width":"100%","padding":"0 24rpx","margin":"40rpx auto","background":"none","order":"9"}'>
				<view class="title" :style='{"padding":"0 0 0 0px","margin":"0 0 40rpx","borderColor":"#23baf7","color":"#000","textAlign":"left","background":"none","borderWidth":"0 0 0 0px","width":"100%","position":"relative","borderStyle":"solid","fontWeight":"600"}'>
					<view :style='{"color":"#000","fontSize":"44rpx"}'>失物招领</view>
					<view :style='{"border":"0px solid #1ccb96","padding":"0 40rpx","margin":"0 auto","textAlign":"center","display":"block","right":"0px","top":"0px","borderRadius":"0px","background":"none","width":"auto","lineHeight":"80rpx","position":"absolute","zIndex":"999"}' @tap="onPageTap('shiwuzhaoling')">
					  <text :style='{"color":"#174add","fontSize":"28rpx","fontWeight":"500"}'>更多</text>
					  <text class="icon iconfont icon-jiantou18" :style='{"color":"#174add","fontSize":"28rpx","fontWeight":"500"}'></text>
					</view>
				</view>
				<!-- 样式6 -->
				<view class="list-box style6" :style='{"width":"100%","padding":"0","margin":"0","fontSize":"26rpx","color":"#fff","height":"auto"}'>
					<view v-if="homeshiwuzhaolinglist.length > 0" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[0].id)" class="box box1" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
						<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[0].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[0].wupintupian"></image>
						<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[0].wupintupian?baseUrl+homeshiwuzhaolinglist[0].wupintupian.split(',')[0]:''"></image>
						<view :style='{"width":"100%"}'>
							<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">{{homeshiwuzhaolinglist[0].wupinmingcheng}}</view>
							<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">状态:{{homeshiwuzhaolinglist[0].zhuangtai}}</view>
							<view :style='{"padding":"0 20rpx"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[0].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"padding":"0 20rpx"}'>
								<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[0].zhanghao}}</text>
							</view>
							<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
								<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[0].storeupnum}}</text>
							</view>
						</view>
					</view>
					<view class="list-item" :style='{"width":"100%","margin":"0 0 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
						<view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
							<view v-if="homeshiwuzhaolinglist.length > 1" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[1].id)" class="box box2" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[1].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[1].wupintupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[1].wupintupian?baseUrl+homeshiwuzhaolinglist[1].wupintupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%"}'>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">{{homeshiwuzhaolinglist[1].wupinmingcheng}}</view>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">状态:{{homeshiwuzhaolinglist[1].zhuangtai}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[1].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[1].zhanghao}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[1].storeupnum}}</text>
									</view>
								</view>
							</view>
							<view v-if="homeshiwuzhaolinglist.length > 2" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[2].id)" class="box box3" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"164rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[2].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[2].wupintupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"164rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[2].wupintupian?baseUrl+homeshiwuzhaolinglist[2].wupintupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%"}'>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">{{homeshiwuzhaolinglist[2].wupinmingcheng}}</view>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">状态:{{homeshiwuzhaolinglist[2].zhuangtai}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[2].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[2].zhanghao}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[2].storeupnum}}</text>
									</view>
								</view>
							</view>
						</view>
						<view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
							<view v-if="homeshiwuzhaolinglist.length > 3" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[3].id)" class="box box4" :style='{"padding":"12rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"164rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[3].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[3].wupintupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"164rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[3].wupintupian?baseUrl+homeshiwuzhaolinglist[3].wupintupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%"}'>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">{{homeshiwuzhaolinglist[3].wupinmingcheng}}</view>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">状态:{{homeshiwuzhaolinglist[3].zhuangtai}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[3].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[3].zhanghao}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[3].storeupnum}}</text>
									</view>
								</view>
							</view>
							<view v-if="homeshiwuzhaolinglist.length > 4" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[4].id)" class="box box5" :style='{"padding":"12rpx","margin":"0","borderRadius":"20rpx","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","width":"100%","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[4].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[4].wupintupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[4].wupintupian?baseUrl+homeshiwuzhaolinglist[4].wupintupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%"}'>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">{{homeshiwuzhaolinglist[4].wupinmingcheng}}</view>
									<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"48rpx","overflow":"hidden","whiteSpace":"nowrap","textOverflow":"ellipsis"}' class="title ">状态:{{homeshiwuzhaolinglist[4].zhuangtai}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[4].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[4].zhanghao}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit"}'></text>
										<text :style='{"lineHeight":"1.5"}'>{{homeshiwuzhaolinglist[4].storeupnum}}</text>
									</view>
								</view>
							</view>
						</view>
					</view>
					<view v-if="homeshiwuzhaolinglist.length > 5" @tap="onDetailTap('shiwuzhaoling',homeshiwuzhaolinglist[5].id)" class="box box6" :style='{"padding":"0","margin":"0","background":"linear-gradient(180deg, rgba(33,83,223,1) 0%, rgba(145,183,243,1) 100%)","display":"none","width":"100%","position":"relative","height":"auto"}'>
						<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="homeshiwuzhaolinglist[5].wupintupian.substring(0,4)=='http'" :src="homeshiwuzhaolinglist[5].wupintupian"></image>
						<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="homeshiwuzhaolinglist[5].wupintupian?baseUrl+homeshiwuzhaolinglist[5].wupintupian.split(',')[0]:''"></image>
						<view :style='{"width":"100%"}'>
							<view :style='{"padding":"0 20rpx","lineHeight":"1.5","fontSize":"32rpx","color":"#fff"}' class="title ">{{homeshiwuzhaolinglist[5].wupinmingcheng}}</view>
							<view :style='{"padding":"0 20rpx","lineHeight":"1.5","fontSize":"32rpx","color":"#fff"}' class="title ">状态:{{homeshiwuzhaolinglist[5].zhuangtai}}</view>
							<view :style='{"padding":"0 20rpx"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{homeshiwuzhaolinglist[5].addtime.replace(/\-/g,'-').replace(/\:/g,':')}}</text>
							</view>
							<view :style='{"padding":"0 20rpx"}'>
								<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{homeshiwuzhaolinglist[5].zhanghao}}</text>
							</view>
							<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{homeshiwuzhaolinglist[5].storeupnum}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 商品列表 -->
			<!-- 新闻资讯 -->
			<view class="listBox news" :style='{"padding":"0px","margin":"40rpx auto","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 48rpx)","position":"relative","height":"auto","order":"1"}'>
				<view class="title" :style='{"padding":"0 0 0 0px","margin":"0 0 40rpx","borderColor":"#23baf7","color":"#000","textAlign":"left","background":"none","borderWidth":"0 0 0 0px","width":"100%","lineHeight":"80rpx","position":"relative","borderStyle":"solid","fontWeight":"600"}'>
					<view :style='{"color":"#000","fontSize":"44rpx"}'>公告信息</view>
					<view :style='{"border":"0px solid #1ccb96","padding":"0 40rpx","margin":"0 auto","textAlign":"center","display":"block","right":"0px","top":"0px","borderRadius":"0px","background":"none","width":"auto","lineHeight":"80rpx","position":"absolute","zIndex":"999"}' @tap="onPageTap('news')">
					  <text :style='{"color":"#174add","fontSize":"30rpx"}'>更多</text>
					  <text class="icon iconfont icon-jiantou18" :style='{"color":"#174add","fontSize":"28rpx"}'></text>
					</view>
				</view>
				<!-- 样式7 -->
				<view class="news-box4" :style='{"width":"100%","padding":"0","margin":"0","fontSize":"26rpx","color":"#888","height":"auto"}'>
					<block v-for="(item,index) in news" :key="index">
						<view @tap="onNewsDetailTap(item.id)" v-if="index%2==0" class="list-item" :style='{"padding":"20rpx","margin":"0 0 40rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#f0f8ff","display":"flex","width":"100%","height":"auto"}'>
							<image :style='{"width":"200rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"180rpx"}' mode="aspectFill" class="listmpic" :src="baseUrl+item.picture"></image>
							<view class="list-item-body" :style='{"width":"calc(100% - 200rpx)","padding":"0","margin":"0","height":"auto"}'>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">{{item.title}}</view>
								<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#888","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="text">{{item.introduction}}</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.name}}</text>
								</view>
								<view :style='{"color":"#174add","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.thumbsupnum}}</text>
								</view>
								<view :style='{"color":"#ffc431","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.storeupnum}}</text>
								</view>
								<view :style='{"color":"#f34859","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan2" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.clicknum}}</text>
								</view>
							</view>
						</view>
						<view @tap="onNewsDetailTap(item.id)" v-if="index%2==1" class="list-item" :style='{"padding":"20rpx","margin":"0 0 40rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#f0f8ff","display":"flex","width":"100%","height":"auto"}'>
							<view class="list-item-body" :style='{"width":"calc(100% - 200rpx)","padding":"0","margin":"0","height":"auto"}'>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title ">{{item.title}}</view>
								<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#888","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="text">{{item.introduction}}</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.name}}</text>
								</view>
								<view :style='{"color":"#174add","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.thumbsupnum}}</text>
								</view>
								<view :style='{"color":"#ffc431","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.storeupnum}}</text>
								</view>
								<view :style='{"color":"#f34859","padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan2" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5"}'></text>
									<text :style='{"lineHeight":"1.5"}'>{{item.clicknum}}</text>
								</view>
							</view>
							<image :style='{"width":"200rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"180rpx"}' mode="aspectFill" class="listmpic" :src="baseUrl+item.picture"></image>
						</view>
					</block>
				</view>
			</view>
			<!-- 新闻资讯 -->
			<view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx","zIndex":"9999"}'>
				<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
			</view>
		</view>
	</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				startX: 0,
				prevNumList4: '',
				numList4: 0,
				timerList4: null,
				flagList4: false,
				navigationActive: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#23baf7","height":"16rpx"},
				navigationDefault: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"},
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
					'cuIcon-same',
					'cuIcon-deliver',
					'cuIcon-evaluate',
					'cuIcon-shop',
					'cuIcon-ticket',
					'cuIcon-cascades',
					'cuIcon-discover',
					'cuIcon-question',
					'cuIcon-pic',
					'cuIcon-filter',
					'cuIcon-footprint',
					'cuIcon-pulldown',
					'cuIcon-pullup',
					'cuIcon-moreandroid',
					'cuIcon-refund',
					'cuIcon-qrcode',
					'cuIcon-remind',
					'cuIcon-profile',
					'cuIcon-home',
					'cuIcon-message',
					'cuIcon-link',
					'cuIcon-lock',
					'cuIcon-unlock',
					'cuIcon-vip',
					'cuIcon-weibo',
					'cuIcon-activity',
					'cuIcon-friendadd',
					'cuIcon-friendfamous',
					'cuIcon-friend',
					'cuIcon-goods',
					'cuIcon-selection'
				],
				role : '',
				menuList: [],
				swiperMenuList:[],
				user: {},
				tableName:'',
				menuColor: '#6580a6,#548aff,#ffc431,#ff6161,#4f91ff,#ffbd65,#0fd38c,#52c9e1,#6580a6,#548aff'.split(','),

				//轮播
				swiperList: [],
				zixishilist: [],
				homeshiwuzhaolinglist: [],
				news: [],
				scrollTop: 0,
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
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
		async onLoad(){
			this.menuColor = this.menuColor.sort(()=> {
				return (0.5-Math.random());
			});
		},
		async onShow() {
			if (this.timerList4&&this.timerList4!=null) clearInterval(this.timerList4)
			this.swiperMenuList = []
			this.role = uni.getStorageSync("appRole");
			let table = uni.getStorageSync("nowTable");
			let res = {}
			if(table) {
				res = await this.$api.session(table);
				this.user = res.data;
				this.tableName = table;
			}
			let menus = menu.list();
			this.menuList = menus;
			this.menuList.forEach((item,key) => {
				if(key==0) {
					item.frontMenu.forEach((item2,key2) => {
						if(item2.child[0].buttons.indexOf("查看")>-1) {
							this.swiperMenuList.push(item2);
						}
					})
				}
			})
			// let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.list('config', {
				page: 1,
				limit: 5
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
						title: item.name,
						url: item.url
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			
			this.prevNumList4 = this.swiperList.length - 1
			this.timerList4 = setInterval(this.autoPlayList4, 5000)

			// 推荐信息
			this.getRecommendList()
			this.getHomeList()
			this.getNewsList()
		},
		methods: {
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
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
			newsTabClick2(index){
				this.newsIndex2 = index
				this.getNewsList()
			},
			async getNewsList(){
				let res;
				let params = {
					page: 1,
					limit: 4,
					sort: 'id',
					order: 'desc',
				}
				// 公告信息
				res = await this.$api.list('news', params)
				this.news = res.data.list
			},
			homeTabClick2(index,name){
				this['home' + name + 'Index2'] = index
				this.getHomeList()
			},
			async getHomeList(){
				let res;
				let params;
				params = {
					page:1,
					limit: 5,
				}
				res = await this.$api.list('shiwuzhaoling', params);
				this.homeshiwuzhaolinglist = res.data.list
			},
			recommendTabClick2(index,name){
				this[name + 'Index2'] = index
				this.getRecommendList()
			},
			async getRecommendList(){
				let res;
				let params;
				// 推荐信息
				params = {
					page: 1,
					limit: 6,
				}
				if(uni.getStorageSync("appUserid")) {
					res = await this.$api.recommend2('zixishi', params);
				} else {
					res = await this.$api.recommend('zixishi', params);
				}
				this.zixishilist = res.data.list
				

			},
			autoPlayList4() {
				this.prevNumList4 = this.numList4
			
				this.numList4++
				if (this.numList4 == this.swiperList.length) this.numList4 = 0
			},
			touchStart(event) {
				this.startX = event.touches[0].clientX
				
				clearInterval(this.timerList4)
				this.flagList4 = true
			},
			touchMove(event) {
				const currentX = event.touches[0].clientX;
				const deltaX = currentX - this.startX;
				
				if (deltaX > 50) {
					// 向右滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4++
						if (this.numList4 == this.swiperList.length) this.numList4 = 0
					}
					
				} else if (deltaX < -50) {
					// 向左滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4--
						if (this.numList4 < 0) this.numList4 = this.swiperList.length - 1
					}
				}
			},
			touchEnd() {
				this.startX = 0
				this.timerList4 = setInterval(this.autoPlayList4, 5000)
				this.flagList4 = false
			},
			//轮播图跳转
			onSwiperTap(e) {
				if(e.url) {
					if (e.url.indexOf('https') != -1) {
						// #ifdef MP-WEIXIN
						uni.navigateTo({
						    url: '../../common/linkOthers/linkOthers?url=' + encodeURIComponent(e.url),
						});
						return false
						// #endif
						window.open(e.url)
					} else {
						this.$utils.jump(e.url)
					}
				}
			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			onPageTap(tableName){
				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
			onPageTap2(index) {
				let url = '../' + index + '/list'
				if(index=='forum'){
					url = '../forum-index/forum-index'
				}
				if(index=='kefuguanli') {
					url = '../chat/chat'
				}
				uni.setStorageSync("useridTag",0);
				uni.navigateTo({
					url: url,
					fail: function() {
						uni.switchTab({
							url: url
						});
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.list-swiper-4 .animate__animated {
		--animate-delay: 500ms;
	}

	.menu_view {
		border-radius: 20rpx;
		padding: 0;
		margin: 20rpx auto 0;
		background: none;
		display: flex;
		width: calc(100% - 48rpx);
		flex-wrap: wrap;
		height: auto;
		.menu-item {
			border-radius: 8rpx;
			padding: 12rpx 0;
			margin: 10rpx 0px;
			width: calc(25% - 0px);
			height: auto;
			.iconarr {
				border-radius: 20rpx;
				padding: 0;
				margin: 0px auto;
				color: #fff;
				background: none;
				display: block;
				width: 96rpx;
				font-size: 96rpx;
				line-height: 96rpx;
				text-align: center;
				height: 96rpx;
			}
			.text {
				padding: 0;
				margin: 12rpx auto 0;
				color: #333;
				width: 100%;
				font-size: 26rpx;
				line-height: 28rpx;
				text-align: center;
			}
		}
	}
</style>
