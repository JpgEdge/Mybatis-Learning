package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Clinton Begin
 */
public class SqlTimestampTypeHandler extends BaseTypeHandler<Timestamp> {

    @Override
    public Timestamp getNullableResult(CallableStatement cs, int columnIndex)
        throws SQLException {
        return cs.getTimestamp(columnIndex);
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, int columnIndex)
        throws SQLException {
        return rs.getTimestamp(columnIndex);
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, String columnName)
        throws SQLException {
        return rs.getTimestamp(columnName);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Timestamp parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setTimestamp(i, parameter);
    }
}
