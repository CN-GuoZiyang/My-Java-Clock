源文件为MainFrame.java

我的一个挺垃圾的Java小闹钟，初次使用swing编写的，一边写一边百度……

可以说是一次探索吧

**不适合Windows**

**不适合Windows**

**不适合Windows**

mac下编译运行：

```shell
javac -cp jl1.0.1.jar MainFrame.java
java -cp .:jl1.0.1.jar MainFrame
```

~~windows下编译运行：~~

```shell
javac -cp jl1.0.1.jar -encoding UTF-8 MainFrame.java
java -cp .;jl1.0.1.jar MainFrame
```

设置中可以更改自己的闹铃，但是默认闹铃炒鸡好听！

调用了第三方的mp3播放器jl1.0.1.jar

虽然我写的很垃圾很简陋，但记得给star哦！

已知bug：

​	闹钟响铃时，主界面上时间停止，直到关闭闹钟后才继续运行。

​	分析：可能和线程相关，但我不会改

重大问题：

​	由于不可抗力原因，

​	此闹钟不适合Windows，仅适合Linux和Mac

​	Windows版本后续开发

