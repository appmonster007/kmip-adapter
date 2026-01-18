package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

public class SerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SerialNumber, String> {

    public SerialNumberTtlvDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType, String.class, value -> SerialNumber.builder().value(value).build());
    }
}