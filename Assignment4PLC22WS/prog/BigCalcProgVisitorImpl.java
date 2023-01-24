package prog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class BigCalcProgVisitorImpl extends BigCalcProgBaseVisitor<BigDecimal> {
    private final Map<String, BigDecimal> varMap = new HashMap<>();

    @Override
    public BigDecimal visitProgram(BigCalcProgParser.ProgramContext ctx) {
        BigDecimal result = BigDecimal.ZERO;
        for(BigCalcProgParser.StatementContext statement : ctx.statement()){
            result = visit(statement);
        }
        return result;
    }

    @Override
    public BigDecimal visitStatement(BigCalcProgParser.StatementContext ctx) {
        return visit(ctx.expressionStatement());
    }

    @Override
    public BigDecimal visitAssign(BigCalcProgParser.AssignContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public BigDecimal visitExpr(BigCalcProgParser.ExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public BigDecimal visitAssignment(BigCalcProgParser.AssignmentContext ctx) {
        BigDecimal result = visit(ctx.expression());
        varMap.put(ctx.ID().getText(), result);
        return result;
    }

    @Override
    public BigDecimal visitParen(BigCalcProgParser.ParenContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public BigDecimal visitMulDiv(BigCalcProgParser.MulDivContext ctx) {
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        if (ctx.op.getText().equals("*")) {
            return left.multiply(right);
        } else {
            return left.divide(right, 10, RoundingMode.HALF_UP);
        }
    }

    @Override
    public BigDecimal visitAddSub(BigCalcProgParser.AddSubContext ctx) {
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        if (ctx.op.getText().equals("+")) {
            return left.add(right);
        } else {
            return left.subtract(right);
        }
    }

    @Override
    public BigDecimal visitId(BigCalcProgParser.IdContext ctx) {
        return varMap.getOrDefault(ctx.ID().getText(), BigDecimal.ZERO);
    }

    @Override
    public BigDecimal visitNum(BigCalcProgParser.NumContext ctx) {
        return new BigDecimal(ctx.Number().getText());
    }
}