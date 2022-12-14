package org.apache.ibatis.scripting.xmltags;

/**
 * @author Frank D. Martinez [mnesarco]
 */
public class VarDeclSqlNode implements SqlNode {

    private final String name;

    private final String expression;

    public VarDeclSqlNode(String name, String exp) {
        this.name = name;
        this.expression = exp;
    }

    @Override
    public boolean apply(DynamicContext context) {
        final Object value = OgnlCache.getValue(expression, context.getBindings());
        context.bind(name, value);
        return true;
    }

}
