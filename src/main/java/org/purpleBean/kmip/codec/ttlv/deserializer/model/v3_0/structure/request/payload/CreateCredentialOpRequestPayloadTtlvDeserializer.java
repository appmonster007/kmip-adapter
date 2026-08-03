package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateCredentialOpRequestPayload;

public class CreateCredentialOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateCredentialOpRequestPayload,
        CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder> {

  public CreateCredentialOpRequestPayloadTtlvDeserializer() {
    super(CreateCredentialOpRequestPayload.kmipTag, CreateCredentialOpRequestPayload.encodingType);
  }

  @Override
  protected CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder createBuilder() {
    return CreateCredentialOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CREDENTIAL_TYPE -> {
        CredentialType credentialType = mapper.readValue(p, CredentialType.class);
        builder.credentialType(credentialType);
        mapper.setAttribute("credentialType", credentialType.getDescription());
      }
      case KmipTag.Standard.CREDENTIAL_VALUE ->
          builder.credentialValue(mapper.readValue(p, CredentialValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateCredentialOpRequestPayload build(
      CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}