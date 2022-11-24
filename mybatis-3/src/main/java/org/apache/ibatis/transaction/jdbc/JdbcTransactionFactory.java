package org.apache.ibatis.transaction.jdbc;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * Creates {@link JdbcTransaction} instances.
 *
 * @author Clinton Begin
 * @see JdbcTransaction
 */
public class JdbcTransactionFactory implements TransactionFactory {

    private boolean skipSetAutoCommitOnClose;

    @Override
    public void setProperties(Properties props) {
        if (props == null) {
            return;
        }
        String value = props.getProperty("skipSetAutoCommitOnClose");
        if (value != null) {
            skipSetAutoCommitOnClose = Boolean.parseBoolean(value);
        }
    }

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(ds, level, autoCommit, skipSetAutoCommitOnClose);
    }
}
