package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;

public class QueryAsynchronousRequestsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryAsynchronousRequestsOpResponsePayload,
        QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder> {

  public QueryAsynchronousRequestsOpResponsePayloadTtlvDeserializer() {
    super(QueryAsynchronousRequestsOpResponsePayload.kmipTag,
        QueryAsynchronousRequestsOpResponsePayload.encodingType);
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder createBuilder() {
    return QueryAsynchronousRequestsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_REQUEST ->
          builder.asynchronousRequest(mapper.readValue(p, AsynchronousRequest.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryAsynchronousRequestsOpResponsePayload build(
      QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}