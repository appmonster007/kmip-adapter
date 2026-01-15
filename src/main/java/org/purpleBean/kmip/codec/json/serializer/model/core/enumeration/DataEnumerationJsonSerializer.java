package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataEnumeration, String> {

    public DataEnumerationJsonSerializer() {
        super(DataEnumeration::getDescription);
    }
}