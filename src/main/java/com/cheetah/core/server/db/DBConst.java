package com.cheetah.core.server.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Holds DB constants and initialization parameters.
 * 
 * @author Marcel
 */
public class DBConst {

	/**
	 * SQL mapper from MyBatis.
	 */
	public static final SqlSessionFactory SQL;

	static {
		try {
			String resource = "com/cheetah/core/server/db/sqlconfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SQL = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// If you get an error at this point, it doesn't matter what it
			// was. It is going to be unrecoverable and we will want the app
			// to blow up hard so we are aware of the problem. You should
			// always log such errors and re-throw them in such a way that
			// you can be made immediately aware of the problem.
			throw new RuntimeException("Error initializing SqlSessionFactory",
					e);
		}
	}

}
