package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NameValue, String> {

    public NameValueJsonSerializer() {
        super(NameValue::getValue);
    }
}