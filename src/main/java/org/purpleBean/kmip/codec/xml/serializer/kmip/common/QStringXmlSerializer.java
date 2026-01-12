package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.nio.ByteBuffer;

public class QStringXmlSerializer extends AbstractKmipXmlSerializer<QString, ByteBuffer> {

    public QStringXmlSerializer() {
        super(QString::getValue);
    }
}