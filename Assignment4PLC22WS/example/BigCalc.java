package example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigCalc {
    public static void main(String[] args) {  
        try {
           final CharStream input = CharStreams.fromFileName(args[0]);
           final example.BigCalcLexer lexer = new example.BigCalcLexer(input);
           final CommonTokenStream tokens = new CommonTokenStream(lexer);
           final example.BigCalcParser parser = new example.BigCalcParser(tokens);
           final ParseTree tree = parser.expressionStatement();

           final example.BigCalcVisitor<BigDecimal> visitor = new BigCalcVisitorImpl();
           final BigDecimal result = visitor.visit(tree);

           if (result != null)
              System.out.println("result: " + result.setScale(10, RoundingMode.HALF_UP));
        }
        catch (ArrayIndexOutOfBoundsException e) {
      	   System.out.println("usage: usage: java BigCalc <file>");
        }
        catch (Exception e) {
           System.out.println(e);
        }
    }
}
