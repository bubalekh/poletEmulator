package app.backend;

import app.backend.models.Parameter;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public abstract class PackageInterface {
   private List<Byte> bl_package = new ArrayList<Byte>();
    public byte[] process(List<Parameter> parameters) {
        final byte initSym = (byte)0xD2;
        final byte endSym = 16;

        final byte destAdd = 1;  //////!!!
        final byte srcAdd = 2;

        for(int i = 0; i < 2; i++){
            bl_package.add(initSym);
            sizeProcessing(parameters);
        }

        bl_package.add(destAdd);
        bl_package.add(srcAdd);

        List<Byte> bl_values = new ArrayList<Byte>(valuesProcessing(parameters));

        bl_package.addAll(bl_values);

        byte crc = crcProcessing(bl_values);
        bl_package.add(crc);
        bl_package.add(endSym);

        Byte[] bytes = bl_package.toArray(new Byte[bl_package.size()]);
        return ArrayUtils.toPrimitive(bytes);
    }

    private List<Byte> valuesProcessing(List<Parameter> parameters) {
        List<Byte> bl_value = new ArrayList<Byte>();
        parameters.forEach(parameter -> {
            byte[] tempArray = new byte[Integer.parseInt(parameter.getSize())];
            BigInteger Value = new BigInteger(parameter.getValue());
            for(int i = 0; i < Integer.parseInt(parameter.getSize()); i++) {
                BigInteger tempValue = Value.shiftRight( (Integer.parseInt(parameter.getSize()) - (1 + i)) * 8);
                tempValue = tempValue.and(BigInteger.valueOf(0xFF));
                tempArray[i] = tempValue.byteValue();

                //String s1 = String.format("%8s", Integer.toBinaryString(tempArray[i] & 0xFF)).replace(' ', '0');
                //System.out.println(s1); // 10000001
            }

            List<Byte> tempList = Arrays.asList(ArrayUtils.toObject(tempArray));
            bl_value.addAll(tempList);
        });

        return bl_value;

    }

    private void sizeProcessing(List<Parameter> parameters) {
        int size = 0;

        for(int i = 0; i < parameters.size(); i++)
            size += Integer.parseInt(parameters.get(i).getSize());

        byte[] tempArray = new byte[2]; // <- assuming "in" value in 0..65535 range and we can use 2 bytes only

        tempArray[0] = (byte)((size >> 8) & 0xFF);
        tempArray[1] = (byte)(size & 0xFF);

        List<Byte> tempList = Arrays.asList(ArrayUtils.toObject(tempArray));

        bl_package.addAll(tempList);

    }

    private static byte crcProcessing(List<Byte> payload) {
        byte pdun = 0;
        for (int i = 8; i < payload.size(); ++i) {
            pdun = (byte)(pdun + payload.get(i));
        }
        return (byte)Math.abs(~pdun + 1);
    }

    private void addressesProcessing(String address) {


    }
}
