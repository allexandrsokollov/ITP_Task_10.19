package vsu.cs.sokolov.argsHeandler;

import java.util.Objects;

public class CmdParams {
    public boolean help;
    public boolean error;
    public String inputFile;
    public String outputFile;

    public CmdParams() {
    }

    public CmdParams(boolean help, boolean error) {
        this.help = help;
        this.error = error;
    }

    public CmdParams(boolean help, boolean error, String inputFile, String outputFile) {
        this.help = help;
        this.error = error;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CmdParams cmdParams)) return false;
        return help == cmdParams.help && error == cmdParams.error && Objects.equals(inputFile, cmdParams.inputFile) && Objects.equals(outputFile, cmdParams.outputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(help, error, inputFile, outputFile);
    }

    @Override
    public String toString() {
        return "CmdParams{" +
                "help=" + help +
                ", error=" + error +
                ", inputFile='" + inputFile + '\'' +
                ", outputFile='" + outputFile + '\'' +
                '}';
    }
}
