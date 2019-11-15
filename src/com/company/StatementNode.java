package com.company;

public class StatementNode implements INode {

    private AssignmentNode assignment;
    private StatementNode statment;
    private int nodeAtLevel;

    public StatementNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception{
        this.nodeAtLevel = nodeAtLevel;
        if (tokenizer.getCurrentLexeme().token() == Token.IDENT) {
            System.out.println("StementNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
            assignment = new AssignmentNode(tokenizer, nodeAtLevel + 1);
            statment = new StatementNode(tokenizer, nodeAtLevel + 1);
        }


    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++){
            builder.append("\t");
        }
        builder.append("StatmentNode\n");
        if(assignment != null){
            assignment.buildString(builder, tabs + 1);
        }
        if(statment != null){
            statment.buildString(builder,tabs + 1);
        }

    }
}
