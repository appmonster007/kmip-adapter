package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.X;

import java.math.BigInteger;

public class XTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<X, BigInteger> {

    public XTtlvSerializer() {
        super(X::getValue);
    }
}