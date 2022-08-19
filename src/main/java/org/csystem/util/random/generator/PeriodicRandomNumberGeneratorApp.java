package org.csystem.util.random.generator;

import org.csystem.util.console.Console;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class PeriodicRandomNumberGeneratorApp {
    private static void checkArguments(String [] args)
    {
        if (args.length != 5) {
            Console.Error.writeLine("Wrong number of arguments");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try {
            checkArguments(args);
            var random = new Random();
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);
            new PeriodicIntGenerator(Integer.parseInt(args[0]), Long.parseLong(args[3]), Long.parseLong(args[4]), new IntSupplier() {
                public int getAsInt() {
                    return random.nextInt(min, bound);

                }
            }).start(new IntConsumer() {
                public void accept(int value) {
                    Console.write("%d ", value);
                }
            });
        }
        catch (NumberFormatException ex) {
            Console.Error.write("Invalid values");
        }

        try {
            checkArguments(args);
            new PeriodicRandomIntGenerator.Builder()
                    .setCount(Integer.parseInt(args[0]))
                    .setMin(Integer.parseInt(args[1]))
                    .setBound(Integer.parseInt(args[2]))
                    .setDelay(Long.parseLong(args[3]))
                    .setPeriod(Long.parseLong(args[4]))
                    .build().start(new IntConsumer() {
                        public void accept(int value) {
                            Console.write("%d ", value);
                        }
                    });
        }

        catch (NumberFormatException ex){
            Console.writeLine("Invalid Values");
        }

    }

}
