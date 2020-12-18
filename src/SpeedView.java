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
        this.setPreferredSize(textArea.getSize());
        frame.add(this);
    }

    private void initTextArea(){
        this.textArea = new JTextArea(3,10);
        this.textArea.setEditable(false);
        this.add(textArea);
    }

    @Override
    public void actOnVehicleMovement(List<IVehicle> vehicles){
        textArea.setText("");
        textArea.setRows(vehicles.size());
        int maxLength = 20;
        for(IVehicle vehicle : vehicles){
            String vehicleString = getVehicleString(vehicle);
            textArea.append(vehicleString + "\n");
            if(maxLength < vehicleString.length()){
                maxLength = vehicleString.length();
                textArea.setColumns(maxLength);
            }
        }
        this.setPreferredSize(textArea.getSize());
        this.frame.repaint();
    }
    private String getVehicleString(IVehicle vehicle){
        String[] className = vehicle.getClass().getName().split("\\.");
        return className[className.length-1] + ": " +( (int) vehicle.getCurrentSpeed() ) + " km/h";
    }
}
