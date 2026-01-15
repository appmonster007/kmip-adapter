package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

public class LinkTypeBenchmarkSubject extends KmipBenchmarkSubject<LinkType> {

    public LinkTypeBenchmarkSubject() throws Exception {
        LinkType linkType = LinkType.Standard.CERTIFICATE_LINK.inst();
        initialize(linkType, LinkType.class);
    }

    @Override
    public String name() {
        return "LinkType";
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
