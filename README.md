# Blood And Glory(BAG) for Minecraft 1.12.2

Blood And Glory是一个Minecraft1.12.2的Mod，里面实现了类似于中土世界和《骑马与砍杀》风格的世界
顺便实现1.13和1.14的部分内容  
  
在这片广袤的大陆上，你可以尽情实现你的伟大事业和雄心壮志。是加入一方势力，为他们效力，建功立业？
还是落草为寇，劫富济贫？还是利用金钱和指挥在世间组织自己的雇佣军团，逐鹿中原？  
你可以在幽深的地下探索宝藏，与矮人相遇；你可以组织军团，抢劫商队；你可以钻研魔法，成为
万众敬仰的法师；你可以扬帆出海，探索神秘的东方；你可以……  
  
### Build
我们目前正在努力开发1.0版本，直到那个版本出来我们才会正式发布，如果你很想试玩，请参考以下步骤：

1. 选择分支： 已经确认你在**正确**的分支上：  
    1. `master`分支表示这是一个稳定版本  
    2. `develop`分支表示这是一个正在研发的可构建版本
    3. `feature`分支表示这是一个最新版本，但不保证它能够正常构建  
2. 构建：将本项目(**请确保你已经选择了正确分支！！！**)下载解压后，在本项目的根目录(可以看到`build.gradlew`文件 )
打开命令行输入`./gradlew build`  
3.在`/build/libs/`下选择jar文件(如果文件含有`sources`，请不要使用**那个**文件)
  
祝您游玩愉快:)  
    
### PR规范
   抱歉，我们目前只合并以下PR:  
     
 - Bug修改建议(用`[bugFix]`开头)  
 - Lang文件修改、翻译(用`[Lang]`开头)  
 - 如果允许的话，你可以贡献你绘制的材质(用`[asstes]`开头)  
   
其余的PR不合并，如果你对玩法有什么建议的话，欢迎在issues提出 

###作者：  
- 技术实现:Yaesey
- 材质制作：Black Fish
- 文化顾问：Black Fish
- 创意指导：Black Fish
- 军事顾问以及其他：Black Fish
(没错，就连要制作这个Mod的想法都是Black Fish提出来的，虽然Black Fish不插手技术)