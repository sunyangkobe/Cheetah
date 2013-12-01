package com.cheetah.core.server.db.mappers;

import com.cheetah.core.shared.models.Person;

public interface PersonMapper {
	Person searchPerson(Person person);

	Person getPersonByUid(int uid);
}
