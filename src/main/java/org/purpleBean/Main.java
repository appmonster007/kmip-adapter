package org.purpleBean;

import org.purpleBean.ttlv.TtlvObject;
import org.purpleBean.ttlv.serializer.TtlvObjectSerializer;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        TtlvObject t = new TtlvObject(new byte[]{0x01, 0x02, 0x03}, (byte) 0x04, 5, new byte[]{0x06, 0x07, 0x08, 0x09, 0x0a});
        TtlvObjectSerializer s = new TtlvObjectSerializer();
        System.out.println(Arrays.toString(s.serialize(t)));

    }
}