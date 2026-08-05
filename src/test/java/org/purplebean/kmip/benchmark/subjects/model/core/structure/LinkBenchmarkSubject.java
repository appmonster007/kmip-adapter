package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.model.core.structure.Link;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

/**
 * Benchmark subject for {@link Link}.
 */
public class LinkBenchmarkSubject extends KmipBenchmarkSubject<Link> {

  /**
   * Constructs a new {@link LinkBenchmarkSubject}.
   */
  public LinkBenchmarkSubject() throws Exception {
    Link link = Link
        .builder()
        .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
        .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
        .build();
    initialize(link, Link.class);
  }

  @Override
  public String name() {
    return "Link";
  }

}