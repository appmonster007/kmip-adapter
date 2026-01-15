package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.structure.Link;

public class LinkBenchmarkSubject extends KmipBenchmarkSubject<Link> {

    public LinkBenchmarkSubject() throws Exception {
        Link link = Link.builder()
                .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
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