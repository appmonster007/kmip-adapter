package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purplebean.kmip.model.v2x1.structure.Operations;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

/**
 * TTLV deserializer for {@link QueryAsynchronousRequestsOpRequestPayload}.
 */
public class QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryAsynchronousRequestsOpRequestPayload,
        QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer}.
   */
  public QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer() {
    super(QueryAsynchronousRequestsOpRequestPayload.kmipTag,
        QueryAsynchronousRequestsOpRequestPayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder
      createBuilder() {
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