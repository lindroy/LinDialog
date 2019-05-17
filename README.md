# LinDialog
[![](https://jitpack.io/v/Lindroy/LinDialog.svg)](https://jitpack.io/#Lindroy/LinDialog)


使用Kotlin语言编写的的对话框。

## 配置方法

1、在工程的build.gradle中添加仓库地址：

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2、dependencies中添加：

```
	dependencies {
	        implementation 'com.github.Lindroy:LinDialog:latest-version'
	}
```
latest-version请参考jitpack图标后的版本号。

3、在你的Application的onCreate()方法中初始化

```kotlin
	LinDialog.init(instance)
```

## 使用方法
请参考代码中的例子和阅读[wiki](https://github.com/Lindroy/LinDialog/wiki "wiki")。

## 待优化
屏幕旋转后由于状态没有保存，会导致对话框布局异常或页面闪退，目前可在清单文件中给对话框所在的Activity添加：
```
	android:configChanges="orientation|screenSize"
```

## 特别鸣谢
[LDialog](https://github.com/limuyang2/LDialog "LDialog")


