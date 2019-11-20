<template>
	<view class="regist">
		<view class="cu-card case" :class="isCard?'no-card':''">
			<view class="cu-item shadow">
				<h1 style="text-align: center;">注册</h1>
			</view>
			<view class="cu-item shadow">
				<form @submit="doRegist">
					<view class="cu-form-group margin-top">
						<view class="title">账号</view>
						<input placeholder="请输入账号" name="username"></input>
					</view>
					<view class="cu-form-group">
						<view class="title">密码</view>
						<input password placeholder="请输入密码" name="password"></input>
					</view>
					<view class="padding flex flex-direction">
						<button class="cu-btn bg-grey lg" form-type="submit">注册</button>
						<button @click="toLogin" class="cu-btn bg-red margin-tb-sm lg">返回登录</button>
					</view>
				</form>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		onShow() {
			this.globaldata = getApp().globalData;
		},
		data() {
			return {
				isCard: false,
				globaldata: null
			}
		},
		methods: {
			toLogin() {
				uni.navigateTo({
					url: "../login/login"
				})
			},
			doRegist(e) {
				var user = e.detail.value
				var globaldata = this.globaldata;
				if (user.length == 0 || user.password == 0) {
					uni.showToast({
						icon: "none",
						mask: true,
						title: "用户名或密码不能为空"
					})
				} else {
					var serverUrl = this.globaldata.serverUrl;
					uni.showLoading({
						title: "注册中"
					})
					uni.request({
						url: serverUrl + '/regist',
						method: "POST",
						data: user,
						header: {
							'content-type': 'application/json'
						},
						success(res) {
							console.log("注册返回信息", res.data);
							var status = res.data.status;
							if (status == 200) {
								uni.showToast({
									icon: "none",
									mask: true,
									title: "注册成功"
								})
								globaldata = res.data.data;
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
				}
			}

		}
	}
</script>

<style>
	.regist {
		display: flex;
		flex-direction: column;
		justify-content: center;
		height: 100vh;
	}
</style>
