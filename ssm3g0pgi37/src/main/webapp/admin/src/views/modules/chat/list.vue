<template>
	<div class="main-content" :style='{"padding":"60px 30px","margin":"0 0 0 65px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<div v-if="!showFlag" style="width: 100%;">
			<el-form :style='{"margin":"0 10px 0px"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"width":"100%","padding":"10px","flexWrap":"wrap","background":"none","display":"flex"}'>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"border":"1px solid #CBCBCB","padding":"0 10px","margin":"0 10px 0 0","color":"#000","textAlign":"center","display":"inline-block","borderRadius":"10px","background":"#fff","width":"180px","lineHeight":"34px","fontSize":"inherit","fontWeight":"500","height":"34px"}' class="item-label">是否回复</label>
						<el-select v-model="searchForm.isreply" placeholder="请选择">
							<el-option label="已回复" :value="0"></el-option>
							<el-option label="未回复" :value="1"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"border":"1px solid #CBCBCB","padding":"0 10px","margin":"0 10px 0 0","color":"#000","textAlign":"center","display":"inline-block","borderRadius":"10px","background":"#fff","width":"180px","lineHeight":"34px","fontSize":"inherit","fontWeight":"500","height":"34px"}' class="item-label">是否已读</label>
						<el-select v-model="searchForm.isread" placeholder="请选择">
							<el-option label="已读" :value="1"></el-option>
							<el-option label="未读" :value="0"></el-option>
						</el-select>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 10px","color":"#0F1330","borderRadius":"5px","background":"#AFD0F5","width":"auto","fontSize":"16px","height":"34px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#0F1330","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>
			</el-form>
			<div>
				<el-table
					:stripe='true'
					:style='{"padding":"0","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}'
					:data="dataList"
					:border='true'
					v-loading="dataListLoading"
					style="width: 100%;"
					>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="用户"
						>
						<template slot-scope="scope">
							<div style="display: flex;align-items: center;">
								<img :src="scope.row.uimage?$base.url + scope.row.uimage.split(',')[0]:require('@/assets/img/avator.png')" style="width: 80px;height: 80px;border-radius: 50%;margin: 0 5px 0 0;" alt="" />
								{{scope.row.uname}}
							</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="ask" :formatter="askFormat" header-align="center" align="center" sortable label="新消息"></el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="是否回复"
						width="150"
						>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.isreply==1" type="success">未回复</el-tag>
							<el-tag v-if="scope.row.isreply==0" type="info">已回复</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="allnode"
						header-align="center"
						align="center"
						sortable
						label="是否已读"
						width="150"
						>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.isread==1" type="success">已读</el-tag>
							<el-tag v-if="scope.row.isread==0" type="info">未读</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						header-align="center"
						align="center"
						width="150"
						label="操作"
						>
						<template slot-scope="scope">
							<el-button
								type="text"
								:style='{"border":"1px solid #D8D8D8","cursor":"pointer","padding":"0 10px","margin":"0 5px 5px 0","color":"#000000","borderRadius":"2px","background":"none","width":"auto","fontSize":"14px","fontWeight":"400","height":"32px"}'
								size="small"
								@click="addOrUpdateHandler(scope.row)"
							>回复</el-button>
						</template>
					</el-table-column>
				</el-table>
			
				<el-pagination
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
					:current-page="pageIndex"
					:page-sizes="[10, 50, 100, 200]"
					:page-size="pageSize"
					:total="totalPage"
					:layout="layouts.join()"
					prev-text="<"
					next-text=">"
					:hide-on-single-page="false"
					:style='{"border":" 0px solid #F1F1F1","padding":"0","margin":"20px auto 0","whiteSpace":"nowrap","color":"#000000","textAlign":"center","display":"flex","justifyContent":"space-between","borderRadius":"5px","background":"none","width":"500px","fontSize":"12","fontWeight":"400","height":"50px"}'
				></el-pagination>
			</div>
		</div>
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
	</div>
