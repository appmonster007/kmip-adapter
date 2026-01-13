package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SerialNumber, String> {

    public SerialNumberTtlvDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType, String.class, value -> SerialNumber.builder().value(value).build());
    }
}