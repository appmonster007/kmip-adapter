package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Y, BigInteger> {

    public YJsonSerializer() {
        super(Y::getValue);
    }
}