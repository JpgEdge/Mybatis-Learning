package org.apache.ibatis.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * Creates {@link Transaction} instances.
 *
 * @author Clinton Begin
 */
public interface TransactionFactory {

    /**
     * Sets transaction factory custom properties.
     *
     * @param props the new properties
     */
    default void setProperties(Properties props) {
        // NOP
    }

    /**
     * Creates a {@link Transaction} out of an existing connection.
     *
     * @param conn Existing database connection
     * @return Transaction
     * @since 3.1.0
     */
    Transaction newTransaction(Connection conn);

    /**
     * Creates a {@link Transaction} out of a datasource.
     *
     * @param dataSource DataSource to take the connection from
     * @param level      Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     * @since 3.1.0
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
