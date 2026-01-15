package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeBenchmarkSubject extends KmipBenchmarkSubject<TicketType> {

    public TicketTypeBenchmarkSubject() throws Exception {
        TicketType ticketType = TicketType.Standard.LOGIN.inst();
        initialize(ticketType, TicketType.class);
    }

    @Override
    public String name() {
        return "TicketType";
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
