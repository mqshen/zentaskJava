package org.goldratio.core.hibernate;

import java.io.Serializable;

import org.hibernate.AssertionFailure;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.internal.util.StringHelper;

/** 
 * ClassName: NamingStrategy <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 3:21:44 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class ZenTaskNamingStrategy implements NamingStrategy, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5225075197292411846L;
	private static final String PREFIX = "";

	public String classToTableName(String className) {
		return PREFIX + StringHelper.unqualify( className );
	}

	public String propertyToColumnName(String propertyName) {
		return PREFIX + StringHelper.unqualify( propertyName );
	}

	public String tableName(String tableName) {
		return PREFIX + tableName;
	}

	public String columnName(String columnName) {
		return PREFIX + columnName;
	}

	public String collectionTableName(
			String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable,
			String propertyName
	) {
		return tableName(
				new StringBuilder( ownerEntityTable ).append( "_" )
						.append(
								associatedEntityTable != null ?
										associatedEntityTable :
										StringHelper.unqualify( propertyName )
						).toString()
		);
	}

	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return columnName( joinedColumn );
	}

	public String foreignKeyColumnName(
			String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName
	) {
		String header = propertyName != null ? StringHelper.unqualify( propertyName ) : propertyTableName;
		if ( header == null ) throw new AssertionFailure( "NamingStrategy not properly filled" );
		return columnName( header + "_id");
	}

	public String logicalColumnName(String columnName, String propertyName) {
		return PREFIX + (StringHelper.isNotEmpty( columnName ) ? columnName : StringHelper.unqualify( propertyName ));
	}

	public String logicalCollectionTableName(
			String tableName,
			String ownerEntityTable, String associatedEntityTable, String propertyName
	) {
		if ( tableName != null ) {
			return PREFIX + tableName;
		}
		else {
			//use of a stringbuffer to workaround a JDK bug
			return new StringBuffer(PREFIX + ownerEntityTable ).append( "_" )
					.append(
							associatedEntityTable != null ?
									associatedEntityTable :
									StringHelper.unqualify( propertyName )
					).toString();
		}
	}

	public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
		return PREFIX + (StringHelper.isNotEmpty( columnName ) ?
				columnName :
				StringHelper.unqualify( propertyName ) + "_" + referencedColumn);
	}
}