</template>
<script>
	import AddOrUpdate from "./chat-add-or-update";
	import { setInterval, clearInterval } from 'timers';
	export default {
		data() {
			return {
				layouts: ["prev","pager","next","sizes"],
				searchForm: {
					isreply:1,
					isread: 0
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				showFlag: false,
				dataListSelections: [],
				inter: null,
			};
		},
		created() {
			var that = this;
			that.getDataList();
			var inter = setInterval(function(){
				that.getDataList();
			},5000);
			this.inter = inter;
		},
		destroyed(){
			clearInterval(this.inter);
		},
		components: {
			AddOrUpdate
		},
		methods: {
			askFormat(row, column) {
				if (row.ask && row.type==2) {
					return '[图片]'
				} else if(row.ask&&row.type==3) {
					return '[视频]'
				} else if(row.ask&&row.type==4) {
					return '[文件]'
				} else {
					return row.ask
				}
			},
			search() {
				this.pageIndex = 1
				this.getDataList()
			},
			getDataList() {
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
				}
				params.isreply = this.searchForm.isreply
				params.isread = this.searchForm.isread
				this.dataListLoading = true;
				this.$http({
					url: this.$api.chatpage,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 回复
			addOrUpdateHandler(row) {
				this.showFlag = true;
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(row.userid);
				});
			}
		}
	};
</script>
<style lang="scss" scoped>
	.table-content {
		background: transparent;
	}
	
	.center-form-pv .el-select {
				width: 100%;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 1px solid #ddd;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				width: 170px;
				font-size: 16px;
				height: 34px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 10px;
				background: #EEEEEE;
				border-color: #eee;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 0 0 5px;
				word-wrap: normal;
				color: #000000;
				white-space: normal;
				font-weight: 400;
				display: flex;
				vertical-align: middle;
				font-size: 14px;
				line-height: 24px;
				text-overflow: ellipsis;
				word-break: break-all;
				width: 100%;
				align-items: center;
				position: relative;
				min-width: 110px;
			}
	
	
	.el-table /deep/ .el-table__body-wrapper {
			}
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding:  0px;
				color: #9E9E9E;
				background: #fff;
				font-size: inherit;
				border-color: #eee;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr.el-table__row--striped td {
		background: #f8f8f8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 0;
				color: #333;
				background: #f0f0f0;
				border-color: #eee;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding:  0px;
				color: #9E9E9E;
				background: #fff;
				font-size: inherit;
				border-color: #eee;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 0 0 5px;
				overflow: hidden;
				color: #666;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				vertical-align: top;
				font-size: inherit;
				line-height: 48px;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 50%;
				padding: 0;
				margin: 0 5px;
				color: #fff;
				background: #0F1330;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				min-width: 35px;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				padding: 0;
				margin: 0 5px;
				color: #fff;
				display: block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				border-radius: 50%;
				background: #0F1330;
				width: 9%;
				min-width: 35px;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				border-radius: 50%;
				background: #0F1330;
				width: 9%;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				border-radius: 50%;
				background: #0F1330;
				width: 9%;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: flex;
				vertical-align: top;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 20px;
				color: #000000;
				background: none;
				font-weight: 400;
				display: flex;
				vertical-align: top;
				font-size: 16px;
				line-height: 100%;
				align-items: center;
				text-align: center;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 20px;
				color: #FFFFFF;
				font-weight: 400;
				display: flex;
				vertical-align: top;
				font-size: 16px;
				line-height: 100%;
				border-radius: 50%;
				background: #6EADEF;
				align-items: center;
				text-align: center;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: pointer;
				padding: 0 20px;
				color: #FFFFFF;
				font-weight: 400;
				display: flex;
				vertical-align: top;
				font-size: 16px;
				line-height: 100%;
				border-radius: 50%;
				background: #6EADEF;
				align-items: center;
				text-align: center;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: none;
				vertical-align: top;
				font-size: 16px;
				line-height: 48px;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 16px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 48px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 48px;
				height: 48px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 14px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 16px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
</style>
