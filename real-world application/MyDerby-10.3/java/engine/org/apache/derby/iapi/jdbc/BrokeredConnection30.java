/*

   Derby - Class org.apache.derby.iapi.jdbc.BrokeredConnection30

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package org.apache.derby.iapi.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.derby.iapi.reference.JDBC30Translation;

/**
	Extends BrokeredConnection to provide the JDBC 3.0 connection methods.
 */
public class BrokeredConnection30 extends BrokeredConnection
{

	public	BrokeredConnection30(BrokeredConnectionControl control)
	{
		super(control);
	}

	public final Statement createStatement(int resultSetType,
                                 int resultSetConcurrency,
                                 int resultSetHoldability)
								 throws SQLException {
		try {
            resultSetHoldability = statementHoldabilityCheck(resultSetHoldability);
			return control.wrapStatement(getRealConnection().createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability));
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}
	public final CallableStatement prepareCall(String sql,
                                     int resultSetType,
                                     int resultSetConcurrency,
                                     int resultSetHoldability)
									 throws SQLException {
		try {
            resultSetHoldability = statementHoldabilityCheck(resultSetHoldability);
			return control.wrapStatement(
				getRealConnection().prepareCall(sql, resultSetType,
                        resultSetConcurrency, resultSetHoldability), sql);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final Savepoint setSavepoint()
		throws SQLException
	{
		try {
			control.checkSavepoint();
			return getRealConnection().setSavepoint();
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final Savepoint setSavepoint(String name)
		throws SQLException
	{
		try {
			control.checkSavepoint();
			return getRealConnection().setSavepoint(name);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final void rollback(Savepoint savepoint)
		throws SQLException
	{
		try {
			control.checkRollback();
			getRealConnection().rollback(savepoint);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final void releaseSavepoint(Savepoint savepoint)
		throws SQLException
	{
		try {
			getRealConnection().releaseSavepoint(savepoint);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}


	public final void setHoldability(int holdability)
		throws SQLException
	{
		try {
			holdability = control.checkHoldCursors(holdability, false);
			getRealConnection().setHoldability(holdability);
			stateHoldability = holdability;
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final PreparedStatement prepareStatement(
			String sql,
			int autoGeneratedKeys)
    throws SQLException
	{
		try {
			return control.wrapStatement(getRealConnection().prepareStatement(sql, autoGeneratedKeys), sql, new Integer(autoGeneratedKeys));
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final PreparedStatement prepareStatement(
			String sql,
			int[] columnIndexes)
    throws SQLException
	{
		try {
			return control.wrapStatement(getRealConnection().prepareStatement(sql, columnIndexes), sql, columnIndexes);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public final PreparedStatement prepareStatement(
			String sql,
			String[] columnNames)
    throws SQLException
	{
		try {
			return control.wrapStatement(getRealConnection().prepareStatement(sql, columnNames), sql, columnNames);
		}
		catch (SQLException se)
		{
			notifyException(se);
			throw se;
		}
	}

	public BrokeredPreparedStatement newBrokeredStatement(BrokeredStatementControl statementControl, String sql, Object generatedKeys) throws SQLException {
		return new BrokeredPreparedStatement30(statementControl, sql, generatedKeys);
	}
	public BrokeredCallableStatement newBrokeredStatement(BrokeredStatementControl statementControl, String sql) throws SQLException {
		return new BrokeredCallableStatement30(statementControl, sql);
	}

	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}


	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}