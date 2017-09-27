package odev;

import java.util.*;

/**
 * Created by eray on 4/13/17.
 */
public class FamilyTree<E> extends BinaryTree<E> {

    Queue<Node<E>> nodeList = new LinkedList<>();

    public FamilyTree(E rootName)
    {
        root = new Node<E>(rootName);
        nodeList.add(root);
    }

    /*
            Hasan
            /
        Ayse
        /   \
      Ali   Sema
     */
    /**
     * istenilen sekilde agac olusumu icin belirli bir yapiya gore ekleme islemleri uygulanmistir.
     * @param namePerson
     * @param nameOfParent
     * @param nicknameOfPerson
     */
    public void add(E namePerson, String nameOfParent, String nicknameOfPerson )
    {
        if(nameOfParent.equals(root.data) && root.left == null)
        {
            root.left = new Node<E>(namePerson);
            nodeList.offer(root.left);
            return;
        }

        // gercek node listesi local node listesine kopyalanir.
        Queue<Node<E>> copyNodeList = new LinkedList(nodeList);

        while (true)
        {
            Node<E> temp = copyNodeList.poll();

            // ilk cocuk parent in sol tarafina eklenir
            if(nameOfParent.equals(temp.data))
            {
                if(temp.left == null)
                {
                    temp.left = new Node<E>(namePerson);
                    nodeList.offer(temp.left);
                    return;
                }
                // diger cocuklar ilk cocugun soluna eklenir
                else
                {
                    while (!copyNodeList.isEmpty())
                    {
                        Node<E> temp2 = copyNodeList.poll();
                        if(temp2.right == null)
                        {
                            temp2.right = new Node<E>(namePerson);
                            nodeList.offer(temp2.right);
                            break;
                        }
                    }
                    return;
                }
            }
        }

    }

    public Iterator<E> iterator()
    {
        return new FamilyIter<>(root);
    }

    public void levelOrderToString()
    {
        Iterator<E> itr = this.iterator();

        Scanner reader = new Scanner(System.in);

        System.out.println("Step step gitmek icin -- ENTER -- yapin");

        while (itr.hasNext())
        {
            System.out.print(itr.next());
            String readstr=reader.nextLine();

            if(readstr.equals(""))
                continue;
        }
    }

    private class FamilyIter<E> implements Iterator<E>
    {

        private Queue<Node<E>> iterQueue = new LinkedList<>();

        public FamilyIter(Node<E> tempRoot)
        {
            iterQueue.offer(tempRoot);
        }

        @Override
        public boolean hasNext() {
            return !iterQueue.isEmpty();
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();

            Node<E> temp = iterQueue.poll();

            if(temp.left != null)
                iterQueue.offer(temp.left);
            if(temp.right != null)
                iterQueue.offer(temp.right);

            return temp.data;
        }
    }

}

