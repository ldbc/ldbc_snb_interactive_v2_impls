package org.ldbcouncil.snb.impls.workloads.duckdb.operationhandlers;

import org.ldbcouncil.snb.driver.Operation;
import org.ldbcouncil.snb.driver.workloads.interactive.queries.LdbcNoResult;
import org.ldbcouncil.snb.impls.workloads.duckdb.DuckDbDbConnectionState;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.MultipleUpdateOperationHandler;

import java.util.List;

public abstract class DuckDbMultipleUpdateOperationHandler<TOperation extends Operation<LdbcNoResult>>
        extends DuckDbOperationHandler
        implements MultipleUpdateOperationHandler<TOperation, DuckDbDbConnectionState> {

    @Override
    public List<String> getQueryString(DuckDbDbConnectionState state, TOperation operation) {
        throw new IllegalStateException();
    }
}
