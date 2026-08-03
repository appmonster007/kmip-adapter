package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;

public class SimpleResponseHeaderTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleResponseHeader,
        SimpleResponseHeader.SimpleResponseHeaderBuilder> {

  public SimpleResponseHeaderTtlvDeserializer() {
    super(SimpleResponseHeader.kmipTag, SimpleResponseHeader.encodingType);
  }

  @Override
  protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
    return SimpleResponseHeader.builder();
  }

  @Override
  protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, byte[] tag,
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
  protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
    return builder.build();
  }
}
