package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;

public class ProfileVersionMinorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProfileVersionMinor,
        ProfileVersionMinor.ProfileVersionMinorBuilder> {

  public ProfileVersionMinorTtlvDeserializer() {
    super(ProfileVersionMinor.kmipTag, ProfileVersionMinor.encodingType);
  }

  @Override
  protected ProfileVersionMinor.ProfileVersionMinorBuilder createBuilder() {
    return ProfileVersionMinor.builder();
  }

  @Override
  protected void setValue(ProfileVersionMinor.ProfileVersionMinorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ProfileVersionMinor build(ProfileVersionMinor.ProfileVersionMinorBuilder builder) {
    return builder.build();
  }
}