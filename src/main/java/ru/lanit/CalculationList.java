package ru.lanit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculationList {
    static private List listCalc = Collections.synchronizedList(new ArrayList<>());

    static Object getValueByIndex(int index) {
        return listCalc.get(index);
    }

    static void add(Object object) {
        listCalc.add(object);
    }

    static int listSize() {
        return listCalc.size();
    }

}
