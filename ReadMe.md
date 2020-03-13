#### 数据结构与算法之美

![Image](https://img.shields.io/badge/language-muti-brightgreen.svg)
![Image](https://img.shields.io/badge/leetcode-50%2B-orange.svg)

> 引言 ： 数据结构是为算法服务的，算法要作用在特定的数据结构之上。


 
![Image](courseware/banner.png)
   
整体结构：   
 ![Image](courseware/数据结构与算法.jpg)   
最常用的核心：
10数据结构：    
数组、链表、栈、队列、散列表、二叉树、堆、跳表、图、Trie树
10个算法：
递归、排序、二分查找、搜索、哈希算法、贪心算法、分治算法、回溯算法、动态规划、字符串匹配算法

> 观点：学习的目的还是掌握，然后应用
> - 边学边练，“适度”刷题
> - 多问、多思考、多互动
> - 打怪升级学习法
> - 知识需要沉淀，不要想试图一下子掌握所有
  
[怎么才算真正掌握了一个数据结构或算法？](https://mp.weixin.qq.com/s/t8z4KQMrTrR3NljtWJm2zg)
特点、复杂度、能解决的问题、应用场景;  牢固掌握原理，并能轻松代码实现。
 

### 数据结构
- 线性表
	- [数组](src/main/java/dataStructure/array)
    - [链表](src/main/java/dataStructure/List)
    - [栈](src/main/java/dataStructure/stack)
    - [队列](src/main/java/dataStructure/queue)
    - 跳表
    - 散列表
- 树
	- [二叉树](./src/main/java/dataStructure/BinaryTree)
			- [二叉树基础（上）：什么样的二叉树适合用数组来存储？](https://time.geekbang.org/column/article/67856)
    		- [二叉树基础（下）：有了如此高效的散列表，为什么还需要二叉树？](https://time.geekbang.org/column/article/68334)
	- [二叉搜索树](src/main/java/dataStructure/binarySearchTree)
	- [平衡二叉树](src/main/java/dataStructure/avlTree)
	- [红黑树](src/main/java/dataStructure/redBlackTree)
			- [红黑树（上）：为什么工程中都用红黑树这种二叉树？](https://time.geekbang.org/column/article/68638)
			- [红黑树（下）：掌握这些技巧，你也可以实现一个红黑树](https://time.geekbang.org/column/article/68976)
	- [哈夫曼树](src/main/java/dataStructure/huffmanTree)
	- 堆
- [图](./src/main/java/dataStructure/Graph)
    - 无向图、有向图、带权图
        - [图的表示：如何存储微博、微信等社交网络中的好友关系？](https://time.geekbang.org/column/article/70537)



> 帮助文档：
- [十大经典排序算法](https://github.com/hustcc/JS-Sorting-Algorithm)

### 算法

- [复杂度分析](Algorithm/complexityAnalysis)



- [排序](./Sort)
	- 冒泡排序、插入排序、选择排序
	- 归并排序、快速排序
	- 桶排序、计数排序、基数排序
	- [排序优化：如何实现一个通用的、高性能的排序函数？](https://time.geekbang.org/column/article/42359)
- 递归
	- [递归：如何用三行代码找到“最终推荐人”？](https://time.geekbang.org/column/article/41440)
	- [递归树：如何借助树来求解递归算法的时间复杂度？](https://time.geekbang.org/column/article/69388)
- [查找](./Search)
	- 二分查找
		- [二分查找（上）：如何用最省内存的方式实现快速查找功能？](https://time.geekbang.org/column/article/42520)
		- [二分查找（下）：如何快速定位IP对应的省份地址？](https://time.geekbang.org/column/article/42733)
    - 广度、深度优先搜索
    	- [深度和广度优先搜索：如何找出社交网络中的三度好友关系？](https://time.geekbang.org/column/article/70891)
- 哈希算法
	- [哈希算法（上）：如何防止数据库中的用户信息被脱库？](https://time.geekbang.org/column/article/65312)
	- [哈希算法（下）：哈希算法在分布式系统中有哪些应用？](https://time.geekbang.org/column/article/67388)
- [字符串](./String)
	- BF、RK 算法
		- [字符串匹配基础（上）：如何借助哈希算法实现高效字符串匹配？](https://time.geekbang.org/column/article/71187)
	- BM 算法
		- [字符串匹配基础（中）：如何实现文本编辑器中的查找功能？](https://time.geekbang.org/column/article/71525)
	- KMP 算法
		- [字符串匹配基础（下）：如何借助BM算法轻松理解KMP算法？](https://time.geekbang.org/column/article/71845)
    - Trie 树
        - [Trie树：如何实现搜索引擎的搜索关键词提示功能？](https://time.geekbang.org/column/article/72414)
    - AC 自动机
        - [AC自动机：如何用多模式串匹配实现敏感词过滤功能？](https://time.geekbang.org/column/article/72810)
- 算法思想
	- 贪心算法
		- [贪心算法：如何用贪心算法实现Huffman压缩编码？](https://time.geekbang.org/column/article/73188)
    - [分治算法](./DivideAndConquer)
        - [分治算法：谈一谈大规模计算框架MapReduce中的分治思想](https://time.geekbang.org/column/article/73503)
	- [回溯算法](./BackTracking)
        - [回溯算法：从电影《蝴蝶效应》中学习回溯算法的核心思想](https://time.geekbang.org/column/article/74287)
	- [动态规划](./DynamicProgramming)
        - [初识动态规划：如何巧妙解决“双十一”购物时的凑单问题？](https://time.geekbang.org/column/article/74788)
        - [动态规划理论：一篇文章带你彻底搞懂最优子结构、无后效性和重复子问题](https://time.geekbang.org/column/article/75702)

### 高级篇
- [拓扑排序：如何确定代码源文件的编译依赖关系？](https://time.geekbang.org/column/article/76207)

- [最短路径：地图软件是如何计算出最优出行路径的？](https://time.geekbang.org/column/article/76468)

- [位图：如何实现网页爬虫中的URL去重功能？](https://time.geekbang.org/column/article/76827)

- [概率统计：如何利用朴素贝叶斯算法过滤垃圾短信？](https://time.geekbang.org/column/article/77142)

- [向量空间：如何实现一个简单的音乐推荐系统？](https://time.geekbang.org/column/article/77457)

- [B+树：MySQL数据库索引是如何实现的？](https://time.geekbang.org/column/article/77830)

- [搜索：如何用A`*`搜索算法实现游戏中的寻路功能？](https://time.geekbang.org/column/article/78175)

- [索引：如何在海量数据中快速查找某个数据？](https://time.geekbang.org/column/article/78449)

- [并行算法：如何利用并行处理提高算法的执行效率？](https://time.geekbang.org/column/article/78795)

### 实战篇
- [算法实战（一）：剖析Redis常用数据类型对应的数据结构](https://time.geekbang.org/column/article/79159)

- [算法实战（二）：剖析搜索引擎背后的经典数据结构和算法](https://time.geekbang.org/column/article/79433)

- [算法实战（三）：剖析高性能队列Disruptor背后的数据结构和算法](https://time.geekbang.org/column/article/79871)

- [算法实战（四）：剖析微服务接口鉴权限流背后的数据结构和算法](https://time.geekbang.org/column/article/80388)

- [算法实战（五）：如何用学过的数据结构和算法实现一个短网址系统？](https://time.geekbang.org/column/article/80850)


## 推荐专栏
### [《数据结构与算法之美》](http://t.cn/EvWJWn4)专栏导航
 



###### 问题清单

## array（数组）

[01. Two Sum（两数之和）](./01.TwoSum)

[02. Three Sum（三数之和）](./02.ThreeSum)

[03. Majority Element（在数组中出现次数超过一半的数）](./03.MajorityElement)



###### 算法题目

# Two Sum（两数之和）
**LeetCode 1**

- [英文版](https://leetcode.com/problems/two-sum/)

- [中文版](https://leetcode-cn.com/problems/two-sum/)

## 题目
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那*两个*整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:
```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

## 思路
<details>
<summary>点击展开</summary>
借助散列表存储访问过元素的值和下标，时间复杂度 O(n)。
</details>

## 代码实现
| C | C++ | Java | Python | JavaScript | PHP |
| :--: | :--: | :--: | :--: | :---: | :---: |
| 🤔 | 🤔 |  [😀](IsPalindrome.java)  |  🤔 |  🤔  | 🤔 |





    