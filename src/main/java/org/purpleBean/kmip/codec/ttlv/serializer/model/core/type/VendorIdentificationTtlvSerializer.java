package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class VendorIdentificationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<VendorIdentification, String> {

    public VendorIdentificationTtlvSerializer() {
        super(VendorIdentification::getValue);
    }
}