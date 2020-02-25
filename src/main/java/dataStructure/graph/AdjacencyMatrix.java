package dataStructure.graph;

/**
 * 将一个有向图用邻接矩阵的方式存储：  图：有向图的邻接表和逆邻接表.png中的图
 * 结果：
 * graph:
 * V0  V1  V2  V3
 * V0  0   0   0   1
 * V1  1   0   1   0
 * V2  1   1   0   0
 * V3  0   0   0   0
 * Created by golden on 2016/10/11 0011.
 */
public class AdjacencyMatrix {
    private String[] vertexs;       // 顶点集合
    private int[][] matrix;       // 邻接矩阵

    /*
     *  用提供的信息，创建邻接矩阵
     */
    public AdjacencyMatrix(String[] vertexs, String[][] edges) {
        this.vertexs = vertexs;
        int vlen = vertexs.length;
        int elen = edges.length;
        matrix = new int[vlen][vlen];
        for (int i = 0; i < elen; i++) {
            int p1 = getIndex(edges[i][0]); // 一条边中起点的索引位置信息
            int p2 = getIndex(edges[i][1]); // 一条边中终点的索引位置信息
            matrix[p1][p2] = 1;//A到B的边即存在
        }
    }

    /*
     * 根据顶点信息来返回其索引位置
     */
    private int getIndex(String data) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * 打印图
     */
    public void print() {
        System.out.println("graph:");
        System.out.print("    ");
        for (int i = 0; i < vertexs.length; i++) {
            System.out.printf("%s   ", vertexs[i]);
        }
        System.out.println();

        for (int i = 0; i < vertexs.length; i++) {
            System.out.printf(" %s ", vertexs[i]);
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf(" %d  ", matrix[i][j]);
            }
            System.out.printf("\n");
        }
    }

    /*
     * 返回指定顶点相连的第一个顶点的索引，失败则返回-1  ==== 站在当前点，始终向它右边的第一个未被访问的邻接点走；
     */
    private int firstVertex(int v) {
        if (v < 0 || v > (vertexs.length - 1)) {
            return -1;
        }
        for (int i = 0; i < vertexs.length; i++) {
            if (matrix[v][i] == 1) {
                return i;   //返回第一个邻接顶点的索引
            }
        }
        return -1;
    }

    /*
     * 若该邻接顶点已被访问，查找当前顶点继该邻接顶点后的下一个邻接顶点，返回其索引
     * cIndex:当前顶点的索引
     * vIndex:一个已被访问过当前顶点的邻接顶点的索引
     */
    private int nextVertex(int cIndex, int vIndex) {
        if (cIndex < 0 || cIndex > (vertexs.length - 1) || vIndex < 0 || vIndex > (vertexs.length - 1)) {
            return -1;
        }
        for (int i = vIndex + 1; i < vertexs.length; i++) {    //从vIndex向后去寻找当前顶点的下一个的邻接点
            if (matrix[cIndex][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /*
    * 深度优先搜索的核心程序
    */
    private void DepthFirstSearch(int cIndex, boolean[] flag) {
        flag[cIndex] = true;                      //标记当前顶点已被访问
        System.out.printf("%s ", vertexs[cIndex]);

        int nIndex = firstVertex(cIndex);   //查找当前顶点的第一个邻接顶点，返回其索引
        while (nIndex != -1) {   //若存在邻接顶点
            if (flag[nIndex] == false) {   //如果该邻接顶点没有被访问过,则对该邻接顶点进行深度优先遍历（递归）
                DepthFirstSearch(nIndex, flag);
            }
            //若该邻接顶点已被访问，查找当前顶点继该邻接顶点后的下一个邻接顶点，返回其索引
            nIndex = nextVertex(cIndex, nIndex);
        }
    }

    /*
     * 深度优先搜索遍历（邻接矩阵）图（类似树的前序遍历）
     * 运用递归
     */
    public void DepthFirstSearch() {
        boolean[] flag = new boolean[vertexs.length];       //  定义访问顶点的标记数组
        for (int i = 0; i < vertexs.length; i++) {   //初始化访问标记
            flag[i] = false;
        }

        System.out.printf("DFS: ");
        for (int i = 0; i < vertexs.length; i++) {
            if (flag[i] == false) {
                DepthFirstSearch(i, flag);   //从第一个顶点开始遍历
            }
        }
        System.out.printf("\n");
    }

    /*
     * 广度优先遍历（邻接矩阵）图（类似于树的层次遍历）
     * 使用一个“辅助队列”来保存每一层已访问的顶点信息，以便通过这层顶点找到下一层顶点
     */
    public void BreadthFirstSearch() {
        int head = 0;//队列的头
        int rear = 0;//队列的尾
        int[] queue = new int[vertexs.length];     // 辅助队列
        boolean[] flag = new boolean[vertexs.length]; //  定义访问顶点的标记数组
        for (int i = 0; i < vertexs.length; i++) {  //初始化访问标记
            flag[i] = false;
        }

        System.out.printf("BFS: ");
        for (int i = 0; i < vertexs.length; i++) {
            if (flag[i] == false) {
                flag[i] = true;   //标记当前顶点已被访问
                System.out.printf("%s ", vertexs[i]);
                queue[rear++] = i;  // 入队列
            }
            while (head != rear) {
                int j = queue[head++];  // 出队列，获得队头的顶点索引
                int k = firstVertex(j);// 查找队头顶点的第一个邻接顶点，返回其索引
                while (k >= 0) { //k是为访问的邻接顶点,找到和定点所有关联的邻接点，并且设标记
                    if (flag[k] == false) { //若队头顶点的邻接顶点尚未被访问
                        flag[k] = true;  //标记已被访问
                        System.out.printf("%s ", vertexs[k]);
                        queue[rear++] = k;// 入队列
                    }
                    k = nextVertex(j, k);//若队头顶点的邻接顶点已被访问，查找当前顶点继该邻接顶点后的下一个邻接顶点
                }
            }
        }
        System.out.printf("\n");
    }


    public static void main(String[] args) {
        String[] vexs = {"V0", "V1", "V2", "V3"};
        String[][] edges = new String[][]{
                {"V0", "V3"},
                {"V1", "V0"}, {"V1", "V2"},
                {"V2", "V0"}, {"V2", "V1"},
        };
        //若是无向图，边只不过要表示为A->B B->A
//       string[][] edges = new string[][]{//边是个二维数组
//        		{'A', 'B'},{'B','A'},
//        		{'B', 'C'}, {'C', 'B'},
//        		{'C', 'D'},{'D', 'C'},
//       };
        AdjacencyMatrix am = new AdjacencyMatrix(vexs, edges); //构造图的邻接矩阵
        am.print();   // 打印图


    }


}
