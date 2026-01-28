package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.VendorExtension;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class VendorExtensionTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<VendorExtension, VendorExtension.VendorExtensionBuilder> {

    public VendorExtensionTtlvDeserializer() {
        super(VendorExtension.kmipTag);
    }

    @Override
    protected VendorExtension.VendorExtensionBuilder createBuilder() {
        return VendorExtension.builder();
    }

    @Override
    protected void setValue(VendorExtension.VendorExtensionBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.ttlvDataType(mapper.readValue(p, TtlvDataType.class));
    }

    @Override
    protected VendorExtension build(VendorExtension.VendorExtensionBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return VendorExtension.encodingType;
    }
}
