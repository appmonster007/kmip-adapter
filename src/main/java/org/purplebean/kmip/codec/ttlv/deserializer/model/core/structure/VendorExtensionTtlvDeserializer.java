package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.structure.VendorExtension;

/**
 * TTLV deserializer for {@link VendorExtension}.
 */
public class VendorExtensionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<VendorExtension, VendorExtension.VendorExtensionBuilder> {

  /**
   * Constructs a new {@link VendorExtensionTtlvDeserializer}.
   */
  public VendorExtensionTtlvDeserializer() {
    super(VendorExtension.kmipTag, VendorExtension.encodingType);
  }

  @Override
  protected VendorExtension.VendorExtensionBuilder createBuilder() {
    return VendorExtension.builder();
  }

  @Override
  protected void setValue(VendorExtension.VendorExtensionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.ttlvDataType(mapper.readValue(p, TtlvDataType.class));
  }

  @Override
  protected VendorExtension build(VendorExtension.VendorExtensionBuilder builder) {
    return builder.build();
  }
}
