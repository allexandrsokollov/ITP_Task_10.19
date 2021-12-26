package vsu.cs.sokolov.argsHeandler;


public class ParamsReader {

    public static CmdParams parseArgs(String[] args) {
        CmdParams prams = new CmdParams();

        if (args.length < 1) {
            prams.help = true;
            return prams;
        }

        if (args.length < 4) {
            prams.error = true;
            prams.help = true;
            return prams;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help")) {
                prams.help = true;

                System.out.println("Example input");
                System.out.println("--inputFile src/main/resources/toShow --outputFile src/main/resources/out");
                return prams;
            }

            if (args[i].equals("--inputFile") && (i + 1 != args.length)) {
                i++;
                prams.inputFile = args[i];
                continue;
            }

            if (args[i].equals("--outputFile") && (i + 1 != args.length)) {
                i++;
                prams.outputFile = args[i];
            }
        }

        if (prams.outputFile == null || prams.inputFile == null) {
            prams.error  = true;
        }
        return prams;
    }


}


