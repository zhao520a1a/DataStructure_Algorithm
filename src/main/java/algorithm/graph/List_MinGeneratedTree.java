package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 *（邻接表存储）无向带权图的最小生成树问题：
 * Created by golden on 2016/10/16 0016.
 * * 将一个有向图用邻接表的方式存储： 图： 最小生成树.png
 * 结果：
 * graph: 索引位置(顶点数据信息)
 * 0(A): --> 1  --> 5  --> 6
 * 1(B): --> 0  --> 2  --> 5
 * 2(C): --> 1  --> 3  --> 4  --> 5
 * 3(D): --> 2  --> 4
 * 4(E): --> 2  --> 3  --> 5  --> 6
 * 5(F): --> 0  --> 1  --> 2  --> 4  --> 6
 * 6(G): --> 0  --> 4  --> 5
 * Created by golden on 2016/10/11 0011.
 */

public class List_MinGeneratedTree {
    private VertexNode[] vertexs;  // 顶点的数组集合
    private int eCount;   //有效边的个数  -- kruskal算法使用
    int INF = Integer.MAX_VALUE;  //表示不可达  ---prim算法使用


    // 一个顶点的单链表中的一个节点，用来存储该顶点所指向顶点的索引信息；
    private class IndexNode {
        int index;         // 该边所指向的顶点的索引信息（数组中位置）
        int weight;       //权值
        IndexNode next; // 指向下一条弧的指针

