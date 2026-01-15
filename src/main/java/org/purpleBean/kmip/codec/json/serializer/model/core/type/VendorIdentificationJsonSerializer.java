package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<VendorIdentification, String> {

    public VendorIdentificationJsonSerializer() {
        super(VendorIdentification::getValue);
    }
}