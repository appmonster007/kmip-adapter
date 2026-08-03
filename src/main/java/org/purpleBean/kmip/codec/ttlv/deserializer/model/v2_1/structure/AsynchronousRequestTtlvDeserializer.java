package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2_1.enumeration.ProcessingStage;
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;

public class AsynchronousRequestTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AsynchronousRequest,
        AsynchronousRequest.AsynchronousRequestBuilder> {

  public AsynchronousRequestTtlvDeserializer() {
    super(AsynchronousRequest.kmipTag, AsynchronousRequest.encodingType);
  }

  @Override
  protected AsynchronousRequest.AsynchronousRequestBuilder createBuilder() {
    return AsynchronousRequest.builder();
  }

  @Override
  protected void setValue(AsynchronousRequest.AsynchronousRequestBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.OPERATION -> builder.operation(mapper.readValue(p, Operation.class));
      case KmipTag.Standard.SUBMISSION_DATE ->
          builder.submissionDate(mapper.readValue(p, SubmissionDate.class));
      case KmipTag.Standard.PROCESSING_STAGE ->
          builder.processingStage(mapper.readValue(p, ProcessingStage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AsynchronousRequest build(AsynchronousRequest.AsynchronousRequestBuilder builder) {
    return builder.build();
  }
}