package krakout;

import krakout.movement.Position;
import krakout.movement.nonplayerobject.Item;
import krakout.movement.nonplayerobject.Pinball;

public class Start {

    public static void main(String[] args) {

        System.out.println("Spiel");

        //Player 1 befindet sich an der coordinate 20 , 30
        Position p1 = new Position(20, 30);
        //(mit var) Player 2 befindet sich an der coordinate 10 , 50
        var p2 = new Position(10, 50);

        //Player 2 bewegt sich
        p2.down(5);
        p2.right(100);

        //Formating output of the position
        System.out.println("\n");
        System.out.println("Player 1 is at the " + p1);
        System.out.println("\n");
        System.out.println("Player 2 is at the " + p2);
        System.out.println("\n");

        //Creating Object Pinball and Item
        Pinball ball = new Pinball();
        Item wall = new Item();

        //Formating output of the Pinball and Item
        System.out.println("\n");
        System.out.println(ball);
        System.out.println("\n");
        System.out.println(wall);
        System.out.println("\n");

        //Moving with updatePosition
        ball.updatePosition("UP");
        wall.updatePosition("RIGHT");

        //Formating outpot for Pinball and Item
        System.out.println("\n");
        System.out.println(ball);
        System.out.println("\n");
        System.out.println(wall);
        System.out.println("\n");

    }
}
