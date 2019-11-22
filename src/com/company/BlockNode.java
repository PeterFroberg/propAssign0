// Peter Fröberg, pefr7147@student.su.se
// Douglas Hammarstam, doha6991@student.su.se
package com.company;

public class BlockNode implements INode {

    private StatementNode statementNode;

    public BlockNode(Tokenizer tokenizer) throws Exception {

        if (tokenizer.getCurrentLexeme().token() != Token.LEFT_CURLY) {
            throw new ParserException("Wrong token found at begining av program { expected!");
        }
        tokenizer.moveNext();
        statementNode = new StatementNode(tokenizer);

        if (tokenizer.getCurrentLexeme().token() != Token.RIGHT_CURLY) {
            throw new ParserException("Wrong token found at end av program } expected!");
        }
        tokenizer.moveNext();
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        if (statementNode != null) {
            VarResult[] varArray = new VarResult[100];
            return statementNode.evaluate(varArray);
        }
        return null; //finalResult;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append("BlockNode\n");
        builder.append(Token.LEFT_CURLY + " {\n");

        if (statementNode != null) {
            statementNode.buildString(builder, tabs + 1);
        }

        builder.append(Token.RIGHT_CURLY + " }\n");
    }
}
