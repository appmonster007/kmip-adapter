package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.structure.HashedPasswordCredential;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

/**
 * XML deserializer for {@link HashedPasswordCredential}.
 */
public class HashedPasswordCredentialXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<HashedPasswordCredential,
        HashedPasswordCredential.HashedPasswordCredentialBuilder> {

  /**
   * Constructs a new {@link HashedPasswordCredentialXmlDeserializer}.
   */
  public HashedPasswordCredentialXmlDeserializer() {
    super(HashedPasswordCredential.kmipTag, HashedPasswordCredential.encodingType);
  }

  @Override
  protected HashedPasswordCredential.HashedPasswordCredentialBuilder createBuilder() {
    return HashedPasswordCredential.builder();
  }

  @Override
  protected void setValue(HashedPasswordCredential.HashedPasswordCredentialBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(ctxt.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.HASHED_USERNAME_PASSWORD ->
          builder.hashedUsernamePassword(ctxt.readValue(p, HashedUsernamePassword.class));
      case KmipTag.Standard.HASHED_PASSWORD_USERNAME ->
          builder.hashedPasswordUsername(ctxt.readValue(p, HashedPasswordUsername.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashedPasswordCredential build(
      HashedPasswordCredential.HashedPasswordCredentialBuilder builder) {
    return builder.build();
  }
}