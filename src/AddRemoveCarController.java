import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.VolatileCallSite;
import java.util.Random;

public class AddRemoveCarController extends JPanel{
    private JFrame frame;
    private CarModel model;
    private JButton addButton;
    private JButton removeButton;

    public AddRemoveCarController(JFrame frame, CarModel model) {
        this.frame = frame;
        this.model = model;
        this.setLayout(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(frame.getWidth()/8,frame.getHeight()/4));
        initButtons();
        this.frame.add(this);
        setListeners();
    }

    private void initButtons() {
        addButton = new JButton("Add");
        this.add(addButton,0);

        removeButton = new JButton("Remove");
        this.add(removeButton,1);
    }

    private void setListeners(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = model.getNumberOfCars();
                if (number < 10) {
                    int xPosition = number*100;
                    Vehicle vehicle = getRandomVehicle();
                    vehicle.setXPosition(xPosition);
                    model.add(vehicle);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getNumberOfCars() > 0) {
                    model.removeLast();
                }
            }
        });
    }

    private Vehicle getRandomVehicle() {
        Random r = new Random();
        /* (Failed) Attempt at a nicer solution :)
        Class[] myList = {Scania.class, Volvo240.class, Saab95.class};

        try {
            Vehicle test = (Vehicle) Scania.forName().getConstructor(Integer.class, Integer.class).newInstance(0, 0);
        }catch(Exception e){}
        */

        int choice =  r.nextInt(3);
        Vehicle vehicle;
        if (choice == 0) {
            vehicle = new Saab95();
        } else if (choice == 1) {
            vehicle = new Volvo240();
        } else {
            vehicle = new Scania();
        }
        return vehicle;
    }

}
