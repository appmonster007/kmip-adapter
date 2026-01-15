package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<QString, ByteBuffer> {

    public QStringXmlDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}