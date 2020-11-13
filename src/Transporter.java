public interface Transporter<T extends Transportable> {
    public void loadTransportable(T toBeLoaded);
    public T unloadTransportable();
}
