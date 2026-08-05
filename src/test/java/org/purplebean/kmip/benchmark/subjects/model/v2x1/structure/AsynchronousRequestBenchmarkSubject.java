package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousRequest;

/**
 * Benchmark subject for {@link AsynchronousRequest}.
 */
public class AsynchronousRequestBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousRequest> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AsynchronousRequestBenchmarkSubject}.
   */
  public AsynchronousRequestBenchmarkSubject() throws Exception {
    AsynchronousRequest subject =
        AsynchronousRequest.of(AsynchronousCorrelationValue.of(new byte[] {0x01}),
            Operation.Standard.CREATE.inst(), org.purplebean.kmip.model.v2x1.type.SubmissionDate.of(
                java.time.OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, java.time.ZoneOffset.UTC)),
            ProcessingStage.Standard.SUBMITTED.inst());
    initialize(subject, AsynchronousRequest.class);
  }

  @Override
  public String name() {
    return "AsynchronousRequest";
  }
}