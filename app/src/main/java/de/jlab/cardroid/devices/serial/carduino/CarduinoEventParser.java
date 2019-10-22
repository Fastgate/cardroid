package de.jlab.cardroid.devices.serial.carduino;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.jlab.cardroid.devices.DeviceHandler;

public final class CarduinoEventParser extends CarduinoPacketParser implements EventObservable {

    private DeviceHandler device;
    private ArrayList<EventListener> listeners = new ArrayList<>();

    @Override
    protected boolean shouldHandlePacket(@NonNull CarduinoSerialPacket packet) {
        return CarduinoPacketType.EVENT.equals(packet.getPacketType());
    }

    @Override
    protected void handlePacket(@NonNull CarduinoSerialPacket packet) {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onEvent(packet.getPacketId());
        }
    }

    @Override
    public void addListener(@NonNull EventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(@NonNull EventListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void setDevice(@NonNull DeviceHandler device) {
        this.device = device;
    }

    @Nullable
    @Override
    public DeviceHandler getDevice() {
        return this.device;
    }
}
