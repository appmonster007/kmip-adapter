package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.VendorIdentification;

/**
 * Benchmark subject for {@link MessageExtension}.
 */
public class MessageExtensionBenchmarkSubject extends KmipBenchmarkSubject<MessageExtension> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link MessageExtensionBenchmarkSubject}.
   */
  public MessageExtensionBenchmarkSubject() throws Exception {
    MessageExtension subject = MessageExtension
        .builder()
        .vendorIdentification(VendorIdentification.of("test-vendor"))
        .build();
    initialize(subject, MessageExtension.class);
  }

  @Override
  public String name() {
    return "MessageExtension";
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