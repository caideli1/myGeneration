package com.caideli.springBoot.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/29 0029 10:50
 * 描述： 将一个数组中的数以二叉树的存储结构存储，并遍历打印。
 */
public class ArrayToTree {
    public ArrayToTree left;
    public ArrayToTree right;
    public ArrayToTree root;
    //    数据域
    private Object data;
    //    存节点
    public List<ArrayToTree> datas;

    public ArrayToTree(ArrayToTree left, ArrayToTree right, Object data){
        this.left=left;
        this.right=right;
        this.data=data;
    }
    //    将初始的左右孩子值为空
    public ArrayToTree(Object data){
        this(null,null,data);
    }

    public ArrayToTree() {

    }

    public void creat(Object[] objs){
        datas=new ArrayList<ArrayToTree>();
        //        将一个数组的值依次转换为Node节点
        for(Object o:objs){
            datas.add(new ArrayToTree(o));
        }
//        第一个数为根节点
        root=datas.get(0);
//        建立二叉树
        for (int i = 0; i <objs.length/2; i++) {
//            左孩子
            datas.get(i).left=datas.get(i*2+1);
//            右孩子
            if(i*2+2<datas.size()){//避免偶数的时候 下标越界
                datas.get(i).right=datas.get(i*2+2);
            }
        }
        //System.out.println(JSONArray.toJSONString(datas));
    }
    //先序遍历
    public void preorder(ArrayToTree root){
        if(root!=null){
            System.out.println(root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }
    //中序遍历
    public void inorder(ArrayToTree root){
        if(root!=null){
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }
    //    后序遍历
    public void afterorder(ArrayToTree root){
        if(root!=null){
            System.out.println(root.data);
            afterorder(root.left);
            afterorder(root.right);
        }
    }
    public static void main(String[] args) {
        ArrayToTree ArrayToTree =new ArrayToTree();
        Object []a={2,4,5,7,1,6,12,32,51,22};
        ArrayToTree.creat(a);
        ArrayToTree.preorder(ArrayToTree.root);
    }

}
