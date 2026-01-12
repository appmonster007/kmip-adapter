package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthJsonSerializer extends AbstractKmipJsonSerializer<FixedFieldLength, Integer> {

    public FixedFieldLengthJsonSerializer() {
        super(FixedFieldLength::getValue);
    }
}