package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Authentication;
import org.purplebean.kmip.model.core.structure.Credential;

/**
 * TTLV deserializer for {@link Authentication}.
 */
public class AuthenticationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<Authentication, Authentication.AuthenticationBuilder> {

  /**
   * Constructs a new {@link AuthenticationTtlvDeserializer}.
   */
  public AuthenticationTtlvDeserializer() {
    super(Authentication.kmipTag, Authentication.encodingType);
  }

  @Override
  protected Authentication.AuthenticationBuilder createBuilder() {
    return Authentication.builder();
  }

  @Override
  protected void setValue(Authentication.AuthenticationBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == KmipTag.Standard.CREDENTIAL) {
      builder.credential(mapper.readValue(p, Credential.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Authentication build(Authentication.AuthenticationBuilder builder) {
    return builder.build();
  }
}