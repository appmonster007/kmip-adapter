package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;

/**
 * Benchmark subject for {@link ObjectGroupMember}.
 */
public class ObjectGroupMemberBenchmarkSubject extends KmipBenchmarkSubject<ObjectGroupMember> {

  /**
   * Constructs a new {@link ObjectGroupMemberBenchmarkSubject}.
   */
  public ObjectGroupMemberBenchmarkSubject() throws Exception {
    ObjectGroupMember objectGroupMember = ObjectGroupMember.Standard.GROUP_MEMBER_FRESH.inst();
    initialize(objectGroupMember, ObjectGroupMember.class);
  }

  @Override
  public String name() {
    return "ObjectGroupMember";
  }

}
