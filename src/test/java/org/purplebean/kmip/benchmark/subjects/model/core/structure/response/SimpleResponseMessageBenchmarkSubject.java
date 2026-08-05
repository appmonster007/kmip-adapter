package org.purplebean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseMessage;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;

/**
 * Benchmark subject for {@link SimpleResponseMessage}.
 */
public class SimpleResponseMessageBenchmarkSubject
    extends KmipBenchmarkSubject<SimpleResponseMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SimpleResponseMessageBenchmarkSubject}.
   */
  public SimpleResponseMessageBenchmarkSubject() throws Exception {
    SimpleResponseMessage subject = SimpleResponseMessage
        .builder()
        .responseHeader(SimpleResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
            .build())
        .responseBatchItem(SimpleResponseBatchItem
            .builder()
            .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
            .responsePayloadStructure(SimpleResponsePayload
                .builder()
                .build())
            .build())
        .responseBatchItemError(null)
        .build();
    initialize(subject, SimpleResponseMessage.class);
  }

  @Override
  public String name() {
    return "SimpleResponseMessage";
  }
}
