package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<VendorIdentification, String> {

    public VendorIdentificationTtlvDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}