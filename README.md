# MVPDemo

[![Badge](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu/#/zh_CN)

mvp 框架

* 其中base 下为普通版mvp 

* baseimpl 为进阶版，添加中间层，简化base层和业务层，避免业务层过多无用的实现
* login 为mvp 演示代码
* test 下为分离view版，分离了view 和activity，但在实际开发中，业务层无法避免要和activity 做各种交互，各种回调，暂不推荐使用。
* test_json 为 处理json 预处理相关代码
* test_multiple 为多 url 处理相关代码
* downfile 为文件上传、下载相关代码

* [【Android架构】基于MVP模式的Retrofit2+RXjava封装（一）](https://www.jianshu.com/p/bf1106b339c7)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之文件下载（二）](https://www.jianshu.com/p/f5d82c2b5431)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之文件上传（三）](https://www.jianshu.com/p/6ccdec4f3dd2)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之常见问题（四）](https://www.jianshu.com/p/f59d8aeaf3c0)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之断点下载（五）](https://www.jianshu.com/p/36a689abc20c)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之数据预处理（六）](https://www.jianshu.com/p/bc7dc3b67c5d)
* [【Android架构】基于MVP模式的Retrofit2+RXjava封装之多Url（七）](https://www.jianshu.com/p/da8a01d4548a)

# 常见问题
* 1.为啥后台返回的json都拿到了，还走的`onError` ？

请检查`BaseResponseBodyConverter` 中的判断逻辑是否和自己项目中实际返回的`JSON` 格式一致。

* 2.`JSON`预处理是否有必要？

预处理相当于解析了2遍`JSON`,如果后台返回`JSON`格式固定，则没有必要；如果后台返回`JSON`格式比较混乱，还是有必要的。





