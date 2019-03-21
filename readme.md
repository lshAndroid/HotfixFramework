##前提：
	软件迭代维护过程中，项目突然准备切换热修复框架，但是官方项目直接配置在module中导致杂乱更换很不方便，通过本项目二次封装就可以实现快速移除bugly，或者更换插件化方案。尤其是各种build.gradle,清单文件的添加,导致项目很容易出现冗余.
## 项目结构：
```HotfixFramework
	- libtinker（封装的tinker库，并且已添加混淆规则）
	- app(Android Demo工程）
	pradle.properties（集中管理版本）
```

注：本项目采用As 3.3.3新建项目，对bugly的tinker做了二次封装。更加详细内容请参考官方文档http://bugly.qq.com/androidfast

----tinker调用版本方案:
compile 'com.tencent.bugly:crashreport_upgrade:1.3.6'
compile 'com.tencent.tinker:tinker-android-lib:1.9.9'
常见问题：
  apkdemo在apktest文件夹中

<img src="https://github.com/lshAndroid/HotfixFramework/blob/master/image/image1.jpg" width="400px" height="800px"/>
<img src="https://github.com/lshAndroid/HotfixFramework/blob/master/image/image2.jpg" width="400px" height="800px"/>

4. 可能引用了较新的gradle版本，可以修改成低版本
如：
(1)添加gradle.properties版本号:
```
#热修复（开始）
#使用app唯一的包名
FILEPROVIDER_AUTHORITIES=报名+.fileProvider
BUGLY_ID=官网软件id
#bugly版本配置+tinker版本配置
BUGLY_CRASHREPORT_UPGRADE=1.3.6
BUGLY_TINKER_SUPPORT=1.1.5
TINKER_VERSION=1.9.9
MUTIDEX_VERSION=1.0.2
#后续版本未依赖
BUGLY_NATIVECRASHREPORT=3.3.1
#热修复（结束）
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
pro_boolean_minifyEnabled=false
pro_cotlin_version=1.2.71
butterknife_version=8.5.1

pro_timber=4.5.1

```
(2)在project中添加:
```
dependencies {
        classpath 'com.android.tools.build:gradle:3.3.2'
        
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        //热修复功能添加
        classpath "com.tencent.bugly:tinker-support:${BUGLY_TINKER_SUPPORT}"
    }
```
(3)在module中添加依赖
```
dependencies {
        classpath 'com.android.tools.build:gradle:1.5.0'
    }
```
(4)在module中粘贴tinker-support.gradle文件

