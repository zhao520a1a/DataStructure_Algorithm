package dataStructure.binaryTree;

/**
 * Created by golden on 2016/9/27 0027.
 * 二叉树的顺序存储:  使用数组来记录该树的所有节点数据；
 * 完全二叉树的性质：
 对其节点按层从左向右编号(从0开始)：
 当前节点的编号：i
 它的父节点编号：i/2 + 1
 它的左孩子编号：2i + 1
 它的右孩子编号：2i + 2

 */



public class ArrayBinTree<T> {

    private Object[] datas;
    private int defaultDeep = 8;
    private int deep;
    private int arraySize;

    //以默认的深度来创建二叉树
   public ArrayBinTree() {
       this.deep = this.defaultDeep;
       this.arraySize = (int)Math.pow(2, deep) - 1;  //计算2的deep次方减1，即为满二叉树的节点个数；
       datas = new Object[arraySize];
   }

    //以指定深度来创建二叉树
   public ArrayBinTree(int deep) {
        this.deep = deep;
       this.arraySize = (int)Math.pow(2, deep) - 1;  //计算2的deep次方减1，即为满二叉树的节点个数；
       datas = new Object[arraySize];
    }

    //以指定深度,指定根节点创建二叉树
    public ArrayBinTree(int deep , T data){
        this.deep = deep;
        this.arraySize = (int)Math.pow(2 , deep) - 1;
        datas = new Object[arraySize];
        datas[0] = data;
    }

    /**
     * 为指定节点添加子节点。
     * @param parentIndex 需要添加子节点的父节点的索引
     * @param data 新子节点的数据
     * @param isLeft 是否为左节点
     */
    public void add(int parentIndex, T data, boolean isLeft) {
        if((parentIndex < 0) || (parentIndex * 2 + 1 >= arraySize)) {
            throw new IndexOutOfBoundsException("越界异常,Index: " + parentIndex + ", Size: " + arraySize);
        } else {
            if(datas[parentIndex] == null) {
                throw new RuntimeException(parentIndex + "处节点为null,无法添加子节点");
            } else {
                if(isLeft) {
                    if(datas[parentIndex * 2 + 1] != null) {
                        throw new RuntimeException(parentIndex + "节点已有左子节点，无法添加左子节点");
                    } else {
                        datas[parentIndex * 2 + 1] = data;  //左孩子
                    }
                } else {
                    if(datas[parentIndex * 2 + 2] != null ) {
                        throw new RuntimeException(parentIndex + "节点已有右子节点，无法添加右子节点");
                    } else {
                        datas[parentIndex * 2 + 2] = data;  //右孩子
                    }
                }
            }
        }
    }

    //返回指定节点的左子节点,当不存在时返回null
    public T leftChild (int index) {
        if((index < 0) || (index * 2 + 1 >= arraySize)) {
            throw new IndexOutOfBoundsException("越界异常,Index: " + index + ", Size: " + arraySize);
        }
        return (T)datas[index * 2 + 1];
    }

    //返回指定节点的右子节点,当不存在时返回null
    public T rightChild (int index) {
        if((index < 0) || (index * 2 + 1 >= arraySize)) {
            throw new IndexOutOfBoundsException("越界异常,Index: " + index + ", Size: " + arraySize);
        }
        return (T)datas[index * 2 + 2];
    }

    //返回指定节点的位置
    public int pos (T data) {
        for(int i=0; i<arraySize && datas[i]!=null; i++) {
            if(datas[i] == data) {
                return i;
            }
        }
        return -1;
    }

    //判断二叉树是否为空。
    public boolean empty(){
        return datas[0] == null;
    }

    //返回根节点。
    public T getRoot(){
        return (T)datas[0] ;
    }

    //返回该二叉树的深度。
    public int getDeep(){
        return deep;
    }

    public String toString(){
        return java.util.Arrays.toString(datas);
    }

}
