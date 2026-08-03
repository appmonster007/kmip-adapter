package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseMessage;

public class ResponseMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResponseMessage, ResponseMessage.ResponseMessageBuilder> {

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
