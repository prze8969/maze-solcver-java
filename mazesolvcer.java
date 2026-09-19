
class cell{
    int row;
    int col;
    cell(int row, int col){
        this.row = row;
        this.col = col;
    }
}
class queue{
    cell[] arr;
    int capacity;
    int size;
    queue(int cpcity){
        arr = new cell[cpcity];
        size = 0;
        capacity = cpcity;
    }

    boolean isEmpty(){
        return size ==0;
    }
    boolean isFull(){
        return size == capacity;
    }

    void enqueue(cell val){
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

    cell getFront(){
        if(isEmpty()){
            System.out.println("Underflow");
            return null;
        }
        return arr[0];
    }

    cell getRear(){
        if(isEmpty()){
            System.out.println("Underflow");
            return null;
        }
        return arr[size-1];
    }

}





class mazesolvcer{
    int[][] dir = {{-1,0}, {1,0},{0,-1},{0,1}};


    char[][] maze;
    boolean[][] visited;
    cell[][] parent;
    int rowlen;
    int collen;


    mazesolvcer(char[][] m){
        maze = m;
        rowlen = maze.length;
        collen = maze[0].length;
        visited = new boolean[rowlen][collen];
        parent = new cell[rowlen][collen];
    }

    void solve(cell startcell){
        queue q = new queue(rowlen*collen);

        q.enqueue(startcell);
        visited[startcell.row][startcell.col]  = true;

        while(!q.isEmpty()){

            cell curr = q.getFront();
            q.dequeue();

            if (maze[curr.row][curr.col] == 'E') {
                    System.out.println("Found path");
                    printans(curr);
                    return;
                }
            for(int neighbour = 0; neighbour<4 ; neighbour++){
                // go to all four directions
                int newrow = curr.row + dir[neighbour][0];

                int newcol = curr.col + dir[neighbour][1];

                if(newrow >=0 && newrow < rowlen &&
                    newcol >=0 && newcol < collen
                ){

                    if(maze[newrow][newcol] != '#' &&
                        !visited[newrow][newcol]
                    ){
                        visited[newrow][newcol] = true;
                        parent[newrow][newcol] = curr;
                        q.enqueue(new cell(newrow,newcol));
                    }
                }
            }
        }

        System.out.println("No path found");
    
    }


    void printmaze(char[][] m){
        for(int i = 0 ;i< rowlen ; i++){
            for (int j = 0 ; j<collen ; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    void printans(cell end){

        cell curr = end;

        while(curr!= null){
            if(maze[curr.row][curr.col]!= 'S' && maze[curr.row][curr.col]!= 'E'){
                maze[curr.row][curr.col]= '*';
            }
            curr = parent[curr.row][curr.col];
        }

        printmaze(maze);


    }
    public static void main(String[] args) {
        
        char[][] maze = {
                {'S', '.', '#', '.'},
                {'.', '.', '#', '.'},
                {'#', '.', '.', '.'},
                {'#', '#', '.', 'E'}
            };

    mazesolvcer solver = new mazesolvcer(maze);
    solver.solve(new cell(0, 0));
    }
}