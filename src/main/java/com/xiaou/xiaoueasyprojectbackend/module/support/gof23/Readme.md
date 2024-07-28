## 前言

这个模块我们就是把所有的设计模式给整合了起来

## 一、**设计模式（Design Patterns）简介：**

设计模式代表了最佳实践，通常被有经验的面向对象的开发人员使用。

设计模式是程序猿在软件开发过程中面临的一般问题的解决方案。

设计模式是一套反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。

## 二、那么什么是GoF呢？设计模式跟这个又有何牵扯？

GoF是Gang of Four的简称，中文翻译：四人帮，23种设计模式最先就是他们提出来的，也可以称之为设计模式的四个霸霸。

在 1994 年，由 Eric Gamma、Richard Helm、Ralph Johnson 和 John Vlissides 四位博士合著出版了一本名为 Design Patterns - Elements of Reusable Object-Oriented Software（中文译名：设计模式 - 可复用的面向对象软件元素） 的书，该书首次提到了软件开发中设计模式的概念。

四位作者合称 GoF。他们所提出的设计模式主要是基于以下的面向对象设计原则：

1）对接口编程而不是对实现编程。

2）优先使用对象组合而不是继承。

## 三、分类概括

GoF提出的设计模式有23种，且大致可以分成三类：

**创建型模式（Creational Patterns）**

**结构型模式（Structural Patterns）**

**行为型模式（Behavioral Patterns）**

*（注：下面粗体标记的是我接触过比较多的设计模式，你可以重点研究一下，也可以选择忽略… ...通透理解设计模式能帮助我们很好的阅读框架或第三方依赖源码）*

1）创建型模式

这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。

  **工厂模式（Factory Pattern）**

  **抽象工厂模式（Abstract Factory Pattern）**

  **单例模式（Singleton Pattern）**

  **建造者模式（Builder Pattern）**

  **原型模式（Prototype Pattern）**

2）结构型模式

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式。

  **适配器模式（Adapter Pattern）**

  桥接模式（Bridge Pattern）

  **过滤器模式（Filter、Criteria Pattern）**

  组合模式（Composite Pattern）

  **装饰器模式（Decorator Pattern）**

  外观模式（Facade Pattern）

  享元模式（Flyweight Pattern）

  **代理模式（Proxy Pattern）**

3）行为型模式

这些设计模式特别关注对象之间的通信。

  责任链模式（Chain of Responsibility Pattern）

  命令模式（Command Pattern）

  解释器模式（Interpreter Pattern）

  **迭代器模式（Iterator Pattern）**

  中介者模式（Mediator Pattern）

  备忘录模式（Memento Pattern）

  **观察者模式（Observer Pattern）**

  状态模式（State Pattern）

  空对象模式（Null Object Pattern）

  策略模式（Strategy Pattern）

  **模板模式（Template Pattern）**

  访问者模式（Visitor Pattern）