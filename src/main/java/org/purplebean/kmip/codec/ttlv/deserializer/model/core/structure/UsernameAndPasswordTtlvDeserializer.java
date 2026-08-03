package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;

/**
 * TTLV deserializer for {@link UsernameAndPassword}.
 */
public class UsernameAndPasswordTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UsernameAndPassword,
        UsernameAndPassword.UsernameAndPasswordBuilder> {

  /**
   * Constructs a new {@link UsernameAndPasswordTtlvDeserializer}.
   */
  public UsernameAndPasswordTtlvDeserializer() {
    super(UsernameAndPassword.kmipTag, UsernameAndPassword.encodingType);
  }

  @Override
  protected UsernameAndPassword.UsernameAndPasswordBuilder createBuilder() {
    return UsernameAndPassword.builder();
  }

  @Override
  protected void setValue(UsernameAndPassword.UsernameAndPasswordBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.USERNAME -> builder.username(mapper.readValue(p, Username.class));
      case KmipTag.Standard.PASSWORD -> builder.password(mapper.readValue(p, Password.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected UsernameAndPassword build(UsernameAndPassword.UsernameAndPasswordBuilder builder) {
    return builder.build();
  }
}