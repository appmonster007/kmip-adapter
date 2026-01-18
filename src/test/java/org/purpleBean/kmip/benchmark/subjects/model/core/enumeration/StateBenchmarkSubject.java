package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.State;

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
