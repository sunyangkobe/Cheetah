package com.cheetah.core.server.db;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import com.cheetah.core.server.db.mappers.PersonMapper;
import com.cheetah.core.shared.models.Person;

public class QueryTesterMyBatis {

	/**
	 * Debug/Run me as a java application to try out the query before you build
	 * the client connection Right Click on me > Hit Debug As > Java Application
	 * 
	 * Make sure you have a JDBC connector in your JVM classpath, or goto Build
	 * Path and add external jar. You will need to change the JDBC connector
	 * path in the build path
	 * 
	 */
	public static void main(String[] args) {

		// This will test the query with out to many classes in the way

		String username = "marcel";
		String password = "marcel";
		int uid = 1;

		// Construct SQL query parameters.
		Person person;

		// Start SQL.
		SqlSession session = DBConst.SQL.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);

		try {
			// Test 1.
			person = new Person(username, password);
			person = mapper.searchPerson(person);
			System.out.println("searchPerson");
			System.out.println("uid = " + person.uid);
			System.out.println("email = " + person.email);
			System.out.println("username = " + person.username);
			System.out.println("password = " + person.password);
			System.out.println("lname = " + person.lname);
			System.out.println("fname = " + person.fname);
			System.out.println("----------------------------");
			if (BCrypt.checkpw(password, person.password)) {
				System.out.println("The Password matches!!");
			} else {
				System.out.println("Password wrong!!");
			}
			System.out.println();

			// Test 2.
			person = mapper.getPersonByUid(uid);
			System.out.println("getPersonByUid");
			System.out.println("uid = " + person.uid);
			System.out.println("email = " + person.email);
			System.out.println("username = " + person.username);
			System.out.println("password = " + person.password);
			System.out.println("lname = " + person.lname);
			System.out.println("fname = " + person.fname);
			System.out.println();
		} finally {
			session.close();
		}

	}
}
