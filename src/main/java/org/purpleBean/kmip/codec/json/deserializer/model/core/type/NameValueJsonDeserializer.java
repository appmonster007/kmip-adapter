package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NameValue, String> {

    public NameValueJsonDeserializer() {
        super(NameValue.kmipTag, NameValue.encodingType, String.class, value -> NameValue.builder().value(value).build());
    }
}
