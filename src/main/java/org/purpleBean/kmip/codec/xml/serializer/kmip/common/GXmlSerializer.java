package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class GXmlSerializer extends AbstractKmipXmlSerializer<G, BigInteger> {

    public GXmlSerializer() {
        super(G::getValue);
    }
}