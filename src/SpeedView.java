import javafx.scene.layout.BorderRepeat;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SpeedView extends JPanel implements CarObserver{
    private JFrame frame;
    private JTextArea textArea;

    public SpeedView(JFrame frame){
        this.frame = frame;
        initTextArea();
        frame.add(this, BorderLayout.EAST);
    }
    private void initTextArea(){
        this.textArea = new JTextArea(3,20);
        this.textArea.setEditable(false);
        this.add(textArea);
    }
    @Override
    public void actOnVehicleMovement(List<VehicleImage> vehicles){
        textArea.setText("");
        textArea.setRows(vehicles.size());
        int maxLength = 20;
        for(VehicleImage vehicle : vehicles){
            String vehicleString = getVehicleString(vehicle.getVehicle());
            textArea.append(vehicleString + "\n");
            if(maxLength < vehicleString.length()){
                maxLength = vehicleString.length();
                textArea.setColumns(maxLength);
            }
        }
        this.frame.repaint();
    }
    private String getVehicleString(Vehicle vehicle){
        String[] className = vehicle.getClass().getName().split("\\.");
        return className[className.length-1] + ": " +( (int) vehicle.getCurrentSpeed() ) + "km/h";
    }
}
