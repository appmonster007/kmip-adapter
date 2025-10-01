package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeBenchmarkSubject extends KmipBenchmarkSubject<TicketType> {

    public TicketTypeBenchmarkSubject() throws Exception {
        TicketType ticketType = new TicketType(TicketType.Standard.LOGIN);
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
