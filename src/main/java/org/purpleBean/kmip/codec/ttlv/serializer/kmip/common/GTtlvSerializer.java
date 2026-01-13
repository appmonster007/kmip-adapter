package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<G, BigInteger> {

    public GTtlvSerializer() {
        super(G::getValue);
    }
}