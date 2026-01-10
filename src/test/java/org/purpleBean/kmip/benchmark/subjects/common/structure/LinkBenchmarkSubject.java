package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.common.structure.Link;

public class LinkBenchmarkSubject extends KmipBenchmarkSubject<Link> {

    public LinkBenchmarkSubject() throws Exception {
        Link link = Link.builder()
                .linkType(new LinkType(LinkType.Standard.CERTIFICATE_LINK))
                .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
                .build();
        initialize(link, Link.class);
    }

    @Override
    public String name() {
        return "Link";
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