package org.merlang.compilation.grammar;

import com.google.common.base.Preconditions;
import org.merlang.compilation.SourceRange;
import org.merlang.compilation.diagnostic.DiagnosticEntry;
import org.merlang.compilation.diagnostic.DiagnosticException;

public final class GrammarException extends DiagnosticException {
  private SourceRange invalidRange;
  private String sourceCapture;

  private GrammarException(
    String message,
    DiagnosticEntry entry,
    SourceRange invalidRange,
    String sourceCapture
  ) {
    super(entry, message);
    this.invalidRange = invalidRange;
    this.sourceCapture = sourceCapture;
  }

  public SourceRange invalidRange() {
    return this.invalidRange;
  }

  public String sourceCapture() {
    return this.sourceCapture;
  }

  public static final class Builder {
    private String message;
    private DiagnosticEntry entry;
    private SourceRange invalidRange;
    private String sourceCapture;

    private Builder() {}

    public Builder withMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder withDiagnosticEntry(DiagnosticEntry entry) {
      this.entry = entry;
      return this;
    }

    public Builder withInvalidRange(SourceRange invalidRange) {
      this.invalidRange  = invalidRange;
      return this;
    }

    public Builder withSourceCapture(String capture) {
      this.sourceCapture = capture;
      return this;
    }

    public GrammarException create() {
      Preconditions.checkNotNull(message);
      Preconditions.checkNotNull(entry);
      Preconditions.checkNotNull(invalidRange);
      Preconditions.checkNotNull(sourceCapture);
      return new GrammarException(
        message, entry, invalidRange, sourceCapture
      );
    }

  }
}
