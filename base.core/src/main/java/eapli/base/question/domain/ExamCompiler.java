package eapli.base.question.domain;

import eapli.base.question.domain.ANTLRExam.ExamANTLRLexer;
import eapli.base.question.domain.ANTLRExam.ExamANTLRParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ExamCompiler {

    public static void main(String[] args) throws IOException {
        String path = "docs/ExamSample1.txt";
        ExamANTLRLexer lexer = new ExamANTLRLexer(CharStreams.fromFileName(path));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExamANTLRParser parser = new ExamANTLRParser(tokens);
        ParseTree tree = parser.prog(); // parse

        ExamVisitor eval = new ExamVisitor();
        eval.visit(tree);
    }


}
