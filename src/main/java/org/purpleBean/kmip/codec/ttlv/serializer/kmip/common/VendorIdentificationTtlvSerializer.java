package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.VendorIdentification;

public class VendorIdentificationTtlvSerializer extends AbstractKmipTtlvSerializer<VendorIdentification, String> {

    public VendorIdentificationTtlvSerializer() {
        super(VendorIdentification::getValue);
    }
}