package dataStructure.graph;

/*
*
* 通过邻接矩阵对图进行
* 深度优先遍历
* 广度优先遍历
* 运行结果：
* graph:
    A   B   C   D   E   F   G   H   I
 A  0   1   0   0   0   1   0   0   0
 B  0   0   1   0   0   0   1   0   1
 C  0   1   0   1   0   0   0   0   1
 D  0   0   1   0   1   0   1   1   1
 E  0   0   0   0   0   1   0   1   0
 F  1   0   0   0   1   0   1   0   0
 G  0   1   0   1   0   0   0   1   0
 H  0   0   0   1   1   0   1   0   0
 I  0   1   1   0   0   0   0   0   0
DFS: A B C D E F G H I
BFS: A B F C G I E D H
* */
public class TestGraphOfMatrix {
    public static void main(String[] args) {
        String[] vexs = {"A", "B", "C", "D","E","F","G","H","I"};
        String[][] edges = new String[][]{
                {"A", "B"},
                {"A", "F"},
                {"B", "G"},
                {"B", "C"},
                {"B", "I"},
                {"C", "B"},
                {"C", "I"},
                {"C", "D"},
                {"D", "C"},
                {"D", "I"},
                {"D", "G"},
                {"D", "H"},
                {"D", "E"},
                {"E", "H"},
                {"E", "F"},
                {"F", "G"},
                {"F", "A"},
                {"F", "E"},
                {"G", "H"},
                {"G", "D"},
                {"G", "B"},
                {"H", "G"},
                {"H", "D"},
                {"H", "E"},
                {"I", "B"},
                {"I", "C"}
        };
        AdjacencyMatrix graph;
        graph = new AdjacencyMatrix(vexs, edges);
        graph.print();   // 打印图
        graph.DepthFirstSearch();     // 深度优先遍历
        graph.BreadthFirstSearch();     // 广度优先遍历
    }
}

