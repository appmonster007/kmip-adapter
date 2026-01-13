package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GXmlSerializer extends AbstractKmipDataTypeXmlSerializer<G, BigInteger> {

    public GXmlSerializer() {
        super(G::getValue);
    }
}