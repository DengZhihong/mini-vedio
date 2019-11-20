<template>
	<view class="login">
		<view class="cu-card case" :class="isCard?'no-card':''">
			<view class="cu-item shadow" >
				<h1 style="text-align: center;">登录</h1>
			</view>
			<view class="cu-item shadow">
				<form @submit="doLogin">
					<view class="cu-form-group margin-top">
						<view class="title">账号</view>
						<input placeholder="请输入账号" name="username"></input>
					</view>
					<view class="cu-form-group">
						<view class="title">密码</view>
						<input password placeholder="请输入密码" name="password"></input>
					</view>
					<view class="padding flex flex-direction" style="letter-spacing:8px;">
						<button form-type="submit" class="cu-btn lg" style="background-color: #1CBBB4;color: white;">登陆</button>
						<button @click="toRegist" class="cu-btn bg-red margin-tb-sm lg">注册</button>
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
				isCard: false
			}
		},
		methods: {
			toRegist() {
				uni.navigateTo({
					url:"../regist/regist"
				})
			},
			doLogin(e) {
				var user = e.detail.value;
				if (user.length == 0 || user.password == 0) {
					uni.showToast({
						icon: "none",
						mask: true,
						title: "用户名或密码不能为空"
					})
				} else {
					var serverUrl = this.globaldata.serverUrl;
					uni.showLoading({
						title:"登录中"
					});
					uni.request({
						url: serverUrl + '/login',
						method: "POST",
						data: user,
						header: {
							'content-type': 'application/json'
						},
						success(res) {
							console.log("注册登录信息",res.data);
							var status = res.data.status;
							if (status == 200) {
								uni.showToast({
									icon: "success",
									mask: true,
									title: "登录成功",
								})
								getApp().globalData.userInfo = res.data.data;
								//TODO页面跳转
								uni.navigateTo({
									url:"../mine/mine"
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
				}
			}
		}
	}
</script>

<style>
	.login {
		display: flex;
		flex-direction: column;
		justify-content: center;
		height: 100vh;
	}
</style>
