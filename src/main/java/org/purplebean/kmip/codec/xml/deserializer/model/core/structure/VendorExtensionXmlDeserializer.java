package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.structure.VendorExtension;

/**
 * XML deserializer for {@link VendorExtension}.
 */
public class VendorExtensionXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<VendorExtension, VendorExtension.VendorExtensionBuilder> {

  /**
   * Constructs a new {@link VendorExtensionXmlDeserializer}.
   */
  public VendorExtensionXmlDeserializer() {
    super(VendorExtension.kmipTag, VendorExtension.encodingType);
  }

  @Override
  protected VendorExtension.VendorExtensionBuilder createBuilder() {
    return VendorExtension.builder();
  }

  @Override
  protected void setValue(VendorExtension.VendorExtensionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    // If the XML is:
    // <VendorExtension>
    //    <SomeVendorTag tag="0x..." type="..." value="..." />
    // </VendorExtension>
    builder.ttlvDataType(ctxt.readValue(p, TtlvDataType.class));
  }

  @Override
  protected VendorExtension build(VendorExtension.VendorExtensionBuilder builder) {
    return builder.build();
  }
}
