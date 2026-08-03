package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeriveKeyOpRequestPayload,
        DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder> {

  public DeriveKeyOpRequestPayloadTtlvDeserializer() {
    super(DeriveKeyOpRequestPayload.kmipTag, DeriveKeyOpRequestPayload.encodingType);
  }

  @Override
  protected DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder createBuilder() {
    return DeriveKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DERIVATION_METHOD ->
          builder.derivationMethod(mapper.readValue(p, DerivationMethod.class));
      case KmipTag.Standard.DERIVATION_PARAMETERS ->
          builder.derivationParameters(mapper.readValue(p, DerivationParameters.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeriveKeyOpRequestPayload build(
      DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}