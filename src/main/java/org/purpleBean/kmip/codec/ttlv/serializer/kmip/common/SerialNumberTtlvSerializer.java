package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SerialNumber, String> {

    public SerialNumberTtlvSerializer() {
        super(SerialNumber::getValue);
    }
}