public interface Transporter<T>{

    public void loadTransportable(T toBeLoaded);
    public T unloadTransportable(T toBeUnloaded);

}

