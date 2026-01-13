package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ApplicationData;

public class ApplicationDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ApplicationData, String> {

    public ApplicationDataJsonSerializer() {
        super(ApplicationData::getValue);
    }
}