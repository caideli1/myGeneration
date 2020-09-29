package com.caideli.springBoot.tree;

import java.util.Scanner;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/29 0029 15:17
 * 描述：从键盘输入数，存为二叉树结构并打印。
 */
public class InTwoTree {
    private InTwoTree left,right;
    private char data;
    public InTwoTree creat(String des){
        Scanner scanner=new Scanner(System.in);
        System.out.println("des:"+des);
        String str=scanner.next();
        if(str.charAt(0)<'a')return null;
        InTwoTree root=new InTwoTree();
        root.data=str.charAt(0);
        root.left=creat(str.charAt(0)+"左子树");
        root.right=creat(str.charAt(0)+"右子树");
        return root;
    }
    public void midprint(InTwoTree InTwoTree){
//        中序遍历 左中右
        if(InTwoTree!=null){
            midprint(InTwoTree.left);
            System.out.print(InTwoTree.data+"  ");
            midprint(InTwoTree.right);
        }
    }
    public void firprint(InTwoTree InTwoTree){
//        先序遍历,中左右
        if(InTwoTree!=null){
            System.out.print(InTwoTree.data+" ");
            firprint(InTwoTree.left);
            firprint(InTwoTree.right);
        }
    }
    public void lastprint(InTwoTree InTwoTree){
//        后序遍历
        if(InTwoTree!=null){
            lastprint(InTwoTree.left);
            lastprint(InTwoTree.right);
            System.out.print(InTwoTree.data+"  ");
        }
    }
    public static void main(String[] args) {
        InTwoTree tree = new InTwoTree();
        InTwoTree newtree=tree.creat("根节点");
        tree.firprint(newtree);
        System.out.println();
        tree.midprint(newtree);
        System.out.println();
        tree.lastprint(newtree);
    }
}
