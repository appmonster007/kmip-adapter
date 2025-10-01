package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.State;

public class StateBenchmarkSubject extends KmipBenchmarkSubject<State> {

    public StateBenchmarkSubject() throws Exception {
        State state = new State(State.Standard.ACTIVE);
        initialize(state, State.class);
    }

    @Override
    public String name() {
        return "State";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
