package org.merlang.compilation.syntax.tree;

public interface Node {
  void accept(Visitor visitor);
  void acceptRecursive(Visitor visitor);
  <InputT, OutputT> OutputT produce(Producer<InputT, OutputT> producer, InputT input);
  <OutputT> OutputT produce(Producer<?, OutputT> producer);
}
