package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.P;

import java.math.BigInteger;

public class PXmlSerializer extends AbstractKmipDataTypeXmlSerializer<P, BigInteger> {

    public PXmlSerializer() {
        super(P::getValue);
    }
}