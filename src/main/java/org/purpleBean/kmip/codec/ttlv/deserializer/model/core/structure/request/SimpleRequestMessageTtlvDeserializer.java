package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

public class SimpleRequestMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleRequestMessage,
        SimpleRequestMessage.SimpleRequestMessageBuilder> {

  public SimpleRequestMessageTtlvDeserializer() {
    super(SimpleRequestMessage.kmipTag, SimpleRequestMessage.encodingType);
  }

  @Override
  protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
    return SimpleRequestMessage.builder();
  }

  @Override
  protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
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
  protected SimpleRequestMessage build(SimpleRequestMessage.SimpleRequestMessageBuilder builder) {
    return builder.build();
  }
}
