1、目前使用的技术有html,css,javascript,servlet,jsp,jdbc,easyui框架(这个是看视频做的，粘贴别人做好的框架)\
2、根据学习进度，先做了html，实现了发送信息给servlet和servlet转发，\
为了实现验证码，再把html转换成jsp,所以这里的html文件是没用的，但是可以当作学习Html
实现验证码时候发现：
前端的图片需要刷新，刷新img标签一个好方法就是把src换了
后端需要通过一个Servelt把类实现，才可以把新图片流传给前端，注意是流，不是保存本地，是保存服务器之间的缓冲流。
然后还要把验证码的信息存储在session，登录的时候传给后端判断，注意不可以直接在前端判断，我原来这样想，但是后来想起javascript不能访问session
文件夹中的图片是运行生成验证码的样品，但是我们肯定用的时候要刷新换新。\
3、学习了监听器和拦截器的功能,可以分开普通用户和管理人员\
4、准备学习上传图片,导入依赖jar包:\
 commons-fileUpload.jar\
 commons-io.jar\
 5、学习jdbc和java的时间转换。String->Date(java)->Date(oracle)
直接把这个apache-tomcat-9.0.35文件夹放到D:\tomcat-9的目录下就可以了，用浏览器打开，先使用http://localhost:8888/hello/jsp/login.jsp,后面的就可以直接跳转，查看各种数据
给2个使用身份：
    账户 名字 密码  身份
 	7    7    7    common普通用户
	123  123  123  specail管理员
	且都有借书记录，方便老师查看本次作业
在大的压缩包内一个wbd.sql是本次作业的数据库文件。
