package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<VendorIdentification,
        VendorIdentification.VendorIdentificationBuilder> {

  public VendorIdentificationTtlvDeserializer() {
    super(VendorIdentification.kmipTag, VendorIdentification.encodingType);
  }

  @Override
  protected VendorIdentification.VendorIdentificationBuilder createBuilder() {
    return VendorIdentification.builder();
  }

  @Override
  protected void setValue(VendorIdentification.VendorIdentificationBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected VendorIdentification build(VendorIdentification.VendorIdentificationBuilder builder) {
    return builder.build();
  }
}