package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

/**
 * Convert <code>String</code> to/from <code>SQLXML</code>.
 *
 * @author Iwao AVE!
 * @since 3.5.0
 */
public class SqlxmlTypeHandler extends BaseTypeHandler<String> {

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return sqlxmlToString(cs.getSQLXML(columnIndex));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return sqlxmlToString(rs.getSQLXML(columnIndex));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return sqlxmlToString(rs.getSQLXML(columnName));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
        throws SQLException {
        SQLXML sqlxml = ps.getConnection().createSQLXML();
        try {
            sqlxml.setString(parameter);
            ps.setSQLXML(i, sqlxml);
        } finally {
            sqlxml.free();
        }
    }

    protected String sqlxmlToString(SQLXML sqlxml) throws SQLException {
        if (sqlxml == null) {
            return null;
        }
        try {
            return sqlxml.getString();
        } finally {
            sqlxml.free();
        }
    }

}
