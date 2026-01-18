package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<VendorIdentification, String> {

    public VendorIdentificationJsonDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType, String.class, value -> VendorIdentification.builder().value(value).build());
    }
}