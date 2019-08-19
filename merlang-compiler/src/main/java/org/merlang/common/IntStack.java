package org.merlang.common;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public final class IntStack {
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  private int[] elements;

  private IntStack(int[] elements) {
    this.elements = elements;
  }

  public void push(int element) {}

  @CanIgnoreReturnValue
  public int pop() {
    return 0;
  }

  public int peek() {
    return 0;
  }

  public IntStack copy() {
    return new IntStack(elements.clone());
  }

  public static IntStack withInitialCapacity(int capacity) {
    Preconditions.checkArgument(capacity >= 0, "capacity is negative");
    return new IntStack(new int[capacity]);
  }

  public static IntStack empty() {
    return withInitialCapacity(DEFAULT_INITIAL_CAPACITY);
  }

  public static IntStack copyOf(IntStack stack) {
    Preconditions.checkNotNull(stack);
    return stack.copy();
  }
}
