package org.ldbcouncil.snb.impls.workloads.umbra.operationhandlers;

import org.ldbcouncil.snb.driver.DbException;
import org.ldbcouncil.snb.driver.Operation;
import org.ldbcouncil.snb.driver.ResultReporter;
import org.ldbcouncil.snb.impls.workloads.operationhandlers.SingletonOperationHandler;
import org.ldbcouncil.snb.impls.workloads.umbra.UmbraDbConnectionState;

import java.sql.*;

public abstract class UmbraSingletonOperationHandler<TOperation extends Operation<TOperationResult>, TOperationResult>
        extends UmbraOperationHandler
        implements SingletonOperationHandler<TOperationResult, TOperation, UmbraDbConnectionState> {

    @Override
    public void executeOperation(TOperation operation, UmbraDbConnectionState state,
                                 ResultReporter resultReporter) throws DbException {
        try {
            TOperationResult tuple = null;
            Connection conn = state.getConnection();
            int resultCount = 0;

            String queryString = getQueryString(state, operation);
            replaceParameterNamesWithQuestionMarks(operation, queryString);
            final PreparedStatement stmt = prepareAndSetParametersInPreparedStatement(operation, queryString, conn);
            state.logQuery(operation.getClass().getSimpleName(), queryString);

            try {
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    resultCount++;

                    tuple = convertSingleResult(result);
                    if (state.isPrintResults())
                        System.out.println(tuple.toString());
                }
            }
            catch (Exception e) {
                throw new DbException(e);
            }
            finally {
                stmt.close();
                conn.close();
            }
            resultReporter.report(resultCount, tuple, operation);
        }
        catch (SQLException e){
            throw new DbException(e);
        }
    }

    public abstract TOperationResult convertSingleResult(ResultSet result) throws SQLException;
}