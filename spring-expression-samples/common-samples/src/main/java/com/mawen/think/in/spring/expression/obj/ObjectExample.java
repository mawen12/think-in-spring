package com.mawen.think.in.spring.expression.obj;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/16
 */
public class ObjectExample {

	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		User tesla = new User("Nikola Tesla", LocalDate.of(1856, 7, 9), "Serbian");

		parseName(parser, tesla);
		parseCondition(parser, tesla);
	}

	static void parseName(ExpressionParser parser, User tesla) {

		Expression exp = parser.parseExpression("name");
		String name = (String) exp.getValue(tesla);

		System.out.println(name);
	}

	static void parseCondition(ExpressionParser parser, User tesla) {
		Expression exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp.getValue(tesla, Boolean.class);

		System.out.println(result);
	}

	@Data
	@AllArgsConstructor
	public static class User {

		private String name;

		private LocalDate birthday;

		private String nationality;
	}
}
