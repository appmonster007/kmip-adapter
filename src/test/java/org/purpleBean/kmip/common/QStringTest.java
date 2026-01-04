package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("QString Domain Tests")
class QStringTest extends AbstractKmipDataTypeSuite<QString> {

    @Override
    protected Class<QString> type() {
        return QString.class;
    }

    @Override
    protected QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}