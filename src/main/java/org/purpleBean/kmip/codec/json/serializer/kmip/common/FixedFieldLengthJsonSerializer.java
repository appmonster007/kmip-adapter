package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthJsonSerializer() {
        super(FixedFieldLength::getValue);
    }
}