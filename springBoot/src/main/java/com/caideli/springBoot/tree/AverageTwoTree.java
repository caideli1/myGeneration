package com.caideli.springBoot.tree;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/29 0029 15:40
 * 描述：
 * https://www.cnblogs.com/qm-article/p/9349681.html
 */
public class AverageTwoTree {

    public Node root;

    public int LEFT=1,RIGHT=2;

    public int size=0;
    /**
     * 平衡二叉树的构建
     */
    static class Node{
        Node parent;
        Node leftChild;
        Node rightChild;
        int val;
        public Node(Node parent, Node leftChild, Node rightChild,int val) {
            super();
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.val = val;
        }

        public Node(int val){
            this(null,null,null,val);
        }

        public Node(Node node,int val){
            this(node,null,null,val);
        }

    }

    /**
     * 右旋
     * @param node
     * @return
     */
    public Node rightRotation(Node node){
        if(node != null){
            Node leftChild = node.leftChild;// 用变量存储node节点的左子节点
            node.leftChild = leftChild.rightChild;// 将leftChild节点的右子节点赋值给node节点的左节点
            if(leftChild.rightChild != null){// 如果leftChild的右节点存在，则需将该右节点的父节点指给node节点
                leftChild.rightChild.parent = node;
            }
            leftChild.parent = node.parent;
            if(node.parent == null){// 即表明node节点为根节点
                this.root = leftChild;
            }else if(node.parent.rightChild == node){// 即node节点在它原父节点的右子树中
                node.parent.rightChild = leftChild;
            }else if(node.parent.leftChild == node){
                node.parent.leftChild = leftChild;
            }
            leftChild.rightChild = node;
            node.parent = leftChild;
            return leftChild;
        }
        return null;
    }

    /**
     * 左旋
     * @param node
     * @return
     */
    public Node leftRotation(Node node){
        if(node != null){
            Node rightChild = node.rightChild;
            node.rightChild = rightChild.leftChild;
            if(rightChild.leftChild != null){
                rightChild.leftChild.parent = node;
            }
            rightChild.parent = node.parent;
            if(node.parent == null){
                this.root = rightChild;
            }else if(node.parent.rightChild == node){
                node.parent.rightChild = rightChild;
            }else if(node.parent.leftChild == node){
                node.parent.leftChild = rightChild;
            }
            rightChild.leftChild = node;
            node.parent = rightChild;

        }
        return null;
    }

    /**
     * 插入
     * @param val
     * @return
     */
    public boolean put(int val){
        return putVal(root,val);
    }
    private boolean putVal(Node node,int val){
        if(node == null){// 初始化根节点
            node = new Node(val);
            root = node;
            size++;
            return true;
        }
        Node temp = node;
        Node p;
        int t;
        /**
         * 通过do while循环迭代获取最佳节点，
         */
        do{
            p = temp;
            t = temp.val-val;
            if(t > 0){
                temp = temp.leftChild;
            }else if(t < 0){
                temp = temp.rightChild;
            }else{
                temp.val = val;
                return false;
            }
        }while(temp != null);
        Node newNode = new Node(p, val);
        if(t > 0){
            p.leftChild = newNode;
        }else if(t < 0){
            p.rightChild = newNode;
        }
        rebuild(p);// 使二叉树平衡的方法
        size++;
        return true;
    }

    private void rebuild(Node p){
        while(p != null){
            if(calcNodeBalanceValue(p) == 2){// 说明左子树高，需要右旋或者 先左旋后右旋
                fixAfterInsertion(p,LEFT);// 调整操作
            }else if(calcNodeBalanceValue(p) == -2){
                fixAfterInsertion(p,RIGHT);
            }
            p = p.parent;
        }
    }

    private int calcNodeBalanceValue(Node node){
        if(node != null){
            return getHeightByNode(node);
        }
        return 0;
    }
    // 计算node节点的高度
    public int getChildDepth(Node node){
        if(node == null){
            return 0;
        }
        return 1+Math.max(getChildDepth(node.leftChild),getChildDepth(node.rightChild));
    }
    public int getHeightByNode(Node node){
        if(node == null){
            return 0;
        }
        return getChildDepth(node.leftChild)-getChildDepth(node.rightChild);
    }

    /**
     * 调整树结构，先后顺序需要换一下，先判断LR型和RL型进行二次旋转
     * @param p
     * @param type
     */
    private void fixAfterInsertion(Node p, int type) {
        if(type == LEFT){//需要右旋或者 先左旋后右旋
            final Node leftChild = p.leftChild;
            if(leftChild.rightChild != null){// 先左旋后右旋
                leftRotation(leftChild);
                rightRotation(p);
            }else if(leftChild.leftChild != null){//右旋
                rightRotation(p);
            }

        }else{
            final Node rightChild = p.rightChild;
            if(rightChild.leftChild != null){// 先右旋，后左旋
                rightRotation(p);
                leftRotation(rightChild);
            }else if(rightChild.rightChild != null){// 左旋
                leftRotation(p);
            }
        }
    }




}
