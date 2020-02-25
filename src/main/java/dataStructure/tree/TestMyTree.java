package dataStructure.tree;

import java.util.List;

/**
 * Created by golden on 2016/9/24 0024.
 */
public class TestMyTree {

    public static void main(String[] args) {
        //MyTreeParent tree = new MyTreeParent("root");
        MyTreeChild tree = new MyTreeChild("root");
        MyTreeChild.Node root = tree.getRoot();
        tree.add("节点1", root);
        tree.add("节点2", root);
        System.out.println("树的根结点：" +  root);
        List<MyTreeChild.Node> nodes = tree.getChildren(root);
        System.out.println("根节点的第一个子节点：" + nodes.get(0));
        System.out.println("根节点的第二个子节点：" + nodes.get(1));
        System.out.println("此树的深度:" + tree.getDeep());
        System.out.println("此树的深度:" + tree.getDeep(nodes.get(0)));

        //为根节点的第一个子节点新增一个子节点
        tree.add("节点3", nodes.get(0));
        List<MyTreeParent.Node> nodes1 = tree.getChildren(nodes.get(0));
        System.out.println("根节点的第三个子节点：" + nodes1.get(0));
        System.out.println("此树的深度:" + tree.getDeep());


    }
}
