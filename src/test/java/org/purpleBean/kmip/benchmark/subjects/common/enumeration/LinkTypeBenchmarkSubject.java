package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeBenchmarkSubject extends KmipBenchmarkSubject<LinkType> {

    public LinkTypeBenchmarkSubject() throws Exception {
        LinkType linkType = new LinkType(LinkType.Standard.CERTIFICATE_LINK);
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
