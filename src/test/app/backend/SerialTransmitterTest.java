package app.backend;

import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SerialTransmitterTest {

    @Test
    void process() {
        ObjectMapper mapper = new ObjectMapper();
        Parameters parameters;
        try {
            parameters = mapper.readValue(new File("configs/parameters.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SerialTransmitter serialTransmitter = new SerialTransmitter();
        byte[] transmitResult = serialTransmitter.process(parameters.getParameterList());
        StringBuilder sb = new StringBuilder(transmitResult.length * 2);
        for(byte b: transmitResult)
            sb.append(String.format("%02x", b));
        System.out.println("Transmitted package is: " + sb.toString());
        assertEquals("d2000ed2000e0102061023000f0010018000009610456b10", sb.toString());

    }
}