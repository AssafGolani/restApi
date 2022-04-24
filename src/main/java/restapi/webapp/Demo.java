package restapi.webapp;

import java.util.Arrays;

public class Demo {
    double[] deposits;
    String name;

    public Demo(String name){
        this.name = name;
        this.deposits = new double[10];
    }

    @Override
    public String toString() {
        return "Demo{" +
                "deposits=" + Arrays.toString(deposits) +
                ", name='" + name + '\'' +
                '}';
    }
}
