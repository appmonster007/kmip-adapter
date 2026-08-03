package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.model.v1x2.structure.request.payload.QueryOpRequestPayload;

/**
 * TTLV deserializer for {@link QueryOpRequestPayload}.
 */
public class QueryOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QueryOpRequestPayload,
        QueryOpRequestPayload.QueryOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link QueryOpRequestPayloadTtlvDeserializer}.
   */
  public QueryOpRequestPayloadTtlvDeserializer() {
    super(QueryOpRequestPayload.kmipTag, QueryOpRequestPayload.encodingType);
  }

  @Override
  protected QueryOpRequestPayload.QueryOpRequestPayloadBuilder createBuilder() {
    return QueryOpRequestPayload.builder();
  }

  @Override
  protected void setValue(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.QUERY_FUNCTION)) {
      builder.queryFunction(mapper.readValue(p, QueryFunction.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected QueryOpRequestPayload build(
      QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
