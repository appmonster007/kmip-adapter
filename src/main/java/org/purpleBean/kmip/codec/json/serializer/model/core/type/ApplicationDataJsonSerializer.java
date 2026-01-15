package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ApplicationData, String> {

    public ApplicationDataJsonSerializer() {
        super(ApplicationData::getValue);
    }
}