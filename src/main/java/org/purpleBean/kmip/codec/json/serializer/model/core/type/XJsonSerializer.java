package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.X;

import java.math.BigInteger;

public class XJsonSerializer extends AbstractKmipDataTypeJsonSerializer<X, BigInteger> {

    public XJsonSerializer() {
        super(X::getValue);
    }
}