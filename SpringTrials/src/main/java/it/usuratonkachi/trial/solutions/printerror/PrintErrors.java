package it.usuratonkachi.trial.solutions.printerror;

public class PrintErrors {

    public static void main(String[] args){
        System.out.println("printerError Fixed Tests");
        String s="aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyz";
        String res = printerError(s);
        System.out.println("Res for " + s + " is " + res);
        assert "3/56".equalsIgnoreCase(res);
    }

    private static String printerError(String s){
        int err = Math.toIntExact(s.chars().boxed().filter(c -> c > 109).count());
        return err + "/" + s.length();
    }

}
