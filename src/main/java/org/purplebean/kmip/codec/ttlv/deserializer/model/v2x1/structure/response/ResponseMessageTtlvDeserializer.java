package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponseBatchItemStructure;
import org.purplebean.kmip.api.response.ResponseHeaderStructure;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.ResponseMessage;

/**
 * TTLV deserializer for {@link ResponseMessage}.
 */
public class ResponseMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResponseMessage, ResponseMessage.ResponseMessageBuilder> {

  /**
   * Constructs a new {@link ResponseMessageTtlvDeserializer}.
   */
  public ResponseMessageTtlvDeserializer() {
    super(ResponseMessage.kmipTag, ResponseMessage.encodingType);
  }

  @Override
  protected ResponseMessage.ResponseMessageBuilder createBuilder() {
    return ResponseMessage.builder();
  }

  @Override
  protected void setValue(ResponseMessage.ResponseMessageBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RESPONSE_HEADER ->
          builder.responseHeader(mapper.readValue(p, ResponseHeaderStructure.class));
      case KmipTag.Standard.BATCH_ITEM -> {
        try {
          builder.responseBatchItem(mapper.readValue(p, ResponseBatchItemStructure.class));
          builder.responseBatchItemError(null);
        } catch (Exception e) {
          builder.responseBatchItem(null);
          builder.responseBatchItemError(e);
        }
      }
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ResponseMessage build(ResponseMessage.ResponseMessageBuilder builder) {
    return builder.build();
  }
}
