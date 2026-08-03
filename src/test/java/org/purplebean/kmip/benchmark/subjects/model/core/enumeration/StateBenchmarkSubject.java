package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.State;

public class StateBenchmarkSubject extends KmipBenchmarkSubject<State> {

  public StateBenchmarkSubject() throws Exception {
    State state = State.Standard.ACTIVE.inst();
    initialize(state, State.class);
  }

  @Override
  public String name() {
    return "State";
  }

}
