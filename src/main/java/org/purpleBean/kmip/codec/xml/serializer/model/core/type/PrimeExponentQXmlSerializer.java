package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQXmlSerializer() {
        super(PrimeExponentQ::getValue);
    }
}