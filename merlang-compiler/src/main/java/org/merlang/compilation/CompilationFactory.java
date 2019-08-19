package org.merlang.compilation;

public final class CompilationFactory {
  private CompilationFactory() {}

  public Compilation createCompilation(Source source) {
    return new Compilation();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private Builder() {}

    public CompilationFactory create() {
      return new CompilationFactory();
    }
  }
}
