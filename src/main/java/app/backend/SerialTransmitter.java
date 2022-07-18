package app.backend;

import app.backend.models.Parameter;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.List;

public class SerialTransmitter extends PackageInterface {

    @Override
    public byte[] process(List<Parameter> parameters) {
        byte[] b_package = super.process(parameters);

        SerialPort transmitter = new SerialPort("COM1");

        try {
            transmitter.openPort();
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

        try {
            transmitter.writeBytes(b_package);
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

        try {
            transmitter.closePort();
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

        return b_package;
    }
}