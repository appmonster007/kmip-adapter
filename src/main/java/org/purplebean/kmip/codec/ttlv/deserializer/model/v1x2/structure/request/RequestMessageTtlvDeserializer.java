package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v1x2.structure.request.RequestMessage;

/**
 * TTLV deserializer for {@link RequestMessage}.
 */
public class RequestMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RequestMessage, RequestMessage.RequestMessageBuilder> {

  /**
   * Constructs a new {@link RequestMessageTtlvDeserializer}.
   */
  public RequestMessageTtlvDeserializer() {
    super(RequestMessage.kmipTag, RequestMessage.encodingType);
  }

  @Override
  protected RequestMessage.RequestMessageBuilder createBuilder() {
    return RequestMessage.builder();
  }

  @Override
  protected void setValue(RequestMessage.RequestMessageBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REQUEST_HEADER ->
          builder.requestHeader(mapper.readValue(p, RequestHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        try {
          builder.requestBatchItem(mapper.readValue(p, RequestBatchItemStructure.class));
          builder.requestBatchItemError(null);
        } catch (Exception e) {
          builder.requestBatchItem(null);
          builder.requestBatchItemError(e);
        }
      }
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RequestMessage build(RequestMessage.RequestMessageBuilder builder) {
    return builder.build();
  }
}