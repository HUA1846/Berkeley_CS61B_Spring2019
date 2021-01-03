public class LinkedListDeque<T> {
     private class Node{
         public T item;
         public Node next;
         public Node prev;

         Node(T i, Node n, Node p){
             item = i;
             next = n;
             prev = p;
         }
     }
     private int size;
     private Node sentinal;

     public LinkedListDeque(){
         sentinal = new Node(null, null, null);
         sentinal.prev = sentinal;
         sentinal.next = sentinal;
         size = 0;
     }
     public LinkedListDeque(T x){
         sentinal = new Node(x, null, null);
         Node N = new Node(x, sentinal, sentinal);
         sentinal.next = N;
         sentinal.prev = N;
         size = 1;
     }
     public LinkedListDeque(LinkedListDeque other){
         sentinal = new Node(null, null, null);
         sentinal.prev = sentinal;
         sentinal.next = sentinal;
         size = 0;
         for (int i = 0; i< other.size(); i++){
             addLast((T) other.get(i));
         }
     }
     public  void addFirst(T item){
         Node N = new Node(item, sentinal.next, sentinal);
         sentinal.next = N;
         size += 1;
     }
     public  void addLast(T item){
         Node N = new Node(item, sentinal, sentinal.prev );
         sentinal.prev.next = N;
         sentinal.prev = N;
         size += 1;
     }
     public boolean isEmpty(){
         return size == 0;
     }
     public int size(){
         return size;
     }
     public void printDeque(){
           Node p = sentinal.next;
            while (p != sentinal){
                System.out.print(p.item + " ");
                p = p.next;
            }
     }
     public T removeFirst(){
         Node p = sentinal.next;
         if(p == sentinal) return null;
         p.next.prev = sentinal;
         sentinal.next = p.next;
         size -= 1;
         return p.item;
     }
     public T removeLast(){
         Node p = sentinal.prev;
         if(p == sentinal) return null;
         sentinal.prev = p.prev;
         p.prev.next = sentinal;
         size -= 1;
         return p.item;
     }
     public T get(int index){
         if(index < 0) return  null;
         Node p = sentinal;
         while (index >=0){
             p = p.next;
             index -= 1;
             if(p == sentinal) return null;
         }
         return p.item;
     }
     public T getRecursive(int index){
        if(index >= size || index < 0) return null;

        return getRecursiveHelper(sentinal.next, index);
        

     }
     public T getRecursiveHelper(Node p, int index){
        if(index == 0) return p.item;
        p = p.next;
        return getRecursiveHelper(p, index -1);
        
     }
     public static void main(String[] args){
         LinkedListDeque<Integer> L = new LinkedListDeque<>(20);
         L.addFirst(10);
         L.addLast(30);
         LinkedListDeque<Integer> N = new LinkedListDeque(L);
//         System.out.println(L.sentinal.next.item);
//         System.out.println(L.sentinal.next.next.item);
//         System.out.println(L.sentinal.next.next.next.item);
//         System.out.println(L.sentinal.prev.item);
         System.out.println(N.size());
         N.addFirst(5);
         System.out.println(L.getRecursive(0));
         System.out.println(L.getRecursive(1));
         System.out.println(L.getRecursive(2));
         System.out.println(L.getRecursive(3));
         System.out.println(L.getRecursive(-1));
//         System.out.println(L.isEmpty());
//         System.out.println(L.removeFirst());
//         System.out.println(L.removeFirst());
//         System.out.println(L.removeFirst());
//         System.out.println(L.removeFirst());
//         System.out.println(L.isEmpty());
//         L.printDeque();

     }
}