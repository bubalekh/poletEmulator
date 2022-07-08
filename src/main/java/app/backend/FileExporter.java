package app.backend;

import app.backend.models.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter extends PackageInterface {

    private final File file = new File("data.txt");
    private final FileWriter fileWriter = new FileWriter(file);
    private final StringBuilder stringBuilder = new StringBuilder();

    public FileExporter() throws IOException {
    }

    public String Export(List<Parameter> parameters) throws IOException {
        ValuesProcessing(parameters);
        fileWriter.append(stringBuilder.toString());
        fileWriter.close();
        System.out.println("export test");

        return stringBuilder.toString();
    }

    @Override
    public String process(List<Parameter> parameters) {

        super.process(parameters);

        final int initSym = 0xD2;
        final int endSym = 16;
        final String destAdd = "1";
        final String srcAdd = "2";


        for(int i = 0; i < 2; i++){
            stringBuilder.append(Integer.toHexString(initSym));
            SizeProcessing(parameters);
        }

        AddressesProcessing(destAdd);
        AddressesProcessing(srcAdd);
        ValuesProcessing(parameters);

        /* // SRC
        List<Byte> bl_values = new ArrayList<>();
        parameters.forEach(parameter -> {
            bl_values.add(Byte.parseByte(parameter.getValue()));
        });
        byte src = SrcProcessing(bl_values);
        stringBuilder.append(src);
        */

        stringBuilder.append(endSym);

        try {
            fileWriter.append(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    private void ValuesProcessing(List<Parameter> parameters) {
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

    private void SizeProcessing(List<Parameter> parameters) {
        int size = 0;

        for(int i = 0; i < parameters.size(); i++)
            size += Integer.parseInt(parameters.get(i).getSize());

        int hexPow = 0;
        int tempValue = size;
        while(tempValue >= 16) {
            tempValue /= 16;
            hexPow++;
        }
        stringBuilder.append("0".repeat(4 - (hexPow + 1)));
        stringBuilder.append(Integer.toHexString(size));
    }

    private byte SrcProcessing(List<Byte> payload){

        byte pdun = 0;
        for (int i = 8; i < payload.size(); ++i) {
            pdun = (byte)(pdun + payload.get(i));
        }
        return (byte)Math.abs(~pdun + 1);

    }

    private void AddressesProcessing(String address) {

        if (Integer.parseInt(address) <= 15) {
            stringBuilder.append("0");
        }
        stringBuilder.append(Integer.toHexString(Integer.parseInt(address)));

    }
}
