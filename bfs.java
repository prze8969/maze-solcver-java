class queue{
    int[] arr;
    int capacity;
    int size;
    queue(int cpcity){
        arr = new int[cpcity];
        size = 0;
        capacity = cpcity;
    }

    boolean isEmpty(){
        return size ==0;
    }
    boolean isFull(){
        return size == capacity;
    }

    void enqueue(int val){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        arr[size] = val;
        size++;
    }

    void dequeue(){
        if(isEmpty()){
            System.out.println("Underflow");
            return;
        }
        for(int i = 1 ; i < size;i++){
            arr[i-1] = arr[i];
        }
        size--;
    }

    int getFront(){
        if(isEmpty()){
            System.out.println("Underflow");
            return -1;
        }
        return arr[0];
    }

    int getRear(){
        if(isEmpty()){
            System.out.println("Underflow");
            return -1;
        }
        return arr[size-1];
    }

}


public class bfs{
    int vertices;
    int[][] adjMatrix;
    boolean[] visited;
    queue q;


    bfs(int vertices){
        this.vertices = vertices;
        this.adjMatrix = new int[vertices][vertices];
    }    


    public void addEdge(int source , int destination){
        adjMatrix[source][destination] = 1;
    }

    public void initBFS(int start_vertex){
        this.visited  = new boolean[vertices];
        this.q = new queue(vertices);
        this.visited[start_vertex] = true;
        this.q.enqueue(start_vertex);
        System.out.println("BFS intialized at " + start_vertex);

    }

    public void proceedBFS(){
        if(q == null || q.isEmpty()){
            System.out.println("initialization failed");
        }

        
        while(!q.isEmpty()){
            int curr = q.getFront();
            q.dequeue();
            for(int neighbour = 0; neighbour < vertices ;  neighbour++){

                if(adjMatrix[curr][neighbour] == 1 && visited[neighbour] == false){
                    visited[neighbour] = true;
                    q.enqueue(neighbour);
                }
            }
            
            
        }
    }

} 


