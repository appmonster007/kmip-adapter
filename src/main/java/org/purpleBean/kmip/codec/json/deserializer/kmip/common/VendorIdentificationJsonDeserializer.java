package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<VendorIdentification, String> {

    public VendorIdentificationJsonDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}