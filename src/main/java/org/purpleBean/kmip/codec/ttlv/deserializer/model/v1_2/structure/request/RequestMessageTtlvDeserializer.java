package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestMessage;

public class RequestMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RequestMessage, RequestMessage.RequestMessageBuilder> {

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