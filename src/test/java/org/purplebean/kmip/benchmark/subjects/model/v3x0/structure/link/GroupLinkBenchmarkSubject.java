package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.link.GroupLink;

public class GroupLinkBenchmarkSubject extends KmipBenchmarkSubject<GroupLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public GroupLinkBenchmarkSubject() throws Exception {
    GroupLink subject = GroupLink.of("test-id");
    initialize(subject, GroupLink.class);
  }

  @Override
  public String name() {
    return "GroupLink";
  }
}
