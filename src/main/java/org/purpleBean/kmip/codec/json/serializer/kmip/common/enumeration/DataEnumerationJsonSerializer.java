package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

public class DataEnumerationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataEnumeration, String> {

    public DataEnumerationJsonSerializer() {
        super(DataEnumeration::getDescription);
    }
}