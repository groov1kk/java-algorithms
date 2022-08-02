package com.github.groov1kk.matchers;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsSatisfy<T> extends TypeSafeDiagnosingMatcher<T> {

  private final Predicate<T> condition;
  private final String conditionDescription;

  public IsSatisfy(Predicate<T> condition, @Nullable String conditionDescription) {
    this.condition = requireNonNull(condition);
    this.conditionDescription = requireNonNullElse(conditionDescription, "condition");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("satisfying ").appendText(conditionDescription);
  }

  @Override
  protected boolean matchesSafely(T item, Description mismatchDescription) {
    if (!condition.test(item)) {
      mismatchDescription.appendText(conditionDescription).appendText(" was not satisfied");
      return false;
    }
    return true;
  }

  /**
   * Creates a matcher that matches when the given predicate returns {@code true}.
   *
   * @param predicate predicate to verify
   * @return predicate matcher
   * @param <T> the type of the input to the predicate
   */
  public static <T> Matcher<T> satisfy(Predicate<T> predicate) {
    return satisfy(predicate, null);
  }

  /**
   * Creates a matcher that matches when the given predicate returns {@code true}.
   *
   * @param predicate predicate to verify
   * @param conditionDescription predicate description
   * @return predicate matcher
   * @param <T> the type of the input to the predicate
   */
  public static <T> Matcher<T> satisfy(
      Predicate<T> predicate, @Nullable String conditionDescription) {
    return new IsSatisfy<>(predicate, conditionDescription);
  }
}
