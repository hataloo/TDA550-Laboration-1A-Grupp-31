public abstract class IVehicleFactory {
    public static IVehicle createSaab95(int x, int y) {
        return new Saab95(x,y);
    }

    public static IVehicle createSaab95() {
        return new Saab95();
    }

    public static IVehicle createVolvo240(int x, int y){
        return new Volvo240(x,y);
    }

    public static IVehicle createVolvo240(){
        return new Volvo240();
    }

    public static IVehicle createScania(int x, int y){
        return new Scania(x,y);
    }

    public static IVehicle createScania(){
        return new Scania();
    }

    public static IVehicle createCarTransport(int x, int y){
        return new CarTransport(x,y);
    }

    public static IVehicle createCarTransport(){
        return new CarTransport();
    }

    public static IVehicle createCarFerry(int x, int y, int capacity){
        return new CarFerry(x,y,capacity);
    }

    public static IVehicle createCarFerry(int x, int y){
        return new CarFerry(x,y);
    }

    public static IVehicle createCarFerry(int capacity){
        return new CarFerry(capacity);
    }

    public static IVehicle createCarFerry(){
        return new CarFerry();
    }



}
