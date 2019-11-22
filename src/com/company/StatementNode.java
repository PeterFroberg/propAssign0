// Peter Fröberg, pefr7147@student.su.se
// Douglas Hammarstam, doha6991@student.su.se
package com.company;

public class StatementNode implements INode {

    private AssignmentNode assignment;
    private StatementNode statment;

    public StatementNode(Tokenizer tokenizer) throws Exception {
        if (tokenizer.getCurrentLexeme().token() == Token.IDENT) {
            System.out.println("StementNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: ");
            assignment = new AssignmentNode(tokenizer);
            statment = new StatementNode(tokenizer);
        }else if(tokenizer.getNextLexeme().token() != Token.EOF){
            throw new ParserException("Syntax error, Identifier expected, got: " + tokenizer.getCurrentLexeme().token().toString());
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        if (assignment != null) {

            VarResult currentResult = (VarResult) assignment.evaluate(args);

            stringBuilder.append(currentResult.getId() + " = " + String.format("%.01f\n", currentResult.getValue()));

            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    VarResult curRes = (VarResult) args[i];
                    if (curRes.getId().equals(currentResult.getId())) {
                        args[i] = currentResult;
                        break;
                    }
                } else {
                    args[i] = currentResult;
                    break;
                }
            }
            stringBuilder.append(statment.evaluate(args));
        }


        return stringBuilder;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append(insertTabs(tabs) + "StatmentNode\n");

        if (assignment != null) {
            assignment.buildString(builder, tabs + 1);
        }
        if (statment != null) {
            statment.buildString(builder, tabs + 1);
        }

    }

    private String insertTabs(int tabs) {
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }
}
