import jdk.nashorn.internal.ir.Flags;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    List<Vehicle> vehicles = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.vehicles.add(new Volvo240(0,0));
        cc.vehicles.add(new Saab95(100,0));
        cc.vehicles.add(new Scania(200,0));
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {

                vehicle.move();
                int x = (int) Math.round(vehicle.getXPosition());
                int y = (int) Math.round(vehicle.getYPosition());
                if (!vehicleInsidePanel(vehicle,x,y)) {
                    vehicle.turnRight();
                    vehicle.turnRight();
                }
                frame.drawPanel.moveit(vehicle, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }

        private boolean vehicleInsidePanel(Vehicle vehicle,int x,int y){
            int frameX = frame.getX() - frame.drawPanel.getImageWidth(vehicle);
            int frameY = frame.getY() - frame.drawPanel.getImageHeight(vehicle);

            return x >= 0 && x <= frameX && y >= 0 && y <= frameY;
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            try {
                vehicle.gas(gas);
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    void startEngine(){
        for (Vehicle vehicle: vehicles){
            try {
                vehicle.startEngine();
            }catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    void stopEngine() {
        for (Vehicle vehicle: vehicles) {
            vehicle.stopEngine();
        }
    }

    void turboOn() {
        for (Vehicle vehicle: vehicles) {
            if(vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle vehicle: vehicles) {
            if(vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    void raiseFlatbed() {
        for (Vehicle vehicle: vehicles) {
            if (vehicle instanceof FlatbedCar) {
                if(vehicle.getCurrentSpeed() == 0) {
                    ((FlatbedCar) vehicle).raiseFlatbed();
                } else{ System.out.println("Stop " + vehicle.getClass().getName()+ " before raising the Flatbed");}
            }
        }
    }

    void lowerFlatbed() {
        for (Vehicle vehicle: vehicles) {
            if (vehicle instanceof FlatbedCar) {
                ((FlatbedCar) vehicle).lowerFlatbed();
            }
        }
    }
}
