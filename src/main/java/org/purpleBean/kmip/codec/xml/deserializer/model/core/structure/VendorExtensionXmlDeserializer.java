package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.VendorExtension;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.io.IOException;

public class VendorExtensionXmlDeserializer extends AbstractKmipStructureXmlDeserializer<VendorExtension, VendorExtension.VendorExtensionBuilder> {

    public VendorExtensionXmlDeserializer() {
        super(VendorExtension.kmipTag);
    }

    @Override
    protected VendorExtension.VendorExtensionBuilder createBuilder() {
        return VendorExtension.builder();
    }

    @Override
    protected void setValue(VendorExtension.VendorExtensionBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
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
