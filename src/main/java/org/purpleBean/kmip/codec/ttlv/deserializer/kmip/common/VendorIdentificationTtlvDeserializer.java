package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<VendorIdentification, String> {

    public VendorIdentificationTtlvDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}