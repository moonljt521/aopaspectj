# 基于aspectJ 的AOP注解工具库

    
# 使用

    可以在方法上直接用@注解方式使用
    
    例如
    @NoDoubleClickTrace
    @Override
    public void onClick(View view) {

        Log.i(TAG,"click.........");

    }
    
    可以防止此按钮被连点，让你的业务逻辑因此产生错误
    
    除此之外
    还提供了
        
        @AopDebugLog    // 打印日志

        @CheckLoginTrace    // 检查登录状态 ，因为此方法与您的业务耦合，具体实现需要您自己来做，位置在 CheckLoginUtil.java 中

        @ExecuteDuringTimeTrace    // 打印某个方法的执行时间

        @PermissionTrace     // 检查权限


# 引用方式，直接clone源码，或者采用直接依赖方式
	
	1 在你的项目根目录gradle文件里设置

	buildscript 
	{
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        // 添加此行
        classpath 'com.uphyca.gradle:gradle-android-aspectj-plugin:0.9.14'

    }
	}

	2 在需要使用注解的module里（例如app）的build.gradle中设置

	顶部添加 apply plugin: 'com.uphyca.android-aspectj'

	dependencies 中添加
		compile 'com.moon.aopaspectj:aspectjlib:1.0.1'

