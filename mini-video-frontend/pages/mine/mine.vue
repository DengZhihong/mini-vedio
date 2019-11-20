<template>
	<view>
		<view class="header">
			<view class="bg">
				<view class="box">
					<view class="box-hd">
						<view @click="uploadFace" class="avator">
							<!-- <img src="../../static/user/face.jpg"> -->
							<img :src="faceImage">
						</view>
						<view class="phone-number">{{globaldata.userInfo.nickname}}</view>
						<view class="phone-number">
							<button size="mini" style="background-color: #1CBBB4;" type="primary">上传作品</button>
							<button size="mini" style="background-color: #e54d42;" @click="logout" type="primary">注销</button>
						</view>
					</view>
					<view class="box-bd">
						<view class="item">
							<!-- <view class="icon"><img src="../../static/user/message.png"></view>
							<view class="text">粉丝</view> -->
							<view class="cu-capsule">
								<view class='cu-tag bg-red'>
									粉丝
								</view>
								<view class="cu-tag line-red">
									{{globaldata.userInfo.fansCounts}}
								</view>
							</view>
						</view>
						<view class="item">
							<!-- <view class="icon"><img src="../../static/user/favorite.png"></view>
							<view class="text">关注</view> -->
							<view class="cu-capsule">
								<view class='cu-tag bg-pink'>
									关注
								</view>
								<view class="cu-tag line-pink">
									{{globaldata.userInfo.followCounts}}
								</view>
							</view>
						</view>
						<view class="item">
							<!-- 	<view class="icon"><img src="../../static/user/service.png"></view>
							<view class="text">获赞</view> -->
							<view class="cu-capsule">
								<view class='cu-tag bg-cyan'>
									获赞
								</view>
								<view class="cu-tag line-cyan">
									{{globaldata.userInfo.receiveLikeCounts}}
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="mine-content">
			<!-- <button type="primary" style="background-color: #1CBBB4;">上传作品</button>
			<button type="primary" style="background-color: #e54d42;">注销</button> -->
		</view>
	</view>
</template>
<script>
	export default {
		onLoad() {
			var serverUrl = getApp().globalData.serverUrl;
			var userInfo = getApp().globalData.userInfo;
			if (userInfo.userToken == null || userInfo.userToken == '') {
				uni.reLaunch({
					url: "../login/login"
				})
			}
			uni.showLoading({
				title: "正在加载"
			});
			uni.request({
				url: serverUrl + "/user/" + userInfo.id,
				success(res) {
					var status = res.data.status;
					var data = res.data.data;
					if (res.status == 200) {
						console.log("mine onload", res.data)
						userInfo.faceImage = data.faceImage;
						userInfo.fansCounts = data.fansCounts;
						userInfo.followCounts = data.followCounts;
						userInfo.receiveLikeCounts = data.receiveLikeCounts;
						userInfo.nickname = data.nickname;
					} else if (status == 500) {
						uni.showToast({
							icon: "none",
							mask: true,
							title: data.msg
						})
					}
				},
				complete() {
					uni.hideLoading();
				}
			})
		},
		onShow() {
			this.globaldata = getApp().globalData;
		},
		data() {
			return {
				globaldata: null
			};
		},
		computed: {
			faceImage() {
				return this.globaldata.serverUrl + this.globaldata.userInfo.faceImage;
			}
		},
		methods: {
			uploadFace() {
				console.log("上传头像");
				var me = this;
				var serverUrl = me.globaldata.serverUrl;
				uni.showLoading({
					title: "正在上传"
				});
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (chooseImageRes) => {
						const tempFilePaths = chooseImageRes.tempFilePaths;
						uni.uploadFile({
							url: serverUrl + "/user/" + me.globaldata.userInfo.id + "/uploadFace", //仅为示例，非真实的接口地址
							filePath: tempFilePaths[0],
							name: 'file',
							success: (uploadFileRes) => {
								var data = JSON.parse(uploadFileRes.data);
								console.log(data);
								if (data.status == 200) {
									me.globaldata.userInfo.faceImage = data.data;
								} else if (data.status == 500) {
									uni.showToast({
										icon: "none",
										mask: true,
										title: data.msg
									})
								}
							},
							complete() {
								uni.hideLoading();
							}
						});
					}
				});
			},
			logout() {
				var serverUrl = this.globaldata.serverUrl;
				console.log(this.globaldata);
				uni.showLoading({
					title: "注销中"
				});
				uni.request({
					url: serverUrl + '/logout?userId=' + this.globaldata.userInfo.id,
					method: "POST",
					header: {
						'content-type': 'application/json'
					},
					success(res) {
						console.log("注销信息", res.data);
						var status = res.data.status;
						if (status == 200) {
							uni.showToast({
								icon: "success",
								mask: true,
								title: "注销成功",
							})
							getApp().globalData.userInfo = null;
							//TODO页面跳转
							uni.navigateTo({
								url: "../login/login"
							})
						} else if (status == 500) {
							uni.showToast({
								icon: "none",
								mask: true,
								title: res.data.msg
							})
						}
					},
					complete() {
						uni.hideLoading();
					}

				})
			},
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f1f1f1;
		font-size: 30upx;
	}

	.header {
		background: #fff;
		height: 290upx;
		padding-bottom: 110upx;

		.bg {
			width: 100%;
			height: 200upx;
			padding-top: 100upx;
			background-color: #1CBBB4;
		}
	}

	.box {
		width: 650upx;
		height: 300upx;
		border-radius: 20upx;
		margin: 0 auto;
		background: #fff;
		box-shadow: 0 5upx 20upx 0upx rgba(0, 0, 150, .2);

		.box-hd {
			display: flex;
			flex-wrap: wrap;
			flex-direction: row;
			justify-content: center;

			.avator {
				width: 160upx;
				height: 160upx;
				background: #fff;
				border: 5upx solid #fff;
				border-radius: 50%;
				margin-top: -80upx;
				overflow: hidden;

				img {
					width: 100%;
					height: 100%;
				}
			}

			.phone-number {
				width: 100%;
				text-align: center;
			}
		}

		.box-bd {
			display: flex;
			flex-wrap: nowrap;
			flex-direction: row;
			justify-content: center;

			.item {
				flex: 1 1 auto;
				display: flex;
				flex-wrap: wrap;
				flex-direction: row;
				justify-content: center;
				border-right: 1px solid #f1f1f1;
				margin: 15upx 0;

				&:last-child {
					border: none;
				}

				.icon {
					width: 60upx;
					height: 60upx;

					img {
						width: 100%;
						height: 100%;
					}
				}

				.text {
					width: 100%;
					text-align: center;
					margin-top: 10upx;
				}
			}
		}
	}

	.mine-content {
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
	}
</style>
