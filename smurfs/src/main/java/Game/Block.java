/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author bjk_c
 */
public class Block {
    public int X,Y;
    public double Weight;
    public int [] Previous;
    public boolean visited;
    
    public Block(){}

    public Block(int X, int Y, double Weight, int[] Previous, boolean visited) {
        this.X = X;
        this.Y = Y;
        this.Weight = Weight;
        this.Previous = Previous;
        this.visited = visited;
    }

    public int[] getPrevious() {
        return Previous;
    }

    public void setPrevious(int[] Previous) {
        this.Previous = Previous;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double Weight) {
        this.Weight = Weight;
    }


    

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
