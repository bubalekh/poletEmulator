package app.backend;

import app.backend.models.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter {

    private final File file = new File("data.txt");
    private final FileWriter fileWriter = new FileWriter(file);
    private final StringBuilder stringBuilder = new StringBuilder();

    public FileExporter() throws IOException {
    }

    public void Test(List<Parameter> parameters) throws IOException {
        parameters.forEach(parameter -> {
            if (Integer.parseInt(parameter.getSize()) <= 1) {
                if (Integer.parseInt(parameter.getValue()) < 15) {
                    stringBuilder.append("0");
                }
            }
            else {
                stringBuilder.append("0".repeat(Integer.parseInt(parameter.getSize()) * 2 - 1));
            }
            stringBuilder.append(Integer.toHexString(Integer.parseInt(parameter.getValue())));
        });

        fileWriter.append(stringBuilder.toString());
        fileWriter.close();
        System.out.println("test");
    }
}
