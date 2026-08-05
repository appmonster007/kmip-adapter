package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.util.UUID;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * Benchmark subject for {@link KeyWrappingSpecification}.
 */
public class KeyWrappingSpecificationBenchmarkSubject
    extends KmipBenchmarkSubject<KeyWrappingSpecification> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link KeyWrappingSpecificationBenchmarkSubject}.
   */
  public KeyWrappingSpecificationBenchmarkSubject() throws Exception {
    KeyWrappingSpecification subject = KeyWrappingSpecification
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
        .encryptionKeyInformation(EncryptionKeyInformation
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of(UUID
                .randomUUID()
                .toString()))
            .build())
        .build();
    initialize(subject, KeyWrappingSpecification.class);
  }

  @Override
  public String name() {
    return "KeyWrappingSpecification";
  }

}