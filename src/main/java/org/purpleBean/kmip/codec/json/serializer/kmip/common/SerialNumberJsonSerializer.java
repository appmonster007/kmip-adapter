package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SerialNumber, String> {

    public SerialNumberJsonSerializer() {
        super(SerialNumber::getValue);
    }
}