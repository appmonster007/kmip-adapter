package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ExportOpRequestPayload;

public class ExportOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ExportOpRequestPayload,
        ExportOpRequestPayload.ExportOpRequestPayloadBuilder> {

  public ExportOpRequestPayloadTtlvDeserializer() {
    super(ExportOpRequestPayload.kmipTag, ExportOpRequestPayload.encodingType);
  }

  @Override
  protected ExportOpRequestPayload.ExportOpRequestPayloadBuilder createBuilder() {
    return ExportOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ExportOpRequestPayload build(
      ExportOpRequestPayload.ExportOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}