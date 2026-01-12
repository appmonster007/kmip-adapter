package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationTtlvDeserializer extends AbstractKmipTtlvDeserializer<VendorIdentification, String> {

    public VendorIdentificationTtlvDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}