package com.mawen.think.in.spring.expression.string;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/15
 */
public class StringExample {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();

		none(parser);
		concat(parser);
		bytes(parser);
		bytesLength(parser);
		toUpperCase(parser);
	}

	static void none(ExpressionParser parser) {
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		System.out.println(message);
	}

	static void concat(ExpressionParser parser) {
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = (String) exp.getValue();
		System.out.println(message);
	}

	static void bytes(ExpressionParser parser) {
		Expression exp = parser.parseExpression("'Hello World'.bytes");
		byte[] bytes = (byte[]) exp.getValue();
		System.out.println(new String(bytes));
	}

	static void bytesLength(ExpressionParser parser) {
		Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		Integer length = (Integer) exp.getValue();
		System.out.println(length);
	}

	static void toUpperCase(ExpressionParser parser) {
		Expression exp = parser.parseExpression("new String('Hello World').toUpperCase()");
		String message = exp.getValue(String.class);
		System.out.println(message);
	}
}
