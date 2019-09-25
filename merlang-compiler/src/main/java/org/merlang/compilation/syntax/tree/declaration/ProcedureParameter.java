package org.merlang.compilation.syntax.tree.declaration;

import java.util.Objects;

import org.merlang.compilation.syntax.tree.Producer;
import org.merlang.compilation.syntax.tree.Scope;
import org.merlang.compilation.syntax.tree.Visitor;
import org.merlang.compilation.syntax.tree.expression.Identifier;
import org.merlang.compilation.syntax.tree.typename.TypeName;

public final class ProcedureParameter implements TypedDeclaration {
  private static final String NO_LABEL = "";

  private String label;
  private Identifier name;
  private TypeName typeName;

  private ProcedureParameter(String label, Identifier name, TypeName typeName) {
    this.name = name;
    this.label = label;
    this.typeName = typeName;
  }

  @Override
  public Scope scope() {
    return this.name.scope();
  }

  @Override
  public Identifier name() {
    return this.name;
  }

  @Override
  public TypeName typeName() {
    return this.typeName;
  }

  public String label() {
    return this.label;
  }

  public boolean isLabeled() {
    return !label.isEmpty();
  }

  @Override
  public String descriptor() {
    return isLabeled() ? labeledDescriptor() : unlabeledDescriptor();
  }

  @Override
  public boolean isExported() {
    return false;
  }

  private String labeledDescriptor() {
    return String.format("param_%s", name);
  }

  private String unlabeledDescriptor() {
    return String.format("param_%s_%s", name, label);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void acceptRecursive(Visitor visitor) {
    visitor.visit(this);
    this.typeName.acceptRecursive(visitor);
    this.name.acceptRecursive(visitor);
  }

  @Override
  public <OutputT> OutputT produce(Producer<?, OutputT> producer) {
    return producer.produce(this);
  }

  @Override
  public <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input) {
    return producer.produce(this, input);
  }

  public static ProcedureParameter create(Identifier name, TypeName typeName) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(typeName);
    return new ProcedureParameter(NO_LABEL, name, typeName);
  }

  public static ProcedureParameter labeled(String label, Identifier name, TypeName typeName) {
    Objects.requireNonNull(label);
    Objects.requireNonNull(name);
    Objects.requireNonNull(typeName);
    return new ProcedureParameter(label, name, typeName);
  }
}