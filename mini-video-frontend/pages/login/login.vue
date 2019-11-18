<template>
	<view class="login">
		<view class="cu-card case" :class="isCard?'no-card':''">
			<view class="cu-item shadow" >
				<h1 style="text-align: center;">登录</h1>
			</view>
			<view class="cu-item shadow">
				<form>
					<view class="cu-form-group margin-top">
						<view class="title">账号</view>
						<input placeholder="请输入账号" name="input"></input>
					</view>
					<view class="cu-form-group">
						<view class="title">密码</view>
						<input password placeholder="请输入密码" name="input"></input>
					</view>
					<view class="padding flex flex-direction" style="letter-spacing:8px;">
						<button class="cu-btn lg" style="background-color: #1CBBB4;color: white;">登陆</button>
						<button @click="toRegist" class="cu-btn bg-red margin-tb-sm lg">注册</button>
					</view>
				</form>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
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
