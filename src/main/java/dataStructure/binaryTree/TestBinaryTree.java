package dataStructure.binaryTree;


/**
 * Created by golden on 2016/9/27 0027.
 */
public class TestBinaryTree {
    public static void main(String[] args) {
       ArrayBinTree<String> binTree1 = new ArrayBinTree<String>(4, "root");
        binTree1.add(0 , "第二层右子节点" , false);
        binTree1.add(2 , "第三层右子节点" , false);
        binTree1.add(6 , "第四层右子节点" , false);
        System.out.println(binTree1);
        System.out.println(binTree1.leftChild(6));//因为它的左面没有返回null
        System.out.println(binTree1.rightChild(6));//第三层的右面就是第四层右节点
        System.out.println( binTree1.getDeep());
        System.out.println("根节点：" + binTree1.getRoot());
        LinkedBinaryTree<String> binTree2 = new LinkedBinaryTree("根节点");
        //依次添加节点
       TreeNode tn1 = binTree2.add(binTree2.getRoot(), "第二层左节点" , true);
       TreeNode tn2 = binTree2.add(binTree2.getRoot(), "第二层右节点" ,false );
       TreeNode tn3 = binTree2.add(tn2 , "第三层左节点" , true);
       TreeNode tn4 = binTree2.add(tn2, "第三层右节点" , false);
       TreeNode tn5 = binTree2.add(tn3, "第四层左节点" , true);
        System.out.println("tn2的左子节点：" + binTree2.leftChild(tn2));
        System.out.println("tn2的右子节点：" + binTree2.rightChild(tn2));
        System.out.println(binTree2.getDeep());


    }


}

