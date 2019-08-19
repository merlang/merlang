package org.merlang.compilation.diagnostic;

public class DiagnosticException extends Exception {
  private DiagnosticEntry entry;

  protected DiagnosticException(DiagnosticEntry entry) {
    this.entry = entry;
  }

  protected DiagnosticException(DiagnosticEntry entry, String message) {
    super(message);
    this.entry = entry;
  }

  public DiagnosticEntry diagnosticEntry() {
    return this.entry;
  }
}
