package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;

public class SimpleRequestHeaderTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleRequestHeader,
        SimpleRequestHeader.SimpleRequestHeaderBuilder> {

  public SimpleRequestHeaderTtlvDeserializer() {
    super(SimpleRequestHeader.kmipTag, SimpleRequestHeader.encodingType);
  }

  @Override
  protected SimpleRequestHeader.SimpleRequestHeaderBuilder createBuilder() {
    return SimpleRequestHeader.builder();
  }

  @Override
  protected void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
      default -> {
        mapper.readValue(p, KmipDataType.class);
      }
    }
  }

  @Override
  protected SimpleRequestHeader build(SimpleRequestHeader.SimpleRequestHeaderBuilder builder) {
    return builder.build();
  }
}
