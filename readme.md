##前提：
	软件迭代维护过程中，项目突然准备切换热修复框架，但是官方项目直接配置在module中导致杂乱更换很不方便，通过本项目二次封装就可以实现快速移除bugly，或者更换插件化方案。尤其是各种build.gradle,清单文件的添加,导致项目很容易出现冗余.
## 项目结构：
```HotfixFramework
	- libtinker（封装的tinker库，并且已添加混淆规则）
	- app(Android Demo工程）
	pradle.properties（集中管理版本）
```

注：本项目采用As3.3.2新建项目，对bugly的tinker做了二次封装。更加详细内容请参考官方文档http://bugly.qq.com/androidfast

----tinker调用版本方案:
compile 'com.tencent.bugly:crashreport_upgrade:1.3.6'
compile 'com.tencent.tinker:tinker-android-lib:1.9.9'
常见问题：
  apkdemo在apktest文件夹中

<img src="https://github.com/lshAndroid/HotfixFramework/blob/master/image/image1.jpg" width="400px" height="800px"/>
<img src="https://github.com/lshAndroid/HotfixFramework/blob/master/image/image2.jpg" width="400px" height="800px"/>

好处:build.gradle的封装,以及代码和 androidmanifest的涉及代码封装,让主项目彻底脱离tinker的各种配置,同时修改编译只需要在一处gradle.properties修改方便快捷省时省事.
如：


(1)添加gradle.properties版本号:
```
######热修复（开始）
#PATCH_PATH:bakapk的基准包文件夹 APK_NAME为apk名字
PATCH_PATH=app-0321-19-44-37
APK_NAME=app-release

#软件app的bugly官网填写资料:包名和buglyid
FILEPROVIDER_AUTHORITIES=包名.fileProvider
BUGLY_ID=官网id
#bugly的tinker版本号配置
BUGLY_CRASHREPORT_UPGRADE=1.3.6
BUGLY_TINKER_SUPPORT=1.1.5
TINKER_VERSION=1.9.9
MUTIDEX_VERSION=1.0.2
TINKER_ENABLE=true
#后续版本未依赖
#BUGLY_NATIVECRASHREPORT=3.3.1
#####热修复（结束）



pro_compileSdkVersion = 26
pro_minSdkVersion = 16
pro_targetSdkVersion = 26
pro_versionCode = 3
pro_versionName = 1.0.8
pro_appcompatVersion =26.0.2
pro_constraint = 1.1.2
pro_junit = 4.12
pro_runner = 1.0.2
pro_espresso = 3.0.2


```



有用请点赞,谢谢!!
