package com.iasa.da31.kutoviy;

public class ThreadSample extends  Thread {
    private int start;
    private int end;
    private int SIZE;
//    private doub
    private int [][] originalMatrix;
    private int [][] transposedMatrix;

    public ThreadSample( int [][] originalMatrix, int start, int end, int SIZE ){
        this.originalMatrix = originalMatrix;
        this.start = start;
        this.end = end;
        this.SIZE = SIZE;
        this.transposedMatrix = new int[SIZE][SIZE];
    }

    @Override
    public void run(){
        System.out.println("thread started");
        int buffer=0;
        double oper;
        for( int i = start; i < end; i++ ){
            for( int j = i; j < SIZE; j++ ){
                buffer = this.originalMatrix [j][i];
                this.originalMatrix [j][i] = this.originalMatrix [i][j];
                this.originalMatrix [i][j] = buffer;
                transposedMatrix [i][j] = (int) Math.sin( Math.cos( Math.log( (buffer ) ) ) );
            }
        }
    }

    public int[][] getTransposedMatrix(){
        return this.transposedMatrix;
    }
}
