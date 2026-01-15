package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Username;

public class UsernameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Username, String> {

    public UsernameJsonSerializer() {
        super(Username::getValue);
    }
}