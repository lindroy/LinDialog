# LinDialog

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
	        implementation 'com.github.Lindroy:LinDialog:1.0.0'
	}
```

3、在你的Application的onCreate()方法中初始化

```kotlin
        LinDialog.init(instance)
```

