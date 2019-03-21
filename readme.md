##前提：
	软件迭代维护过程中，项目突然准备切换热修复框架，但是官方项目直接配置在module中导致杂乱更换很不方便，通过本项目二次封装就可以实现快速移除bugly，或者更换插件化方案。
## 项目结构：
```HotfixFramework
	- libtinker（封装的tinker库，并且已添加混淆规则）
	- app(Android Demo工程）
	pradle.properties（集中管理版本）
```

注：本项目采用As 3.3.3新建项目，对bugly的tinker做了二次封装。更加详细内容请参考官方文档http://bugly.qq.com/androidfast

常见问题：
1. 导入项目可能会提醒你的sdk没有对应的buildTools，可以将build.gradle的buildToolsVersion改为你本地的版本
如： buildToolsVersion "22.0.1"
2. 运行项目可能会因为没有配置cup架构导致无法安装，可以在build.gradle中配置ndk支持的架构
如：

 ``` 
 ndk {
            //设置支持的SO库架构
            abiFilters 'armeabi', 'x86'//, 'armeabi-v7a', 'x86_64', 'arm64-v8a'
        }
        
  ```
  
3. 可能依赖了较新的库，可以修改成你本地的版本
如：compile 'com.android.support:appcompat-v7:23.1.0'

4. 可能引用了较新的gradle版本，可以修改成低版本
如：
(1)添加gradle.properties版本号:
```
#热修复（开始）
#使用app唯一的包名
FILEPROVIDER_AUTHORITIES=lsh.com.hotfixframework
BUGLY_ID=32a518ed9f
#bugly版本配置+tinker版本配置
BUGLY_CRASHREPORT_UPGRADE=1.3.6
BUGLY_TINKER_SUPPORT=1.1.5
TINKER_VERSION=1.9.9
MUTIDEX_VERSION=1.0.2
#后续版本未依赖
BUGLY_NATIVECRASHREPORT=3.3.1
#热修复（结束）
pro_compileSdkVersion = 28
pro_minSdkVersion = 19
pro_targetSdkVersion = 28
pro_versionCode = 3
pro_versionName = 1.0.8
pro_appcompatVersion =28.0.0
pro_constraint = 1.1.3
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

