package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

public class SerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SerialNumber, String> {

    public SerialNumberJsonSerializer() {
        super(SerialNumber::getValue);
    }
}