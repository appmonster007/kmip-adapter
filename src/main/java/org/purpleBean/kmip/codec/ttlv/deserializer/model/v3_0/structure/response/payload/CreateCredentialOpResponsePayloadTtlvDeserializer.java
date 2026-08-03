package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateCredentialOpResponsePayload;

public class CreateCredentialOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateCredentialOpResponsePayload,
        CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder> {

  public CreateCredentialOpResponsePayloadTtlvDeserializer() {
    super(CreateCredentialOpResponsePayload.kmipTag,
        CreateCredentialOpResponsePayload.encodingType);
  }

  @Override
  protected CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder createBuilder() {
    return CreateCredentialOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateCredentialOpResponsePayload build(
      CreateCredentialOpResponsePayload.CreateCredentialOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}