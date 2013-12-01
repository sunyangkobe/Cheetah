/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package com.cheetah.core.shared.rpc;

import net.customware.gwt.dispatch.shared.Result;

import com.cheetah.core.shared.models.Person;

/**
 * LoginUserResult which will be served as Result in GWT RPC communication
 * corresponding to action {@link LoginAction}
 * 
 * @author Kobe Sun
 * 
 */
public class LoginResult implements Result {

	private static final long serialVersionUID = -8740775403377441876L;
	private Person user;

	public LoginResult(Person user) {
		this.user = user;
	}

	@SuppressWarnings("unused")
	private LoginResult() {
	}

	public Person getUser() {
		return user;
	}
}