        public IndexNode(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    //表示图中的一个顶点
    private class VertexNode {
        String data;          // 顶点信息
        IndexNode first;    // 用来连接第一个指向的顶点

        public VertexNode(String data, IndexNode first) {
            this.data = data;
            this.first = first;
        }
    }

    //表示图中的一条边
    private static class Edge {
        /*注：因为是无向图，所以这里的起点和终点并不带有方向性质，仅仅是按点存储的顺序而相对出来的概念*/
        private String start; // 边的起点
        private String end;   // 边的终点
        private int weight;   // 边的权重

        public Edge(String start, String end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


    /*
     * 用提供的信息，创建邻接表
     */
    public List_MinGeneratedTree(String[] vexs, Edge[] edges) {
        eCount = edges.length;
        // 初始化顶点数组
        vertexs = new VertexNode[vexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            vertexs[i] = new VertexNode(vexs[i], null);
        }
        // 建立点与点的关联
        for (int i = 0; i < edges.length; i++) {
            String start = edges[i].start;   // 一条边中起点
            String end = edges[i].end;   // 一条边中终点
            int weight = edges[i].weight;   // 一条边的权值
            // 读取边的起始顶点和结束顶点
            int index1 = this.getIndex(start);
            int index2 = this.getIndex(end);
            // 将indexNode和vertexNode建立关联； 注：因为这里是无向图，所以要两者相互关联；
            IndexNode node1 = new IndexNode(index2, weight);
            if (vertexs[index1].first == null) {  //将node2节点设为第一个子节点
                vertexs[index1].first = node1;
            } else {    //将node2节点挂到Linkedlist的最后
                this.linkLast(vertexs[index1].first, node1);
            }
            IndexNode node2 = new IndexNode(index1, weight);
            if (vertexs[index2].first == null) {
                vertexs[index2].first = node2;
            } else {
                this.linkLast(vertexs[index2].first, node2);
            }

        }
    }

    /*
     * 将node节点链接到list的最后
	 */
    private void linkLast(IndexNode first, IndexNode node) {
        IndexNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
    }


    /*
     * 根据顶点信息来返回其索引位置
     */
    private int getIndex(String data) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].data.equals(data)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * 打印图
     */
    public void print() {
        System.out.printf("graph: 索引位置(顶点数据信息)\n");
        for (int index = 0; index < vertexs.length; index++) {
            System.out.printf(" %d(%s): ", index, vertexs[index].data);
            IndexNode indexNode = vertexs[index].first;
            while (indexNode != null) {
                int linkedIndex = indexNode.index;
                System.out.printf("--> %d  ", linkedIndex, vertexs[linkedIndex].data);
                indexNode = indexNode.next;
            }
            System.out.printf("\n");
        }
    }

    /*
     * 拓扑排序
     *
     * 返回值：
     *     -1 -- 失败(由于内存不足等原因导致)
     *      0 -- 成功排序，并输入结果
     *      1 -- 失败(该有向图是有环的)
     */
    public int toplogicalSort() {
        int index = 0;
        int vlenght = vertexs.length;   //顶点的个数
        int[] in = new int[vlenght];     // 记录每个点时时入度个数的数组     ---   核心点
        String[] result = new String[vlenght];  // 拓扑排序的结果数组
        Queue<Integer> queue = new LinkedList<Integer>();// 辅助队列：存储入度为0的节点索引；
        // 初始化每个顶点的入度数
        for (int i = 0; i < vlenght; i++) {
            IndexNode node = vertexs[i].first;
            while (node != null) {
                in[node.index]++;
                node = node.next;
            }
        }
        // 先将图中入度为0的顶点加入队列,
        for (int i = 0; i < vlenght; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int j = queue.poll().intValue();     // 出队列。取得队头-顶点的索引
            result[index++] = vertexs[j].data;  // 将顶点信息加入到结果中
            IndexNode node = vertexs[j].first;  // 获取该顶点的第一个邻接点
            //遍历其下的邻接点
            while (node != null) {
                // 先将邻接点的入度减1，去掉来自原节点的入度；
                in[node.index]--;
                if (in[node.index] == 0) {   //之后入度为0，就加入队列中；
                    queue.add(node.index);
                }
                node = node.next;
            }
        }
        if (index != vlenght) {  //如果加入的次数不等于顶点个数，说明没有将图中的所有点加入进去，有环路存在；
            System.out.printf("图中有个环！\n");
            return 1;
        }
        // 打印拓扑排序结果
        System.out.printf("TopSort: ");
        for (int i = 0; i < vlenght; i++) {
            System.out.printf("%s ", result[i]);
        }
        System.out.printf("\n");
        return 0;
    }

    /**
     * Prim算法
     *
     * @param startIndex 起点的索引值
     */
    public void prim(int startIndex) {

        int vCount = this.vertexs.length;
        String[] result = new String[vCount]; //保存最小生成树的顶点集合
        int[] weight = new int[vCount];   //记录点所连接的边的权值
        int index = 0;  //结果数组的索引号

        //将起点记录到结果中
        result[index++] = this.vertexs[startIndex].data;
        //初始化权值数组
        for (int i = 0; i < vCount; i++) {
            weight[i] = this.getWeight(startIndex, i);
        }
        //通过找到最小的权值，进而选择出下一个顶点；  ----- 核心点
        int sum = 0;
        for (int i = 0; i < vCount; i++) {
            if (startIndex == i) {  //跳过访问自己
                continue;
            }
            int min = INF;   //最小权值
            int nextIndex = 0;  //满足最小权值下一顶点的索引
            for (int j = 0; j < vCount; j++) {
                if (weight[j] != 0 && weight[j] < min) {  //找出最小权值
                    min = weight[j];
                    nextIndex = j;
                }
            }
            sum += min;
            // 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
            // 将第k个顶点加入到最小生成树的结果数组中
            result[index++] = this.vertexs[nextIndex].data;
            // 将"第k个顶点的权值"权值置零，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)，防止再次访问该索引所指的权值。
            weight[nextIndex] = 0;
            // 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
            for (int j = 0; j < vCount; j++) {
                int temp = this.getWeight(nextIndex, j);
                if (weight[j] != 0 && temp < weight[j]) {  //如果下一点比原有点所属的权值还有大，就没有记录的必要，因为我们有取最小的权值；
                    weight[j] = temp;
                }
            }
        }
        // 统计并打印"prim最小生成树"的信息
        System.out.printf("Prim(%s)：权值和=%d,经过顶点的顺序：", vertexs[startIndex].data, sum);
        for (int i = 0; i < index; i++) {
            System.out.printf("%s ", result[i]);
        }
        System.out.printf("\n");
    }

    /*
	 * 获取边<start, end>的权值；若start和end不是连通的，则返回无穷大。
	 */
    private int getWeight(int start, int end) {
        if (start == end) {
            return 0;
        }
        IndexNode indexNode = vertexs[start].first;
        while (indexNode != null) {
            if (end == indexNode.index) {
                return indexNode.weight;
            }
            indexNode = indexNode.next;
        }
        return INF;
    }

    public void kruskal() {
        int index = 0;  //
        Edge[] edges = new Edge[eCount];     //有效边集合
        int[] endVexsIndex = new int[this.vertexs.length];  //记录图中各点经过（即会组成最小生成树的边）实效边连接后所到达终点索引
        Edge[] eResult = new Edge[eCount];   //保存最小生成树的边集合--实效边集合
        //初始化有效边的集合
        for (int i = 0; i < this.vertexs.length; i++) {
            IndexNode indexNode = vertexs[i].first;
            while (indexNode != null) {
                if (indexNode.index > i) {  //防止添加重复的边
                    edges[index++] = new Edge(vertexs[i].data, vertexs[indexNode.index].data, indexNode.weight);
                }
                indexNode = indexNode.next;
            }
        }
        index = 0;
        //   核心1 -- 对边按权值从小到大进行排序；这里采用冒泡排序
        for (int i = 0; i < eCount; i++) {
            for (int j = i + 1; j < eCount; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
        //   核心2 -- 避免出现回路的情况：时时检测两点所对应的终点是否一致
        for (int i = 0; i < eCount; i++) {
            int oneIndex = this.getPosition(edges[i].start);//一条边起点的位置
            int anotherIndex = this.getPosition(edges[i].end); //一条边终点的位置
            /*找到两点所指终点索引*/
            int eIndex1 = this.getEndVexsIndex(endVexsIndex, oneIndex);
            int eIndex2 = this.getEndVexsIndex(endVexsIndex, anotherIndex);
            if (eIndex1 != eIndex2) {  //若是相同，说明会出现环路；否则，就不是环路；
                endVexsIndex[eIndex1] = eIndex2;  //记录一个点所对应的终点索引
                eResult[index++] = edges[i];  //将该边加入实效边的集合中
            }
        }
        // 统计并打印"kruskal最小生成树"的信息
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += eResult[i].weight;
        }
        System.out.printf("Kruskal：权值和=%d,所包含的边：", sum);
        for (int i = 0; i < index; i++) {
            System.out.printf("(%s,%s) ", eResult[i].start, eResult[i].end);
        }
        System.out.printf("\n");
    }

    /*
   * 根据顶点信息来返回其索引位置
   */
    private int getPosition(String data) {
        for (int i = 0; i < this.vertexs.length; i++) {
            if (data.equals(this.vertexs[i].data)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获得指定顶点经过（会组成最小生成树的部分）实效边连接后所到达终点索引
     *
     * @param nextVexsIndex 保存了各点对应终点索引
     * @param i             顶点索引
     * @return 终点索引
     */
    public int getEndVexsIndex(int[] nextVexsIndex, int i) {
        while (nextVexsIndex[i] != 0) {
            i = nextVexsIndex[i];
        }
        return i; //两种情况：1.一个点对应的终点索引为i 2.集合中还没有记录一个点对应的终点，则返回它本身的索引
    }


    public static void main(String[] args) {

        String[] vexs = {"A", "B", "C", "D", "E", "F", "G"};   //顶点集合
        Edge[] edges = {
                // 起点 终点 权
                new Edge("A", "B", 12),
                new Edge("A", "F", 16),
                new Edge("A", "G", 14),
                new Edge("B", "C", 10),
                new Edge("B", "F", 7),
                new Edge("C", "D", 3),
                new Edge("C", "E", 5),
                new Edge("C", "F", 6),
                new Edge("D", "E", 4),
                new Edge("E", "F", 2),
                new Edge("E", "G", 8),
                new Edge("F", "G", 9),
        };
        List_MinGeneratedTree graph = new List_MinGeneratedTree(vexs, edges); //构造邻接表来存储图
        graph.print();   // 打印图的存储结构
        graph.kruskal();
        graph.prim(0);
    }

}



