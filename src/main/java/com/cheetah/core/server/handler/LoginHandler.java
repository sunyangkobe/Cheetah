package com.cheetah.core.server.handler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

import org.apache.commons.logging.Log;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import com.cheetah.core.server.db.DBConst;
import com.cheetah.core.server.db.mappers.PersonMapper;
import com.cheetah.core.shared.models.Person;
import com.cheetah.core.shared.rpc.LoginAction;
import com.cheetah.core.shared.rpc.LoginResult;
import com.google.inject.Inject;

/**
 * Handler for login a user via username and password, corresponding to action
 * {@link LoginAction} and result {@link LoginResult}
 * 
 * @author Kobe Sun
 */
public class LoginHandler implements ActionHandler<LoginAction, LoginResult> {

	private final Log logger;

	@Inject
	public LoginHandler(final Log logger) {
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware
	 * .gwt.dispatch.shared.Action,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public LoginResult execute(LoginAction action, ExecutionContext context)
			throws ActionException {

		try {
			// Start SQL.
			SqlSession session = DBConst.SQL.openSession();
			PersonMapper mapper = session.getMapper(PersonMapper.class);

			Person user = action.getUser();
			user = mapper.searchPerson(user);
			if (user == null) {
				// TODO: send out an event for this condition
				throw new ActionException("No such user");
			}
			if (BCrypt.checkpw(action.getPassword(), user.getPassword())) {
				logger.info("[LoginHandler]: The Password matches!!");
			} else {
				logger.info("[LoginHandler]: Password wrong!!");
			}

			session.close();
			return new LoginResult(user);

		} catch (Exception cause) {
			logger.error("[LoginHandler]: Unable to send message", cause);
			throw new ActionException(cause);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.customware.gwt.dispatch.server.ActionHandler#rollback(net.customware
	 * .gwt.dispatch.shared.Action, net.customware.gwt.dispatch.shared.Result,
	 * net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	public void rollback(LoginAction user, LoginResult result,
			ExecutionContext context) throws ActionException {
		// Nothing todo here
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	public Class<LoginAction> getActionType() {
		return LoginAction.class;
	}
}
