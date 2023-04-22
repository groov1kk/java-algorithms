package com.github.groov1kk.extensions;

import java.lang.reflect.Parameter;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.util.Preconditions;

public class RandomArrayExtension implements ParameterResolver {

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.isAnnotated(RandomArray.class);
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    return getRandomArray(parameterContext.getParameter(), extensionContext);
  }

  private Object getRandomArray(Parameter parameter, ExtensionContext extensionContext) {
    Random random =
        extensionContext
            .getRoot()
            .getStore(ExtensionContext.Namespace.GLOBAL)
            .getOrComputeIfAbsent(Random.class);

    RandomArray randomArray = parameter.getAnnotation(RandomArray.class);
    checkRandomArray(randomArray);

    Class<?> type = parameter.getType();
    if (int[].class.equals(type)) {
      return intStream(random, randomArray).toArray();
    }

    if (Integer[].class.equals(type)) {
      return intStream(random, randomArray).boxed().toArray(Integer[]::new);
    }

    throw new ParameterResolutionException("No random generator implemented for " + type);
  }

  private static void checkRandomArray(RandomArray randomArray) {
    Preconditions.condition(randomArray.length() >= 0, "Length must be greater than 0");
    Preconditions.condition(
        randomArray.from() <= randomArray.to(),
        "Value 'From' must be the less than the value 'To'");
  }

  private static IntStream intStream(Random random, RandomArray randomArray) {
    IntStream ints = random.ints(randomArray.from(), randomArray.to()).limit(randomArray.length());
    return randomArray.distinct() ? ints.distinct() : ints;
  }
}
