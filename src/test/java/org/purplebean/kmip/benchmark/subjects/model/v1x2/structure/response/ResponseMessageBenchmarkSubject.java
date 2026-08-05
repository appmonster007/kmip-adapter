package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseBatchItem;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseMessage;

/**
 * Benchmark subject for {@link ResponseMessage}.
 */
public class ResponseMessageBenchmarkSubject extends KmipBenchmarkSubject<ResponseMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ResponseMessageBenchmarkSubject}.
   */
  public ResponseMessageBenchmarkSubject() throws Exception {
    KmipContext.setSpec(spec);
    ResponseMessage subject = ResponseMessage
        .builder()
        .responseHeader(ResponseHeader
            .builder()
            .protocolVersion(
                ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
            .timeStamp(TimeStamp.of(OffsetDateTime.now(ZoneOffset.UTC)))
            .batchCount(BatchCount.of(1))
            .build())
        .responseBatchItem(ResponseBatchItem
            .builder()
            .operation(Operation.of(Operation.Standard.CREATE))
            .resultStatus(ResultStatus.of(ResultStatus.Standard.SUCCESS))
            .build())
        .responseBatchItemError(null)
        .build();
    initialize(subject, ResponseMessage.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "ResponseMessage";
  }
}
