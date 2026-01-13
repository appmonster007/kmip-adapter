package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<VendorIdentification, String> {

    public VendorIdentificationTtlvSerializer() {
        super(VendorIdentification::getValue);
    }
}