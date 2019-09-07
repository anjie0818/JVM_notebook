### 运行参数分类
* 标准参数(使用java -help查看所有标准参数)
    * -version
    * -help
```java   
#编译
[root@node01 test]# javac TestJVM.java
#测试
[root@node01 test]# java TestJVM
itcast
[root@node01 test]# java ‐Dstr=123 TestJVM
123
```
* -X参数（非标准参数，使用java -x查看所有非标准参数）
    * -Xint
        * 在解释模式(interpreted mode)下，-Xint标记会强制JVM执行所有的字节码，当然这会降低运行速度，通常低10倍或更多
    * -Xcomp
        * -Xcomp参数与它（-Xint）正好相反，JVM在第一次使用时会把所有的字节码编译成本地代码，从而带来最大程度的优化。
            * 然而，很多应用在使用-Xcomp也会有一些性能损失，当然这比使用-Xint损失的少，原因是-xcomp没有让JVM启用JIT编译器的全部功能。JIT编译器可以对是否需要编译做判断，如果所有代码都进行编译的话，对于一些只执行一次的代码就没有意义了
    * -Xmixed
        * 是混合模式，将解释模式与编译模式进行混合使用，由jvm自己决定，这是jvm默认的模式，也是推荐使用的模式    
```jshelllanguage
#强制设置为解释模式
[root@node01 test]# java ‐showversion ‐Xint TestJVM
java version "1.8.0_141"
Java(TM) SE Runtime Environment (build 1.8.0_141‐b15)
Java HotSpot(TM) 64‐Bit Server VM (build 25.141‐b15, interpreted mode)
itcast
#强制设置为编译模式
[root@node01 test]# java ‐showversion ‐Xcomp TestJVM
java version "1.8.0_141"
Java(TM) SE Runtime Environment (build 1.8.0_141‐b15)
Java HotSpot(TM) 64‐Bit Server VM (build 25.141‐b15, compiled mode)
itcast
#注意：编译模式下，第一次执行会比解释模式下执行慢一些，注意观察。
#默认的混合模式
[root@node01 test]# java ‐showversion TestJVM
java version "1.8.0_141"
Java(TM) SE Runtime Environment (build 1.8.0_141‐b15)
Java HotSpot(TM) 64‐Bit Server VM (build 25.141‐b15, mixed mode)
itcast
```
* -XX参数（使用率高的非标准参数）
    * -XX参数也是非标准参数，主要用于jvm的调优和debug操作。
    * -XX参数的使用有2种方式，一种是boolean类型，一种是非boolean类型：
        * boolean类型
            * 格式：-XX:[+-]
            * 如：-XX:+DisableExplicitGC 表示禁用手动调用gc操作，也就是说调用System.gc()无效
        * 非boolean类型
            * 格式：-XX:
            * 如：-XX:NewRatio=1 表示新生代和老年代的比值
```jshelllanguage
[root@node01 test]# java ‐showversion ‐XX:+DisableExplicitGC TestJVM
java version "1.8.0_141"
Java(TM) SE Runtime Environment (build 1.8.0_141‐b15)
Java HotSpot(TM) 64‐Bit Server VM (build 25.141‐b15, mixed mode)
itcast
``` 
* -Xms与-Xmx参数
    * -Xms与-Xmx分别是设置jvm的堆内存的初始大小和最大大小。
        * -Xmx2048m：等价于-XX:MaxHeapSize，设置JVM最大堆内存为2048M。
        * -Xms512m：等价于-XX:InitialHeapSize，设置JVM初始堆内存为512M。
        * 适当的调整jvm的内存大小，可以充分利用服务器资源，让程序跑的更快。
```jshelllanguage
[root@node01 test]# java ‐Xms512m ‐Xmx2048m TestJVM
itcast
```
### 查看JVM运行参数
#### 运行java命令时候打印参数
* 运行java命令时打印参数，需要添加-XX:+PrintFlagsFinal参数即可
    * eg:[root@node01 test]# java ‐XX:+PrintFlagsFinal ‐version
#### 查看正在运行的java进程的参数
* 如果想要查看正在运行的jvm就需要借助于jinfo命令查看。