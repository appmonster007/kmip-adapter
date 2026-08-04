package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.CredentialValue;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.HashedPasswordCredential;
import org.purplebean.kmip.model.v3x0.structure.OtpCredential;
import org.purplebean.kmip.model.v3x0.structure.PasswordCredential;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;

/**
 * JSON deserializer for {@link CreateCredentialOpRequestPayload}.
 */
public class CreateCredentialOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateCredentialOpRequestPayload,
        CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateCredentialOpRequestPayloadJsonDeserializer}.
   */
  public CreateCredentialOpRequestPayloadJsonDeserializer() {
    super(CreateCredentialOpRequestPayload.kmipTag, CreateCredentialOpRequestPayload.encodingType);
  }

  @Override
  protected CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder
      createBuilder() {
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
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      case KmipTag.Standard.CREDENTIAL_VALUE ->
          builder.credentialValue(ctxt.readValue(p, CredentialValue.class));
      case KmipTag.Standard.PASSWORD_CREDENTIAL ->
          builder.credentialValue(ctxt.readValue(p, PasswordCredential.class));
      case KmipTag.Standard.HASHED_PASSWORD_CREDENTIAL ->
          builder.credentialValue(ctxt.readValue(p, HashedPasswordCredential.class));
      case KmipTag.Standard.OTP_CREDENTIAL ->
          builder.credentialValue(ctxt.readValue(p, OtpCredential.class));
      case KmipTag.Standard.CERTIFICATE ->
          builder.credentialValue(ctxt.readValue(p, Certificate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateCredentialOpRequestPayload build(
      CreateCredentialOpRequestPayload.CreateCredentialOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}