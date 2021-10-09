package priorityqueuewithheap;
public class Heap {
    
    int capacity;
    Node[] arr;
    int size;
    
    boolean isMinHeap;  // true if minHeap, false if maxHeap
    
    int timer;    // For each push, the timer will increase by 1
    
    public Heap(boolean isMinHeap, int cap){
        // Initialize the heap here
        // Don't forget that we will build the binary heap using...
        // ... the concept of "Complete binary tree based on the array implementation"
        // ... The 0 index will not be used, The index starts from 1 (remember?)
        this.size = 1;
        this.isMinHeap = isMinHeap;
        this.capacity = cap;
        this.arr = new Node[cap];
    }
    public Node top(){
        return this.arr[1];
    }
    
    public void push(Node node){
        // Increase timer each time you push something into the Queue
        timer++;
        node.timestamp = timer; // Stamp the time number to the node
        
        // To do:
        // 1. Push the new node at the end of the array (via back pointer)
        this.arr[size] = node;
        // 2. Sift (percolate) it up the heap
        //      * Check priority of the current node with its parent
        //      * Swap the current node if the priority is higher than the parent
        //      * Repeat the process until reaching the root or there is no swap (Pls use while loop)
        int curIndex = size;
        while(curIndex != 1){
            if(this.arr[curIndex].compare(this.arr[(int)Math.floor(curIndex / 2)])){
                swap(curIndex,(int)Math.floor(curIndex/2));
                curIndex = (int)Math.floor(curIndex/2);
            }
            else break;
        }
        // 3. Increase the heap size
        this.size++;
        
    }
    
    public Node pop(){
        
        // To do:
        // 1. Mark the root for return (Don't forget to return this node)
        Node root = this.arr[1];
        // 2. Move the last node to the root (change pointer, set null)
        this.arr[1] = this.arr[size - 1];
        this.arr[size - 1] = null;
        // 3. Decrease the heap size
        this.size--;
        // 4. Sift (percolate) it down the heap
        //      * Check priority of the current node with both children
        //      * Swap the current node with the lower priority child
        //      * Repeat the process until the node has no child or there is no swap (Pls use while loop)
        int curIndex = 1;
        //Node curNode = this.arr[curIndex];
        //until node have no child
        while(curIndex * 2 < size){
            int child; //index of child that we want to swap
            if((curIndex * 2) + 1 == size) child = curIndex * 2; //only have left child
            else{//have both child
                //left have more priority than right
                if(this.arr[curIndex * 2].compare(this.arr[(curIndex * 2) + 1])){
                    child = (curIndex * 2);
                //right have more priority than left
                }else child = (curIndex * 2) + 1;
            }
            //if child have more priority than parent --> swap
            if(this.arr[child].compare(this.arr[curIndex])){
                swap(curIndex, child);
                curIndex = child;
            }else break;
            
        }
 
        return root;

    }

    // This is an optional function, you may use it if you know what it is
    // This function is complete, no need to edit
    public void swap(int index1, int index2){
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
