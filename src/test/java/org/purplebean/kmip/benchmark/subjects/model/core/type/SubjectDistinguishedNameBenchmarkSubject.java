package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * Benchmark subject for {@link SubjectDistinguishedName}.
 */
public class SubjectDistinguishedNameBenchmarkSubject
    extends KmipBenchmarkSubject<SubjectDistinguishedName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link SubjectDistinguishedNameBenchmarkSubject}.
   */
  public SubjectDistinguishedNameBenchmarkSubject() throws Exception {
    SubjectDistinguishedName subjectDistinguishedName =
        SubjectDistinguishedName.of(new byte[] {0x01, 0x02, 0x03});
    initialize(subjectDistinguishedName, SubjectDistinguishedName.class);
  }

  @Override
  public String name() {
    return "SubjectDistinguishedName";
  }

}