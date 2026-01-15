package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.G;

import java.math.BigInteger;

public class GTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<G, BigInteger> {

    public GTtlvSerializer() {
        super(G::getValue);
    }
}