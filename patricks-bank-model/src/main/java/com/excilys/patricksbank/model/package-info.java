@TypeDefs(value = { @TypeDef(name ="jodaDateTime", defaultForType=DateTime.class, typeClass=PersistentDateTime.class) })
package com.excilys.patricksbank.model;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;
