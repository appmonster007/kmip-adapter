package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AlternativeNameValue, String> {

    public AlternativeNameValueJsonSerializer() {
        super(AlternativeNameValue::getValue);
    }
}