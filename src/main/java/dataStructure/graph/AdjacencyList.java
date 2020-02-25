package dataStructure.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一个有向图用邻接表的方式存储： 图：有向图的邻接表和逆邻接表.png中的图
 * 结果：
 * graph: 索引位置(顶点数据信息)
 * 0(V0): --> 3
 * 1(V1): --> 0  --> 2
 * 2(V2): --> 0  --> 1
 * 3(V3):
 * Created by golden on 2016/10/11 0011.
 */
public class AdjacencyList {
    private VertexNode[] vertexs;  // 顶点的数组集合

    //图中的一个顶点
    private class VertexNode {
        String data;          // 顶点信息
        IndexNode first;    // 用来连接第一个指向的顶点

        public VertexNode(String data, IndexNode first) {
            this.data = data;
            this.first = first;
        }
    }

    ;

    // 一个顶点的单链表中的一个节点，用来存储该顶点所指向顶点的索引信息；
    private class IndexNode {
        int index;         // 该边所指向的顶点的索引信息（数组中位置）
        IndexNode next; // 指向下一条弧的指针

        public IndexNode(int index) {
            this.index = index;
        }
    }

    /*
     * 用提供的信息，创建邻接表
     */
    public AdjacencyList(String[] vexs, String[][] edges) {
        // 初始化顶点数组
        vertexs = new VertexNode[vexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            vertexs[i] = new VertexNode(vexs[i], null);
        }
        // 建立点与点的关联
        for (int i = 0; i < edges.length; i++) {
            int index1 = getIndex(edges[i][0]);   // 一条边中起点的索引位置信息
            int index2 = getIndex(edges[i][1]);   // 一条边中终点的索引位置信息
            IndexNode indexNode = new IndexNode(index2);
            // 将indexNode和vertexNode建立关联；
            if (vertexs[index1].first == null) {  //将indexNode节点为第一个子节点
                vertexs[index1].first = indexNode;
            } else { //将indexNode节点挂到Linkedlist的最后
                IndexNode t = vertexs[index1].first;
                while (t.next != null) {
                    t = t.next;
                }
                t.next = indexNode;
            }
        }
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
            System.out.printf("图是个环！\n");
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


    public static void main(String[] args) {
        String[] vexs = {"V0", "V1", "V2", "V3"};   //顶点集合
        String[][] edges = new String[][]{   //边的集合
                {"V0", "V3"},
                {"V1", "V0"},
                {"V1", "V2"},
                {"V2", "V0"},
                {"V2", "V1"}
        };
        AdjacencyList al = new AdjacencyList(vexs, edges); //构造邻接表来存储图
        al.print();   // 打印图的存储结构
    }

}
