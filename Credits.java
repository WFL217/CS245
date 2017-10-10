/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingv1.pkg0;

import java.awt.*;
import javax.swing.*;

/**
 * Class for displaying the credits
 * @author Jason Kaufman
 */
public class Credits extends JPanel
{

    public Credits()
    {
        //Create a new container
        Container container = new Container();

        //Makes the container a box so that the credits will appear starting from the top going down
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //Moves the next label down so it is not close to te top of the window
        container.add(Box.createRigidArea(new Dimension(0, 50)));
        //Create the title and add it to the box container
        container.add(new JLabel("Credits"));

        //Moves the next label a little bit down below the "Credits" label
        container.add(Box.createRigidArea(new Dimension(0, 40)));

        //List of programmers responsible for this application
        container.add(new JLabel("Jason Kaufman"));
        container.add(new JLabel("Amir Sotoodeh"));
        container.add(new JLabel("Jacob Young"));

        //Adds the container to thhe panel
        this.add(container);
    }
}
