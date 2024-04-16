package com.mawen.think.in.spring.expression.context;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/16
 */
public class EvaluationContextExample {

	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();

		SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		writeGeneric(parser, context);
		readGeneric(parser);
		readNullListOutOfIndex();
		readByCompilation();
	}

	static void writeGeneric(ExpressionParser parser, EvaluationContext context) {

		Expression exp = parser.parseExpression("booleanList[0]");

		Simple simple = new Simple();
		simple.booleanList.add(false);

		exp.setValue(context, simple, "true");

		Boolean b = simple.booleanList.get(0);

		System.out.println(b);
	}

	static void readGeneric(ExpressionParser parser) {

		Expression exp = parser.parseExpression("list[3]");

		Simple simple = new Simple();
		simple.list = Lists.newArrayList(null, null, null, null);

		String value = exp.getValue(simple, String.class);
		System.out.println(value);
	}

	static void readNullListOutOfIndex() {

		SpelParserConfiguration config = new SpelParserConfiguration(true, true);

		ExpressionParser parser = new SpelExpressionParser(config);

		Expression exp = parser.parseExpression("list[3]");

		Simple simple = new Simple();

		String value = exp.getValue(simple, String.class);
		System.out.println(value);
	}

	static void readByCompilation() {
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, Thread.currentThread().getContextClassLoader());

		SpelExpressionParser parser = new SpelExpressionParser(config);

		Expression expr = parser.parseExpression("list");

		Object value = expr.getValue(new Simple());
		System.out.println(value);
	}

	public static class Simple {
		public List<Boolean> booleanList = new ArrayList<>();

		public List<String> list;
	}

}
