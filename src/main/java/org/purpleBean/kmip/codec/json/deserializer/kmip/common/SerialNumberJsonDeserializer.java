package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SerialNumber, String> {

    public SerialNumberJsonDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType, String.class, value -> SerialNumber.builder().value(value).build());
    }
}