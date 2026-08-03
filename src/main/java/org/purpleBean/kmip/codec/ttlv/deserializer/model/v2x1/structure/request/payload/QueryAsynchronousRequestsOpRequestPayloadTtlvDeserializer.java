package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purpleBean.kmip.model.v2x1.structure.Operations;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

public class QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryAsynchronousRequestsOpRequestPayload,
        QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder> {

  public QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer() {
    super(QueryAsynchronousRequestsOpRequestPayload.kmipTag,
        QueryAsynchronousRequestsOpRequestPayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder createBuilder() {
    return QueryAsynchronousRequestsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUES ->
          builder.asynchronousCorrelationValues(
              mapper.readValue(p, AsynchronousCorrelationValues.class));
      case KmipTag.Standard.OPERATIONS -> builder.operations(mapper.readValue(p, Operations.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryAsynchronousRequestsOpRequestPayload build(
      QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}