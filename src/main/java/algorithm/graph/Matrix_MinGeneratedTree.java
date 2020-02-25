package algorithm.graph;

/**
 * Created by golden on 2016/10/15 0015.
 * （邻接矩阵存储）无向带权图的最小生成树问题：
 * Prim算法：找点
 * Kruskal算法：找边
 * 结果：
 * Prim(A)：权值和=36,经过顶点的顺序：A B F E D C G
 * Kruskal：权值和=36,所包含的边：(E,F) (C,D) (D,E) (B,F) (E,G) (A,B)
 */
public class Matrix_MinGeneratedTree {
    private static final int INF = Integer.MAX_VALUE;  //两点不可达
    private String[] vertexs;  //顶点集合
    private int[][] matrix;    //邻接矩阵

    public Matrix_MinGeneratedTree(String[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        //  this.matrix = matrix;
        this.matrix = new int[this.vertexs.length][this.vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Prim算法
     * @param startIndex 起点的索引值
     */
    public void prim(int startIndex) {
        int vCount = this.vertexs.length;
        String[] result = new String[vCount]; //保存最小生成树的顶点集合
        int[] weight = new int[vCount];   //记录点所连接的边的权值
        int index = 0;  //结果数组的索引号

        //将起点记录到结果中
        result[index++] = this.vertexs[startIndex];
        //初始化权值数组
        for (int i = 0; i < vCount; i++) {
            weight[i] = matrix[startIndex][i];
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
            result[index++] = this.vertexs[nextIndex];
            // 将"第k个顶点的权值"权值置零，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)，防止再次访问该索引所指的权值。
            weight[nextIndex] = 0;
            // 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
            for (int j = 0; j < vCount; j++) {
                if (weight[j] != 0 && matrix[nextIndex][j] < weight[j]) {  //如果下一点比原有点所属的权值还有大，就没有记录的必要，因为我们有取最小的权值；
                    weight[j] = this.matrix[nextIndex][j];
                }
            }
        }
/*
        //单独计算最小生成树的权值之和
        number sum = 0;
        for(number i=1; i<index; i++) {  //下一个点
            number n = this.getPosition(result[i]);  //结果顶点索引
            number min=INF;
            for(number j=0; j<i; j++) {  //原点
                number m = this.getPosition(result[j]);
                if(this.matrix[m][n] < min) {
                    min = this.matrix[m][n];
                }
            }
            sum += min;
        }*/
        // 统计并打印"prim最小生成树"的信息
        System.out.printf("Prim(%s)：权值和=%d,经过顶点的顺序：", vertexs[startIndex], sum);
        for (int i = 0; i < index; i++) {
            System.out.printf("%s ", result[i]);
        }
        System.out.printf("\n");
    }

    /*Kruskal算法：*/
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

    public void kruskal() {
        int eCount = 0;  //有效边的个数
        //计算有效边的个数
        for (int i = 0; i < this.vertexs.length; i++) {
            for (int j = i + 1; j < this.vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    eCount++;
                }
            }
        }
        int index = 0;
        Edge[] edges = new Edge[eCount];     //有效边集合
        int[] endVexsIndex = new int[this.vertexs.length];  //记录图中各点经过（即会组成最小生成树的边）实效边连接后所到达终点索引
        Edge[] eResult = new Edge[eCount];   //保存最小生成树的边集合--实效边集合
        //初始化有效边的集合
        for (int i = 0; i < this.vertexs.length; i++) {
            for (int j = i + 1; j < this.vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edges[index++] = new Edge(this.vertexs[i], this.vertexs[j], this.matrix[i][j]);  //初始化边对象
                }
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
            if (eIndex1 != eIndex2) {  //若是相同，就会出现环路；否则，就不是环路；
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
            if (data.equals(this.vertexs[i])) {
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
        String[] vexs = {"A", "B", "C", "D", "E", "F", "G"};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
         /*A*/ {0, 12, INF, INF, INF, 16, 14},
         /*B*/ {12, 0, 10, INF, INF, 7, INF},
         /*C*/ {INF, 10, 0, 3, 5, 6, INF},
         /*D*/ {INF, INF, 3, 0, 4, INF, INF},
         /*E*/ {INF, INF, 5, 4, 0, 2, 8},
         /*F*/ {16, 7, 6, INF, 2, 0, 9},
         /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };
        Matrix_MinGeneratedTree e = new Matrix_MinGeneratedTree(vexs, matrix);
        e.prim(0);
        e.kruskal();
    }
}
