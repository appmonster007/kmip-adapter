package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<VendorIdentification, String> {

    public VendorIdentificationJsonSerializer() {
        super(VendorIdentification::getValue);
    }
}