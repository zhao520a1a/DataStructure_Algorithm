package dataStructure.graph;

/**
 * Java: 无回路有向图(Directed Acyclic graph)的拓扑排序
 *       该DAG图是通过邻接表实现的。
 * 结果：
 *       TopSort: B C A D G E F
 */

public class TestGraphOfList {

    public static void main(String[] args) {
        String[] vexs = {"A", "B", "C", "D", "E", "F", "G"};//顶点数组
        String[][] edges = new String[][]{//有向边数组，例如A->G B->A等
                {"A", "G"},
                {"B", "A"},
                {"B", "D"},
                {"C", "F"},
                {"C", "G"},
                {"D", "E"},
                {"D", "F"}};
        AdjacencyList graph = new AdjacencyList(vexs, edges);
        graph.print();               // 打印图
        graph.toplogicalSort();     // 拓扑排序
    }
}

