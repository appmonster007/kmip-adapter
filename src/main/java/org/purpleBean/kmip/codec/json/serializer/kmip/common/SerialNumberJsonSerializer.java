package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SerialNumber;

public class SerialNumberJsonSerializer extends AbstractKmipJsonSerializer<SerialNumber, String> {

    public SerialNumberJsonSerializer() {
        super(SerialNumber::getValue);
    }
}