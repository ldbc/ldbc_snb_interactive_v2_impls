package org.ldbcouncil.snb.impls.workloads.duckdb;

import org.duckdb.DuckDBConnection;
import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.impls.workloads.BaseDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.QueryStore;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DuckDbDbConnectionState<TDbQueryStore extends QueryStore> extends BaseDbConnectionState<TDbQueryStore> {

    public DuckDbDbConnectionState(Map<String, String> properties, TDbQueryStore store) throws ClassNotFoundException {
        super(properties, store);
    }

    public DuckDBConnection getConnection() throws DbException {
        try {
            return (DuckDBConnection) DriverManager.getConnection("jdbc:duckdb:scratch/ldbc.duckdb");
            //TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT+0"));
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void close() {
        //
    }

}