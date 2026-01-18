package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.P;

import java.math.BigInteger;

public class PTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<P, BigInteger> {

    public PTtlvSerializer() {
        super(P::getValue);
    }
}