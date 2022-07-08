package app.backend;

import app.backend.models.Parameter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PackageInterface {
   private List<Byte> bl_package = new ArrayList<Byte>();
    public String process(List<Parameter> parameters) {
        final byte initSym = (byte)0xD2;
        final byte endSym = 16;
        final byte destAdd = 1;
        final byte srcAdd = 2;



        for(int i = 0; i < 2; i++){
            bl_package.add(initSym);
            SizeProcessing(parameters);
        }

        bl_package.add(destAdd);
        bl_package.add(srcAdd);

        ValuesProcessing(parameters);

        /* // SRC
        List<Byte> bl_values = new ArrayList<>();
        parameters.forEach(parameter -> {
            bl_values.add(Byte.parseByte(parameter.getValue()));
        });
        byte src = SrcProcessing(bl_values);
        stringBuilder.append(src);
        */

       // stringBuilder.append(endSym);

        bl_package.forEach(parameter -> {
            if((int)parameter >= 0)
                System.out.println(Integer.toHexString((int)parameter));
            else
                System.out.println(Integer.toHexString(~(int)parameter));
        });

        int tempValue2 = 0;
        int i2 = 5;
        for(int i = 17; i < 22; i++){

            tempValue2 |= (bl_package.get(i) << (i2 * 8));
            i2--;
        }

      return "0"; //return stringBuilder.toString();
    }

    private void ValuesProcessing(List<Parameter> parameters) {
        StringBuilder valuesStr = new StringBuilder();
        parameters.forEach(parameter -> {
            byte[] tempArray = new byte[Integer.parseInt(parameter.getSize())];
            int tempValue = Integer.parseInt(parameter.getValue());
            for(int i = 0; i < Integer.parseInt(parameter.getSize()); i++) {
                tempArray[Integer.parseInt(parameter.getSize()) - (i + 1)] = (byte) ((tempValue >> ((Integer.parseInt(parameter.getSize()) - 1 - i) * 8)) & 0xFF);
            }

            List<Byte> tempList = Arrays.asList(ArrayUtils.toObject(tempArray));
            bl_package.addAll( tempList);
        });

      //  stringBuilder.append(valuesStr.toString());

    }

    private void SizeProcessing(List<Parameter> parameters) {
        int size = 0;

        for(int i = 0; i < parameters.size(); i++)
            size += Integer.parseInt(parameters.get(i).getSize());

        byte[] tempArray = new byte[2]; // <- assuming "in" value in 0..65535 range and we can use 2 bytes only

        tempArray[0] = (byte)((size >> 8) & 0xFF);
        tempArray[1] = (byte)(size & 0xFF);

        List<Byte> tempList = Arrays.asList(ArrayUtils.toObject(tempArray));

        bl_package.addAll( tempList);

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
        //    stringBuilder.append("0");
        }
     //   stringBuilder.append(Integer.toHexString(Integer.parseInt(address)));

    }
}
