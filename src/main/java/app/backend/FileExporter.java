package app.backend;

import app.backend.models.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter extends PackageInterface {

    private final File file = new File("data.txt");
    private final FileWriter fileWriter = new FileWriter(file);
    private final FileOutputStream fos = new FileOutputStream(file);
    private final StringBuilder stringBuilder = new StringBuilder();

    public FileExporter() throws IOException {
    }

    public String export(List<Parameter> parameters) throws IOException {
        valuesProcessing(parameters);
        fileWriter.append(stringBuilder.toString());
        fileWriter.close();
        System.out.println("export test");

        return stringBuilder.toString();
    }

    @Override
    public byte[] process(List<Parameter> parameters) {

        byte[] b_package = super.process(parameters);

        try {
            fos.write(b_package);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return b_package;
    }

    private void valuesProcessing(List<Parameter> parameters) {
        StringBuilder valuesStr = new StringBuilder();
        parameters.forEach(parameter -> {
            int hexPow = 0;
            int tempValue = Integer.parseInt(parameter.getValue());
            while(tempValue >= 16){
                tempValue /= 16;
                hexPow++;
            }
            valuesStr.append("0".repeat(Integer.parseInt(parameter.getSize()) * 2 - (hexPow + 1)));
            valuesStr.append(Integer.toHexString(Integer.parseInt(parameter.getValue())));
        });

        stringBuilder.append(valuesStr.toString());

    }
}
