package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;

public class CreateCredentialOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateCredentialOpRequestPayload,
        CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder> {

  public CreateCredentialOpRequestPayloadJsonDeserializer() {
    super(CreateCredentialOpRequestPayload.kmipTag, CreateCredentialOpRequestPayload.encodingType);
  }

  @Override
  protected CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder createBuilder() {
    return CreateCredentialOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CREDENTIAL_TYPE -> {
        CredentialType credentialType = ctxt.readValue(p, CredentialType.class);
        builder.credentialType(credentialType);
        ctxt.setAttribute("credentialType", credentialType.getDescription());
      }
      case KmipTag.Standard.CREDENTIAL_VALUE ->
          builder.credentialValue(ctxt.readValue(p, CredentialValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateCredentialOpRequestPayload build(
      CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}