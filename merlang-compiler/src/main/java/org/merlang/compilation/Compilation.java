package org.merlang.compilation;

import org.merlang.compilation.diagnostic.DiagnosticReport;

public final class Compilation {
  private Source source;

  Compilation(Source source) {
    this.source = source;
  }

  public Output compile() throws CompilationException {
    throw CompilationException.withMessage("Not Implemented");
  }

  public DiagnosticReport createDiagnosticReport() {
    return null;
  }
}