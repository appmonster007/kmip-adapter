package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XJsonSerializer extends AbstractKmipDataTypeJsonSerializer<X, BigInteger> {

    public XJsonSerializer() {
        super(X::getValue);
    }
}