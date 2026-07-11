package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.ValidationInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationInformation Json Serialization Tests")
class ValidationInformationJsonTest extends AbstractJsonSerializationTestSuite<ValidationInformation> {

    @Override
    public Class<ValidationInformation> type() {
        return ValidationInformation.class;
    }

    @Override
    public ValidationInformation createDefault() {
        return ValidationInformation.builder().build();
    }

    @Override
    public ValidationInformation createVariant() {
        return ValidationInformation.builder().build();
    }
}