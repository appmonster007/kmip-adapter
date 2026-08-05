package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.LinkType;

/**
 * Benchmark subject for {@link LinkType}.
 */
public class LinkTypeBenchmarkSubject extends KmipBenchmarkSubject<LinkType> {

  /**
   * Constructs a new {@link LinkTypeBenchmarkSubject}.
   */
  public LinkTypeBenchmarkSubject() throws Exception {
    LinkType linkType = LinkType.Standard.CERTIFICATE_LINK.inst();
    initialize(linkType, LinkType.class);
  }

  @Override
  public String name() {
    return "LinkType";
  }

}
