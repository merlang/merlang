package org.merlang.compilation.syntax.tree.declaration;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import org.merlang.compilation.syntax.tree.Producer;
import org.merlang.compilation.syntax.tree.Scope;
import org.merlang.compilation.syntax.tree.Visitor;
import org.merlang.compilation.syntax.tree.expression.Identifier;
import org.merlang.compilation.syntax.tree.typename.TypeName;

public final class ProcedureDeclaration implements Declaration {
  private Identifier name;
  private boolean exported;
  private ProcedureKind kind;
  private ImmutableCollection<ProcedureParameter> parameters;
  @Nullable private TypeName returnTypeName;
  private Scope innerScope;

  private ProcedureDeclaration(
    Identifier name,
    boolean exported,
    ProcedureKind kind,
    ImmutableCollection<ProcedureParameter> parameters,
    @Nullable TypeName returnTypeName,
    Scope innerScope
  ) {
    this.name = name;
    this.kind = kind;
    this.exported = exported;
    this.parameters = parameters;
    this.returnTypeName = returnTypeName;
    this.innerScope = innerScope;
  }

  @Override
  public Scope scope() {
    return this.name.scope();
  }

  public Scope innerScope() {
    return this.innerScope;
  }

  @Override
  public Identifier name() {
    return this.name;
  }

  public ProcedureKind kind() {
    return this.kind;
  }

  public ImmutableCollection<ProcedureParameter> parameters() {
    return this.parameters;
  }

  public Optional<TypeName> returnTypeName() {
    return Optional.ofNullable(this.returnTypeName);
  }

  public boolean hasReturnType() {
    return this.returnTypeName != null;
  }

  @Override
  public String descriptor() {
    String combinedParameters = combineParameterDescriptors();
    String baseDescriptor = String.format("%s(%s)", name, combinedParameters);
    return hasReturnType() ? baseDescriptor + returnTypeName.descriptor() : baseDescriptor;
  }

  private String combineParameterDescriptors() {
    Collection<String> parameterDescriptors = parameters.stream()
      .map(ProcedureParameter::descriptor)
      .collect(Collectors.toList());

    return Joiner.on(',').join(parameterDescriptors);
  }

  @Override
  public boolean isExported() {
    return this.exported;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acceptRecursive(Visitor visitor) {
    visitor.visit(this);
    this.name.acceptRecursive(visitor);
    for (ProcedureParameter parameter : this.parameters) {
      parameter.acceptRecursive(visitor);
    }
    if (hasReturnType()) {
      this.returnTypeName.acceptRecursive(visitor);
    }
  }

  @Override
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private Identifier name;
    private TypeName returnTypeName;
    private boolean exported;
    private ProcedureKind kind;
    private ImmutableList.Builder<ProcedureParameter> parameters;
    private Scope innerScope;

    private Builder() {
      this.parameters = ImmutableList.builder();
    }

    public Builder withName(Identifier name) {
      Objects.requireNonNull(name);
      this.name = name;
      return this;
    }

    public Builder withReturnTypeName(TypeName returnTypeName) {
      Objects.requireNonNull(returnTypeName);
      this.returnTypeName = returnTypeName;
      return this;
    }

    public Builder addParameter(ProcedureParameter parameter) {
      Objects.requireNonNull(parameter);
      this.parameters.add(parameter);
      return this;
    }

    public Builder exported() {
      this.exported = true;
      return this;
    }

    public Builder withKind(ProcedureKind kind) {
      Objects.requireNonNull(kind);
      this.kind = kind;
      return this;
    }

    public Builder withInnerScope(Scope scope) {
      Objects.requireNonNull(scope);
      this.innerScope = scope;
      return this;
    }

    public ProcedureDeclaration create() {
      Objects.requireNonNull(name);
      Objects.requireNonNull(kind);
      Objects.requireNonNull(innerScope);
      return new ProcedureDeclaration(
        name,
        exported,
        kind,
        parameters.build(),
        returnTypeName,
        innerScope
      );
    }
  }
}
