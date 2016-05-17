package com.iasa.da31.kutoviy;

import com.sun.deploy.util.ArrayUtil;

import java.util.Scanner;

public class Main {

    public static int SIZE;
    public static final int THREADS_COUNT = 4;
    public static int [][] originalMatrix;

    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        ThreadSample [] thread = new ThreadSample[ THREADS_COUNT ];
        SIZE = Integer.parseInt(sc.nextLine());
        originalMatrix = new int [SIZE][SIZE];
        int [][] transposedMatrix;

        originalMatrix = fillMatrix( SIZE );

        long startTime = System.nanoTime();
        transposeMatrix(thread);
        long endTime = System.nanoTime();
        long algoTimeParalel = endTime - startTime;

        long startTimeLin = System.nanoTime();
        transposeMatrix1(thread);
        long endTimeLin = System.nanoTime();
        long algoTimeLin = endTimeLin - startTimeLin;

        System.out.println("Paralel time --> " + algoTimeParalel);
        System.out.println("Lin time --> " + algoTimeLin);
    }

    public static void transposeMatrix ( ThreadSample[] thread)  {
        for( int i = 0; i < THREADS_COUNT; i++ ){
            int start = i*SIZE/THREADS_COUNT;
            int end = ((i+1)*SIZE)/THREADS_COUNT;
            thread[i] = new ThreadSample(originalMatrix, start, end, SIZE );
            thread[i].start();
        }

        for( int i = 0; i < THREADS_COUNT; i++ ) {
            try {
                thread[i].join();
            }catch( Exception e ){
                e.printStackTrace();
            }
        }
    }

    public static void transposeMatrix1 ( ThreadSample[] thread ){
        for( int i = 0; i < THREADS_COUNT; i++ ){
            int start = i*SIZE/THREADS_COUNT;
            int end = ((i+1)*SIZE)/THREADS_COUNT;
            thread[i] = new ThreadSample(originalMatrix, start, end, SIZE );
            thread[i].run();
        }

        for( int i = 0; i < THREADS_COUNT; i++ ) {
            try {
                thread[i].join();
            }catch( Exception e ){
                e.printStackTrace();
            }
        }
    }

    public static void printMatrix ( int[][] matrix ) {
        for( int i = 0; i < SIZE; i++ ) {
            for( int j = 0; j < SIZE; j++ ) {
                System.out.print( matrix[i][j] + "  " );
            }
            System.out.println();
        }
    }

    public static int[][] fillMatrix ( int SIZE ) {
        int [][] matrix = new int [SIZE][SIZE];
        for( int i = 0; i < SIZE; i++ ) {
            for( int j = 0; j < SIZE; j++ ) {
                matrix[i][j] = (int)(Math.random()*100);
            }
        }
        return matrix;
    }
}