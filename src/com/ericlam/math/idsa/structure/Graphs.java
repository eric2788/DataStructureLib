package com.ericlam.math.idsa.structure;

public class Graphs {
    public enum Type{
        GRAPH{
            @Override
            int getDots(int n) {
                return n;
            }

            @Override
            int getLines(int n) {
                return (n * (n - 1))/2;
            }
        },
        CYCLE{
            @Override
            int getDots(int n) {
                return n;
            }

            @Override
            int getLines(int n) {
                return n;
            }
        },
        WHEEL() {
            @Override
            int getDots(int n) {
                return n+1;
            }

            @Override
            int getLines(int n) {
                return n * 2;
            }
        },
        CUBE() {
            @Override
            int getDots(int n) {
                return (int)Math.pow(2,n);
            }

            @Override
            int getLines(int n) {
                return n * (int)Math.pow(2,n-1);
            }
        };

        abstract int getDots(int n);
        abstract int getLines(int n);
    }

    private Type type;
    private int n;

    public Graphs(Type type, int n) {
        this.type = type;
        this.n = n;
    }

    public int getDots(){
        return type.getDots(n);
    }

    public int getLines(){
        return type.getLines(n);
    }

    public int getVertices(){
        return getDots();
    }

    public int getEdges(){
        return getLines();
    }
}
