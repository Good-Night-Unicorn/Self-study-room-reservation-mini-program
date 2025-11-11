<template>
	<div class="home-content">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('zuoweiyuyue','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-zhangjie8"></span>
				</div>
				<div class="right">
					<div class="num">{{zuoweiyuyueCount}}</div>
					<div class="name">座位预约总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('shiwuzhaoling','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-chujia3"></span>
				</div>
				<div class="right">
					<div class="num">{{shiwuzhaolingCount}}</div>
					<div class="name">失物招领总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
		<div id="news-box" class="news-box animate__animated">
			<div class="news-title">
				公告信息
			</div>
			<div class="news-list">
				<div class="news-item" v-for="(item,index) in newsList" :index="index" @click="newsDetailClick(item)">
					<div class="news-text">
						{{item.title}}
					</div>
					<div class="news-time">
						{{item.addtime}}
					</div>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="newsVisible" title="公告信息" :append-to-body="true" width="60%">
			<div style="width: 100%;text-align: center;font-size: 20px;color: #000;padding: 0 0 20px;font-weight: bold;">{{newsDetail.title}}</div>
			<div style="width: 100%;">
				<div class="ql-snow ql-editor" v-html="newsDetail.content"></div>
			</div>
		</el-dialog>
	
		<!-- echarts -->
		<!-- 2 -->
		<div class="type2">
			<div id="zuoweiyuyueChart1" class="echarts1 animate__animated" v-if="isAuth('zuoweiyuyue','首页统计')"></div>
			<div id="shiwuzhaolingChart1" class="echarts2 animate__animated" v-if="isAuth('shiwuzhaoling','首页统计')"></div>
		</div>
	</div>
</template>
<script>
import 'animate.css'
//2
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			zuoweiyuyueCount: 0,
			shiwuzhaolingCount: 0,
			line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#19406E","#507AFC","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"emptyCircle","symbolSize":4},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
			bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#6EADEF","#D6C1CA","#77A0CC","#6EADEF","#D6C1CA","#77A0CC","#6EADEF","#D6C1CA","#77A0CC"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
			pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#6EADEF","#D6C1CA","#77A0CC","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false},"top":"30%"}},
			funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			boardBase: {"funnelNum":10,"lineNum":10,"gaugeNum":8,"barNum":10,"pieNum":10},
			gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
			newsList: [],
			newsDetail: {},
			newsVisible: false,
		};
	},
	mounted(){
		this.init();
		this.getzuoweiyuyueCount();
		if(this.isAuth('zuoweiyuyue','首页统计')){
			this.zuoweiyuyueChat1();
		}
		this.getshiwuzhaolingCount();
		if(this.isAuth('shiwuzhaoling','首页统计')){
			this.shiwuzhaolingChat1();
		}
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	computed: {
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__fadeInUp'},
				{id:'statis1',css:'animate__fadeInUp'},
				{id:'statis2',css:'animate__fadeInUp'},
				{id:'news-box',css:'animate__fadeInUp'},
				{id:'zuoweiyuyueChart1',css:'animate__fadeInUp'},
				{id:'shiwuzhaolingChart1',css:'animate__fadeInUp'},
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
		// 统计图动画
		myChartInterval(type, xAxisData, seriesData, myChart) {
			this.$nextTick(() => {
				setInterval(() => {
					let xAxis = xAxisData.shift()
					xAxisData.push(xAxis)
					let series = seriesData.shift()
					seriesData.push(series)
				
					if (type == 1) {
						myChart.setOption({
							xAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
					if (type == 2) {
						myChart.setOption({
							yAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
				}, $template2.back.board.bar.base.interval);
			})
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
			this.getNewsList()
		},
		getNewsList(){
			let params = {
				page: 1,
				limit: 6,
				sort: 'addtime',
				order: 'desc',
			}
			this.$http({
				url: `news/list`,
				method: "get",
				params: params
			}).then(res => {
				if(res.data&&res.data.code==0){
					this.newsList = res.data.data.list
				}
			});
		},
		newsDetailClick(row){
			this.newsDetail = row
			this.newsVisible = true
		},
		getzuoweiyuyueCount() {
			this.$http({
				url: `zuoweiyuyue/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.zuoweiyuyueCount = data.data
				}
			})
		},
// 1234 竖
		zuoweiyuyueChat1() {
			this.$nextTick(()=>{

				var zuoweiyuyueChart1 = echarts.init(document.getElementById("zuoweiyuyueChart1"),'macarons');
				this.$http({
					url: `zuoweiyuyue/value/reservationdate/feiyong`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].reservationdate);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].reservationdate
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '收入情况'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						zuoweiyuyueChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							zuoweiyuyueChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getshiwuzhaolingCount() {
			this.$http({
				url: `shiwuzhaoling/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.shiwuzhaolingCount = data.data
				}
			})
		},
// 1234 饼
		shiwuzhaolingChat1() {
			this.$nextTick(()=>{

				var shiwuzhaolingChart1 = echarts.init(document.getElementById("shiwuzhaolingChart1"),'macarons');
				this.$http({
					url: "shiwuzhaoling/group/zhuangtai",
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data;
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].zhuangtai);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].zhuangtai
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '失物情况'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: '55%',
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						shiwuzhaolingChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							shiwuzhaolingChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 60px 0px;
		margin: 20px 0 0 80px;
		background: url(http://codegen.caihongy.cn/20241212/c085feb1bcc943f3b8cc1b44b9839c59.png) no-repeat center top / cover;
		display: flex;
		min-height: 100vh;
		justify-content: flex-start;
		position: relative;
		flex-wrap: wrap;
		.home-title {
			border-radius: 5px;
			padding: 10px 0;
			box-shadow: 0 0px 0px rgba(0,0,0,.3);
			margin: 10px 0;
			display: none;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0 0 0 12px;
				color: #333;
				font-size: 24px;
				line-height: 44px;
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
			z-index: 1;
			background: rgba(255,255,255,.12);
		}
		.statis-box {
			border-radius: 10px;
			padding: 10px ;
			margin: 0 0 0 10px;
			background: none;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			order: 2;
			height: auto;
			.statis1 {
				border: 1px solid #0F1330;
				border-radius: 10px;
				padding: 10px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: 200px;
				transition: 0.3s;
				height: 90px;
				.left {
					border-radius: 100%;
					background: #e9f0f9;
					display: none;
					width: 66px;
					justify-content: center;
					align-items: center;
					height: 66px;
					.iconfont {
						color: #2582f3;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 160px;
					justify-content: center;
					align-items: center;
					.num {
						margin: 5px 0;
						color: #0F1330;
						font-weight: 500;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #666;
						font-size: 15px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis1:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis2 {
				border: 1px solid #0F1330;
				border-radius: 10px;
				padding: 10px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: 200px;
				transition: 0.3s;
				height: 90px;
				.left {
					border-radius: 100%;
					background: #fcece1;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #f56c17;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 160px;
					justify-content: center;
					align-items: center;
					.num {
						margin: 5px 0;
						color: #0F1330;
						font-weight: 500;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #666;
						font-size: 15px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis2:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis3 {
				border: 1px solid #0F1330;
				border-radius: 10px;
				padding: 0 10px 0px 10px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: 200px;
				transition: 0.3s;
				height: 90px;
				.left {
					border-radius: 100%;
					background: #e7fbfd;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #21c2f6;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 160px;
					justify-content: center;
					align-items: center;
					.num {
						margin: 5px 0;
						color: #0F1330;
						font-weight: 500;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #666;
						font-size: 15px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis3:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis4 {
				border: 1px solid #0F1330;
				border-radius: 10px;
				padding: 0 10px 0px 10px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: 200px;
				transition: 0.3s;
				height: 90px;
				.left {
					border-radius: 100%;
					background: #e0ffe9;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #45d45a;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 160px;
					justify-content: center;
					align-items: center;
					.num {
						margin: 5px 0;
						color: #0F1330;
						font-weight: 500;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #666;
						font-size: 15px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis4:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis5 {
				border: 1px solid #0F1330;
				border-radius: 10px;
				padding: 0 10px 0px 10px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: 200px;
				transition: 0.3s;
				height: 90px;
				.left {
					border-radius: 100%;
					background: #fbe8db;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #d26515;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 160px;
					justify-content: center;
					align-items: center;
					.num {
						margin: 5px 0;
						color: #333;
						font-weight: 500;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
					}
					.name {
						margin: 5px 0;
						color: #666;
						font-size: 15px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
				}
			}
			.statis5:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
		}
		.news-box {
			border: 0px solid #ccc;
			border-radius: 2px;
			padding: 20px 30px;
			box-shadow: 0 0px 0px rgba(0,0,0,.3);
			margin: 10px;
			flex-direction: column;
			background: #fff;
			display: none;
			width: calc(30% - 20px);
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.news-title {
				padding: 0 2px;
				margin: 0 0 20px;
				color: #000;
				font-weight: 600;
				width: 100%;
				font-size: 20px;
				border-color: #ddd;
				border-width: 0 0 2px;
				line-height: 44px;
				border-style: solid;
			}
			.news-list {
				flex-direction: column;
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: center;
			}
			.news-item {
				border: 1px solid #eee;
				padding: 10px 0;
				display: flex;
				width: 100%;
				border-width: 0 0 1px;
				justify-content: space-between;
				align-items: center;
				.news-text {
					color: #000;
					font-weight: 500;
					font-size: 14px;
				}
				.news-time {
					color: #999;
					font-size: 14px;
				}
			}
		}
		.news-box:hover {
			transform: translate3d(0, 0px, 0);
		}
		// echarts2
		.type2 {
			padding: 0;
			margin: 10px;
			align-content: flex-start;
			background: none;
			display: flex;
			width: 100%;
			justify-content: space-between;
			flex-wrap: wrap;
			height: auto;
			.echarts1 {
				border: 0px solid #ccc;
				border-radius: 10px;
				padding: 20px;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts1:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.echarts2 {
				border: 0px solid #ccc;
				border-radius: 10px;
				padding: 20px;
				margin: 10px;
				background: rgba(255,255,255,1);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts2:hover {
				transform: translate3d(0, 0px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
