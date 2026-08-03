package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.structure.VendorExtension;

/**
 * JSON deserializer for {@link VendorExtension}.
 */
public class VendorExtensionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<VendorExtension, VendorExtension.VendorExtensionBuilder> {

  /**
   * Constructs a new {@link VendorExtensionJsonDeserializer}.
   */
  public VendorExtensionJsonDeserializer() {
    super(VendorExtension.kmipTag, VendorExtension.encodingType);
  }

  @Override
  protected VendorExtension.VendorExtensionBuilder createBuilder() {
    return VendorExtension.builder();
  }

  @Override
  protected void setValue(VendorExtension.VendorExtensionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    // Usually VendorExtension in KMIP is a structure containing the vendor-specific tag.
    // { "VendorExtension": [ { "tag": "0x...", "type": "...", "value": ... } ] }
    builder.ttlvDataType(ctxt.readValue(p, TtlvDataType.class));
  }

  @Override
  protected VendorExtension build(VendorExtension.VendorExtensionBuilder builder) {
    return builder.build();
  }
}
