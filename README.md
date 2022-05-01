# 单机游戏 Witch Hunt
改编自同名桌游，二人课程项目， 游戏规则附于pdf，可以添加虚拟玩家
## 代码介绍
主要分为五个部分：
- gamebody: 包括组成游戏框架的组件，如弃牌区DisCardArea, 示牌区RevealedCardArea
- cards & cardEffects: 卡牌以及牌效的实现
- players & behavior: 对应玩家和玩家的行为
- threads: 用两个线程分别监听命令行和图形界面的输入，实现交叉输入可能
- vue: 简单的GUI
### gamebody
- BroadCast: 单例，广播，可以向所有注册的玩家窗口发送消息
- DisCardArea: 单例，弃牌区，被弃的牌会被加入该类唯一对象的牌列表，允许从弃牌区取出牌
- GameController: 单例，能初始化游戏，控制游戏进程，为每一局创建一个新的RoundController
- Identity: 枚举类，表示玩家身份
- RevealedCardArea: 单例，示牌区，使用过的牌会被加入该类对象的表
- RoundController: 单例， 控制一轮游戏的进程，允许发牌，让玩家选身份，出局等操作
### cards & cardEffects
所有牌都继承自RumourCard, 所有牌效都继承自CardEffect
- RumourCard: 有‘牌名’，‘持有者’，‘witch效果’，‘hunt效果’，以及‘牌的特征描述’这些主要属性
- RumourCard的生效：每一个牌都储存了一系列对应的牌效，使用时依次执行对应的牌效
- CardEffect: 通过‘生效’方法来执行效果逻辑，需要的参数为‘行为’behavior
### players & behavior
每个玩家都有一个交互窗口对象，虚拟玩家也有特制的窗口，所有人机交互一定会通过窗口
- Charactor: 对应真实玩家，能够计分，得到卡，失去卡，进行自己的回合，对指控做出反应
- InteractionWindow: 真实玩家的交互窗口，被vue中的GUI观察，数据更改后会提示图形界面做出改变
- InputManager: 单例，为了实现命令行和图形界面交叉输入而使用的缓冲区，观察两条对应的线程，被请求读取输入后唤醒它们，并在得到数据后阻塞它们
- Virtualplaye & VirtualInteractionWindow: 继承自真实玩家和它的窗口，不再输出消息并且自动返回随机数
- behavoir: 由于有的牌效需要知道指控的动作发起人，用behavior来封装所有行为并且储存行为发起人和行为目标
### threads
- GraphMoniteur: 大部分时间阻塞，需要输入时被观察者(InputManager)唤醒，读到图形界面输入后告知观察者并再次被阻塞
- CliMoniteur: 大致逻辑同上，但是是每隔一定时间主动读取命令行，有输入则提醒观察者(InputManager)
### vue
借助Swing designer做的简易图形界面
- GameStartFrame: 游戏开始时输入游戏初始化信息的界面
- ChooseWindow: 用于玩家做选择的界面
- DiscardedCardFrame & RevealedCardFram: 弃牌区和示牌区对应的界面，对应的模型的观察者
- MainWindows: 玩家的主要界面，包括手牌，消息区，打开弃牌区和示牌区的按钮，比分板。点击手牌可以看这张牌的效果
- InputFrame: 弹出时允许玩家输入信息
## 一点杂谈
体量稍微有点大，时间跨度也有点大，虽然是双人项目，代码基本都是我一个人完成的，难免有各种各样的问题。命名方面也不太统一，甚至英语混杂法语。
并没有测试所有牌组合的可能性，某些特定的牌组合可能会导致问题。虽然允许创建多个真实玩家，但是只是简单地在本地新建多个MainWindows, 自己一个人扮演多个角色。
