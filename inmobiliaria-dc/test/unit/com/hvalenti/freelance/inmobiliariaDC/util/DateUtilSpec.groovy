package com.hvalenti.freelance.inmobiliariaDC.util

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin

import org.joda.time.DateTime
import org.joda.time.Months

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class DateUtilSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
	
	void "add days to date"(daysToAdd, expected) {
		given:
		DateTime dt
		
		when:
		dt = new DateTime("2015-01-01")
		dt = dt.plusDays(daysToAdd)
		
		then:
		dt.getDayOfMonth() == expected
		
		where:
		daysToAdd << [1,5,30,31,40,-1,-31]
		expected << [2,6,31,1,10,31,1]
	}

    void "create date from string"() {
		given:
		DateTime dt
		
		when:
		dt = new DateTime("2015-01-01")
		
		then:
		dt.getMonthOfYear() == 1
		dt.getDayOfMonth() == 1
		dt.getYear() == 2015
    }
	
	void "calculate month difference"(fromString, toString, expected) {
		given:
		DateTime from = new DateTime(fromString)
		DateTime to = new DateTime(toString)
		
		when:
		Months monthsBetween = Months.monthsBetween(from, to)
		
		then:
		monthsBetween.getMonths() == expected
		
		where:
		fromString <<	["2015-01-01", "2015-01-01", "2015-01-01", 
			"2015-01-01", "2015-01-01", "2015-01-10", 
			"2015-01-10", "2015-01-10"]
		toString <<		["2015-01-01", "2015-02-01", "2016-01-01", 
			"2016-02-01", "2015-01-05", "2015-02-20", 
			"2015-02-11", "2015-02-09"]
		expected <<		[0, 1, 12, 13, 0, 1, 1, 0]
	}
	
	void "month difference"(fromString, toString, expected) {
		given:
		Date from
		Date to
		
		when:
		from = new DateTime(fromString).toDate()
		to = new DateTime(toString).toDate()
		
		then:
		DateUtil.monthDifference(from, to) == expected
		
		where:
		fromString <<	["2015-01-01", "2015-01-01", "2015-01-01",
			"2015-01-01", "2015-01-01", "2015-01-10",
			"2015-01-10", "2015-01-10"]
		toString <<		["2015-01-01", "2015-02-01", "2016-01-01",
			"2016-02-01", "2015-01-05", "2015-02-20",
			"2015-02-11", "2015-02-09"]
		expected <<		[0, 1, 12, 13, 0, 1, 1, 0]
	}
	
	void "relative month difference"(fromString, toString, expected) {
		given:
		Date from
		Date to
		
		when:
		from = new DateTime(fromString).toDate()
		to = new DateTime(toString).toDate()
		
		then:
		DateUtil.relativeMonthDifference(from, to) == expected
		
		where:
		fromString <<	["2015-01-01", "2015-01-01", "2015-01-01",
			"2015-01-01", "2015-01-01", "2015-01-10",
			"2015-01-10", "2015-01-10", "2015-01-31"]
		toString <<		["2015-01-01", "2015-02-01", "2016-01-01",
			"2016-02-01", "2015-01-05", "2015-02-20",
			"2015-02-11", "2015-02-09", "2015-02-01"]
		expected <<		[0, 1, 12, 13, 0, 1, 1, 1, 1]
	}
		
	void "get month from Date"(fromString, expected) {
		given:
		Date from
		
		when:
		from = new DateTime(fromString).toDate()
		
		then:
		DateUtil.getMonthOfYear(from) == expected
		
		where:
		fromString <<	["2015-01-01", "1989-08-22", "1991-12-26"]
		expected <<		[1, 8, 12]
	}
	
	void "get maximum month from Date "(fromString, expected) {
		given:
		Date from
		
		when:
		from = new DateTime(fromString).toDate()
		
		then:
		DateUtil.maxDayOfMonth(from) == expected
		
		where:
		fromString <<	["2015-01-01", "2015-04-01", "2015-02-01", "2016-02-01"]
		expected <<		[31, 30, 28, 29]
	}
	
	void "get Date from numbers "() {
		given:
		DateTime dt = new DateTime("2015-08-22")
		Date date
		
		when:
		date = DateUtil.dateFromNumbers(2015, 8, 22)
		
		then:
		dt.toDate().equals(date)
	}
	
	void "format date as argentinan date"() {
		given:
		String dateString
		Date cumple = new DateTime("1989-08-22").toDate()
		
		when:
		dateString = DateUtil.formatDate(cumple)
		
		then:
		"22/08/1989".equals(dateString)
	}
}
