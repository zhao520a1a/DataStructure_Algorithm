package algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by golden on 2016/10/16 0016.
 * 迪杰斯特拉算法求最短路径
 * 注意：
 * 距离：可以是多条边的值
 * 权值：一条边的值
 */

public class ShortestPath_Dijkstra {
    private Map<String, Integer> path = new HashMap<String, Integer>();//封装了指定点到各个点的距离（顶点 +　距离）
    private Set<Node> open = new HashSet<Node>();//open用于存储未遍历的点
    private Set<Node> close = new HashSet<Node>();//close用来存储已遍历的节点

    /*
     * Node对象用于封装节点信息，包括顶点名字和它的邻接点信息和连接边的权值
	 */
    public class Node {
        private String name; //顶点名
        private Map<Node, Integer> childs = new HashMap<Node, Integer>();   //key：它的邻接点 +  value:连接边的权值

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Map<Node, Integer> getChilds() {
            return childs;
        }
    }

    /*构建图并初始化开始顶点与其他顶点的关系*/
    public Node init() {
        Node start = new MapBuilder().build(open, close);
        //要计算出V0-V8的最短距离，需要先初始化设置V0节点和其他所有节点的关系
        path.put("V1", 1);
        path.put("V2", 5);
        path.put("V3", Integer.MAX_VALUE);
        path.put("V4", Integer.MAX_VALUE);
        path.put("V5", Integer.MAX_VALUE);
        path.put("V6", Integer.MAX_VALUE);
        path.put("V7", Integer.MAX_VALUE);
        path.put("V8", Integer.MAX_VALUE);
        return start;
    }

    /*
     * MapBuilder用于初始化数据源，返回图的起始节点
     */
    public class MapBuilder {
        public Node build(Set<Node> open, Set<Node> close) {
            Node node0 = new Node("V0");
            Node node1 = new Node("V1");
            Node node2 = new Node("V2");
            Node node3 = new Node("V3");
            Node node4 = new Node("V4");
            Node node5 = new Node("V5");
            Node node6 = new Node("V6");
            Node node7 = new Node("V7");
            Node node8 = new Node("V8");
            node0.getChilds().put(node1, 1);
            node0.getChilds().put(node2, 5);
            node1.getChilds().put(node0, 1);
            node1.getChilds().put(node2, 3);
            node1.getChilds().put(node3, 7);
            node1.getChilds().put(node4, 5);
            node2.getChilds().put(node0, 5);
            node2.getChilds().put(node1, 3);
            node2.getChilds().put(node4, 1);
            node2.getChilds().put(node5, 7);
            node3.getChilds().put(node1, 7);
            node3.getChilds().put(node4, 2);
            node3.getChilds().put(node6, 3);
            node4.getChilds().put(node1, 5);
            node4.getChilds().put(node2, 1);
            node4.getChilds().put(node3, 2);
            node4.getChilds().put(node5, 3);
            node4.getChilds().put(node6, 6);
            node4.getChilds().put(node7, 9);
            node5.getChilds().put(node2, 7);
            node5.getChilds().put(node4, 3);
            node5.getChilds().put(node7, 5);
            node6.getChilds().put(node3, 3);
            node6.getChilds().put(node4, 6);
            node6.getChilds().put(node7, 2);
            node6.getChilds().put(node8, 7);
            node7.getChilds().put(node4, 9);
            node7.getChilds().put(node6, 2);
            node7.getChilds().put(node8, 4);
            node8.getChilds().put(node6, 7);
            node8.getChilds().put(node7, 4);
            //初始阶段：将初始节点放入close中，其他节点放入open中
            close.add(node0);
            open.add(node1);
            open.add(node2);
            open.add(node3);
            open.add(node4);
            open.add(node5);
            open.add(node6);
            open.add(node7);
            open.add(node8);
            return node0;
        }
    }

    /**
     * @param current: 指定的节点
     */
    public void dijkstra(Node current) {     //  --- 核心程序
        //获取离指定节点最近的邻接点（最近点），放入close
        Node nearest = this.getNearestNode(current);
        if (nearest == null) {
            return;
        }
        close.add(nearest);
        open.remove(nearest);
        System.out.print(nearest.getName() + ":" + path.get(nearest.getName()) + "\n");
        //重新计算并更新距离值直至close包含所有节点
        Map<Node, Integer> childs = nearest.getChilds();
        for (Node child : childs.keySet()) {
            if (open.contains(child)) {
                int preDis = path.get(nearest.getName()); //之前的距离
                int weight = childs.get(child);  // 加上到下一点的权值
                int newDistance = preDis + weight;
                if (newDistance < path.get(child.getName())) {  //若计算出距离小于原来到达所需的距离，则说明有必要更新“最近点的邻接点到指定点”的距离距离
                    path.put(child.getName(), newDistance);
                }
            }
        }
        this.dijkstra(nearest);  //递归，层层遍历
    }

    /**
     * 获得离当前节点最近的邻接点
     * @param node:当前节点
     * @return :返回最近的节点
     */
    private Node getNearestNode(Node node) {
        Node nearest = null;
        int minWeight = Integer.MAX_VALUE;
        Map<Node, Integer> childs = node.getChilds();
        for (Node child : childs.keySet()) {
            if (open.contains(child)) {//open用于存储未遍历的点
                int weight = childs.get(child);
                if (weight < minWeight) {
                    minWeight = weight;
                    nearest = child;
                }
            }
        }
        return nearest;
    }


    public static void main(String[] args) {
        ShortestPath_Dijkstra test = new ShortestPath_Dijkstra();
        Node start = test.init();
        test.dijkstra(start);
    }


}

